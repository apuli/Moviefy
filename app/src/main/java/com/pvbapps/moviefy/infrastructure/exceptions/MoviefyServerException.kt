package com.pvbapps.moviefy.infrastructure.exceptions

import com.google.gson.annotations.SerializedName

class MoviefyServerException {
    @SerializedName("error_code")
    val mErrorCode: Int? = null

    @SerializedName("message")
    val mErrorMessage: String? = null
}