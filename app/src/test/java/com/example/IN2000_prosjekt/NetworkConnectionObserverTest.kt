import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.example.IN2000_prosjekt.model.Internett.NetworkConnectionObserver
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NetworkConnectionObserverTest {

    private lateinit var mockContext: Context
    private lateinit var mockConnectivityManager: ConnectivityManager
    private lateinit var networkObserver: NetworkConnectionObserver

    @Before
    fun setup() {
        mockContext = mockk(relaxed = true)
        mockConnectivityManager = mockk(relaxed = true)
        every { mockContext.getSystemService(Context.CONNECTIVITY_SERVICE) } returns mockConnectivityManager
        networkObserver = NetworkConnectionObserver(mockContext)
    }

    @Test
    fun networkIsAvailableViaWifi() {
        val mockNetwork = mockk<Network>(relaxed = true)
        val mockNetworkCapabilities = mockk<NetworkCapabilities>(relaxed = true)

        every { mockConnectivityManager.activeNetwork } returns mockNetwork
        every { mockConnectivityManager.getNetworkCapabilities(mockNetwork) } returns mockNetworkCapabilities
        every { mockNetworkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns true
        every { mockNetworkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) } returns true

        val isAvailable = networkObserver.isNetworkAvailable()

        assertTrue(isAvailable)
    }

    @Test
    fun networkIsAvailableViaCellular() {
        val mockNetwork = mockk<Network>(relaxed = true)
        val mockNetworkCapabilities = mockk<NetworkCapabilities>(relaxed = true)

        every { mockConnectivityManager.activeNetwork } returns mockNetwork
        every { mockConnectivityManager.getNetworkCapabilities(mockNetwork) } returns mockNetworkCapabilities
        every { mockNetworkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns true
        every { mockNetworkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) } returns true

        val isAvailable = networkObserver.isNetworkAvailable()
        assertTrue(isAvailable)
    }


    @Test
    fun networkIsUnavailable() {
        every { mockConnectivityManager.activeNetwork } returns null

        val isAvailable = networkObserver.isNetworkAvailable()

        assertFalse(isAvailable)
    }

    @Test
    fun networkIsLost() {
        val mockNetwork = mockk<Network>(relaxed = true)
        val mockNetworkCapabilities = mockk<NetworkCapabilities>(relaxed = true)

        every { mockConnectivityManager.activeNetwork } returns mockNetwork
        every { mockConnectivityManager.getNetworkCapabilities(mockNetwork) } returns mockNetworkCapabilities
        every { mockNetworkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns false

        val isAvailable = networkObserver.isNetworkAvailable()

        assertFalse(isAvailable)
    }
}

