package com.pvbapps.moviefy.ui.activity.splash

import com.pvbapps.moviefy.ui.splash.SplashContract
import com.pvbapps.moviefy.ui.splash.SplashPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashPresenterTest {

    @Mock
    lateinit var view: SplashContract.View
    @InjectMocks
    lateinit var presenter: SplashPresenter

    @Test
    fun itShouldShowMovies() {
        presenter.onLogoAnimationEnd()
        verify(view).showMoviesScreen()
    }
}