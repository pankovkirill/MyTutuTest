package com.tutu.myapplication.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capability = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    return capability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
}