package dev.programadorthi.shared.ui.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat
import dev.programadorthi.shared.domain.network.ConnectionCheck

internal class ConnectionCheckImpl(context: Context) : ConnectionCheck {
    private val service = ContextCompat.getSystemService(context, ConnectivityManager::class.java)

    override suspend fun hasInternetConnection(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return service?.getNetworkCapabilities(service.activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } ?: false
        }
        return service?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
}
