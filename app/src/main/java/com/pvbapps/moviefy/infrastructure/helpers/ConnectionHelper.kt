package com.pvbapps.moviefy.infrastructure.helpers

import android.net.ConnectivityManager

class ConnectionHelper(private val connectivityManager: ConnectivityManager) {

    val isConnected: Boolean
        get() {
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
}