package com.example.zenzy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.zenzy.ui.theme.ZenzyTheme
import com.example.zenzy.ui.screens.SplashScreen
import com.example.zenzy.ui.screens.LoginScreen
import com.example.zenzy.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZenzyTheme {
                ZenzyAppRouter()
            }
        }
    }
}

enum class ScreenState {
    SPLASH,
    LOGIN,
    HOME
}

@Composable
fun ZenzyAppRouter() {
    var currentScreen by rememberSaveable { mutableStateOf(ScreenState.SPLASH) }

    when (currentScreen) {
        ScreenState.SPLASH -> SplashScreen(
            onGetStarted = { currentScreen = ScreenState.HOME },
            onLogin = { currentScreen = ScreenState.LOGIN }
        )
        ScreenState.LOGIN -> LoginScreen(
            onLoginSuccess = { currentScreen = ScreenState.HOME }
        )
        ScreenState.HOME -> MainAppScreen()
    }
}

@PreviewScreenSizes
@Composable
fun MainAppScreen() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            // Use innerPadding to ensure content isn't covered by nav bar
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                when (currentDestination) {
                    AppDestinations.HOME -> HomeScreen()
                    else -> Greeting(name = currentDestination.label)
                }
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    FEED("Feed", R.drawable.ic_favorite),
    MESSAGE("Message", R.drawable.ic_home),
    ALERTS("Alerts", R.drawable.ic_favorite),
    PROFILE("Profile", R.drawable.ic_account_box),
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to $name!",
        modifier = modifier
    )
}
