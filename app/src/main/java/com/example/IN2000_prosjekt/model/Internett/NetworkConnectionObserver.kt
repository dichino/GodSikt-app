package com.example.IN2000_prosjekt.model.Internett

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

// Observer class for network connection status
class NetworkConnectionObserver(
    private val context: Context

):NetworkObserver {
    // Access ConnectivityManager to monitor network state
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            ?: throw IllegalStateException("Failed to get ConnectivityManager")

    // Observes network status changes and emits corresponding events
    override fun observer(): Flow<NetworkObserver.Status> {
        return callbackFlow {
            // Callback to handle network availability changes
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(NetworkObserver.Status.Available) } // Emit 'Available' when network is accessible
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(NetworkObserver.Status.Unavailable) } // Emit 'Unavailable' when network is not accessible
                }
            }
            // Register the callback to listen for network changes
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                // Unregister the callback when observing stops
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged() // Ensure only distinct status changes are emitted


    }

    // Utility method to check for active internet connection
    fun isNetworkAvailable(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        // Check for internet capability and either WiFi or cellular transport
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }
}


