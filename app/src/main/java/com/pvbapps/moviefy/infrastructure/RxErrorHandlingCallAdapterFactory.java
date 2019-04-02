package com.pvbapps.moviefy.infrastructure;

import com.pvbapps.moviefy.infrastructure.exceptions.ServerException;
import io.reactivex.*;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.*;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public final class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public CallAdapter get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter {
        private final Retrofit retrofit;
        private final CallAdapter wrapped;

        RxCallAdapterWrapper(Retrofit retrofit, CallAdapter wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings({"unchecked", "NullableProblems"})
        @Override
        public Object adapt(Call call) {
            Object adaptedCall = wrapped.adapt(call);

            if (adaptedCall instanceof Completable) {
                return ((Completable) adaptedCall).onErrorResumeNext(
                        throwable -> Completable.error(asRetrofitException(throwable)));
            }

            if (adaptedCall instanceof Single) {
                return ((Single) adaptedCall).onErrorResumeNext(
                        throwable -> Single.error(asRetrofitException((Throwable) throwable)));
            }

            if (adaptedCall instanceof Observable) {
                return ((Observable) adaptedCall).onErrorResumeNext(
                        (ObservableSource) throwable -> Observable.error(
                                asRetrofitException((Throwable) throwable)));
            }

            if (adaptedCall instanceof Maybe) {
                return ((Maybe) adaptedCall).onErrorResumeNext(
                        (MaybeSource) throwable -> Maybe.error(
                                asRetrofitException((Throwable) throwable)));
            }

            throw new RuntimeException("Observable Type not supported");
        }

        private ServerException asRetrofitException(Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return ServerException.Companion.httpError(response.raw().request().url().toString(),
                        response, retrofit);
            }

            // A network error happened
            if (throwable instanceof IOException) {
                return ServerException.Companion.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error
            return ServerException.Companion.unexpectedError(throwable);
        }
    }
}

