package com.example.IN2000_prosjekt

import android.Manifest
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.IN2000_prosjekt.model.Internett.NetworkConnectionObserver
import com.example.IN2000_prosjekt.model.Internett.NetworkObserver
import com.example.IN2000_prosjekt.model.signs.SignDao
import com.example.IN2000_prosjekt.model.signs.SignDatabase
import com.example.IN2000_prosjekt.view.screens.HomeScreen
import com.example.IN2000_prosjekt.view.screens.NetworkStatusScreen
import com.example.IN2000_prosjekt.view.screens.settings.AboutDataSourcesScreen
import com.example.IN2000_prosjekt.view.screens.settings.AboutScreen
import com.example.IN2000_prosjekt.view.screens.settings.AboutUsScreen
import com.example.IN2000_prosjekt.view.screens.settings.PrivacyInfoScreen
import com.example.IN2000_prosjekt.view.screens.settings.TermsAndConditionsScreen
import com.example.IN2000_prosjekt.view.screens.showMap
import com.example.IN2000_prosjekt.view.screens.signs.CategoryScreen
import com.example.IN2000_prosjekt.view.screens.signs.SignDescriptionScreen
import com.example.IN2000_prosjekt.view.screens.signs.SignScreen
import com.example.IN2000_prosjekt.view.theme.IN2000_prosjektTheme
import com.example.IN2000_prosjekt.viewmodel.signs.SignViewModel
import com.example.IN2000_prosjekt.viewmodel.splashScreen.SplashScreenDelayer
import com.example.IN2000_prosjekt.viewmodel.weather.AppViewModel
import com.example.IN2000_prosjekt.viewmodel.weather.MapViewModel
import com.example.IN2000_prosjekt.viewmodel.weather.WeatherCardInfo

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }


}

class SharedViewModel : ViewModel() {
    private val _permissionState = MutableLiveData<Event<String?>>(Event(null))
    val permissionState: LiveData<Event<String?>> = _permissionState

    fun setPermissionState(destination: String?) {
        _permissionState.value = Event(destination)
    }
}

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var observernettverk: NetworkObserver

    //creating new database og copies from oceanSign-database
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            SignDatabase ::class.java,
            "oceanSigns.db"
        ).createFromAsset("database/oceanSigns.db").build()
    }

    private val requestPermissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val isFineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val isCoarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        // Permission is denied. Handle the failure to obtain permission.// Permission is granted. Continue with setting up location-based features.
        if (isFineLocationGranted || isCoarseLocationGranted){
            Log.d("GPS Test", "Granted")
            sharedViewModel.setPermissionState("MapScreen")
        }else{
            Log.d("GPS Test", "Not granted")
        }

    }
    private val useUserLocation = true

    public fun checkAndRequestLocationPermissions() {
        Log.d("MainActivity", "Check and request user location permission")
        if (useUserLocation) {
            when {
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                    // Explain to the user why you need the permission, and then request it.
                    requestPermissionsLauncher.launch(arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ))
                }
                else -> {
                    // Directly ask for the permission.
                    requestPermissionsLauncher.launch(arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ))
                }
            }
        }
    }

    private val viewModel by viewModels<SplashScreenDelayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // observernettverk = NetworkConnectionObserver(applicationContext)
        observernettverk = NetworkConnectionObserver(applicationContext)
        //splashScreen starts
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                !viewModel.isReady.value
            }
            setOnExitAnimationListener{screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd {screen.remove()}

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd {screen.remove()}

                zoomX.start()
                zoomY.start()

            }
        }
        setContent {
            IN2000_prosjektTheme (darkTheme = true){
                val status by observernettverk.observer().collectAsState(initial = NetworkObserver.Status.Unavailable)

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Greeting("Android")
                    Screen(this, db.dao, status, observernettverk)


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IN2000_prosjektTheme {
        Greeting("Android")
    }
}

@Composable
fun Screen(activity : MainActivity, signDao: SignDao, status: NetworkObserver.Status, networkObserver:NetworkObserver) {
    val navController = rememberNavController()
    val mapViewModel = viewModel<MapViewModel>()
    val sharedViewModel: SharedViewModel = viewModel()
    val appViewModel: AppViewModel = viewModel<AppViewModel>()
    val signViewModel: SignViewModel = SignViewModel(signDao)



    NavHost(
        navController = navController,
        startDestination = "HomeScreen") {

        //start
        composable("HomeScreen") {
            HomeScreen(navController, activity)
        }

        //map
        composable("MapScreen") {
            val statuss by networkObserver.observer().collectAsState(initial = NetworkObserver.Status.Unavailable)
            if (statuss == NetworkObserver.Status.Available) {
                showMap(navController, mapViewModel, appViewModel, activity)
            } else {
                NetworkStatusScreen(status = "Utilgjengelig", navController = navController)
            }
        }
        composable("WeatherScreen"){
            WeatherCardInfo(mapViewModel = mapViewModel, appViewModel = appViewModel)
        }

        //settings
        composable("AboutScreen") {
            AboutScreen(navController)
        }
        composable("AboutDataSourcesScreen"){
            AboutDataSourcesScreen(navController)
        }
        composable("AboutUsScreen"){
            AboutUsScreen(navController)
        }
        composable("PrivacyInfoScreen"){
            PrivacyInfoScreen(navController)
        }
        composable("TermsAndConditionsScreen"){
            TermsAndConditionsScreen(navController)
        }

        //signs
        composable("CategoryScreen"){
            CategoryScreen(navController, signViewModel)
        }
        composable("SignScreen"){
            SignScreen(signViewModel, navController)
        }
        composable("SignDescriptionScreen"){
            SignDescriptionScreen(signViewModel,navController)
        }

        composable("NetworkStatusScreen"){
            NetworkStatusScreen(status.toString(),navController)
        }
    }

    val permissionState = sharedViewModel.permissionState.observeAsState()
    LaunchedEffect(permissionState.value) {
        permissionState.value?.getContentIfNotHandled()?.let { destination ->
            if (destination == "MapScreen") {
                navController.navigate(destination)
            }
        }
    }
}