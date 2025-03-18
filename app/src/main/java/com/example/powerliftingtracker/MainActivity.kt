package com.example.powerliftingtracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.powerliftingtracker.data.model.ErrorMessage
import com.example.powerliftingtracker.ui.component.home.HomeRoute
import com.example.powerliftingtracker.ui.component.home.HomeScreen
import com.example.powerliftingtracker.ui.component.signup.SignUpRoute
import com.example.powerliftingtracker.ui.component.signup.SignUpScreen
import com.example.powerliftingtracker.ui.theme.PowerliftingTrackerTheme
import com.example.powerliftingtracker.ui.theme.component.login.SignInRoute
import com.example.powerliftingtracker.ui.theme.component.login.SignInScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()

            PowerliftingTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = SignInRoute,
                            modifier = Modifier.padding(innerPadding)
                        )

                        {
                            composable<HomeRoute> {
                                HomeScreen(
                                    openSettingsScreen = {
                                        //navController.navigate(SettingsRoute) { launchSingleTop = true }
                                    }
                                )
                            }
                            composable<SignInRoute> {
                                SignInScreen(
                                    openHomeScreen = {
                                        navController.navigate(HomeRoute) { launchSingleTop = true }
                                    },
                                    openSignUpScreen = {
                                        navController.navigate(SignUpRoute) {
                                            launchSingleTop = true
                                        }
                                    },
                                    showErrorSnackbar = { errorMessage ->
                                        val message = getErrorMessage(errorMessage)
                                        scope.launch { snackbarHostState.showSnackbar(message) }
                                    }
                                )
                            }
                            composable<SignUpRoute> {
                                SignUpScreen(
                                    openHomeScreen = {
                                        navController.navigate(HomeRoute) {
                                            launchSingleTop = true
                                        }
                                    },
                                    showErrorSnackbar = { errorMessage ->
                                        val message = getErrorMessage(errorMessage)
                                        scope.launch { snackbarHostState.showSnackbar(message) }
                                    }
                                )
                            }

                        }

                    }
                }
            }
        }
    }

    private fun getErrorMessage(error: ErrorMessage): String {
        Log.d("MainActivity", "getErrorMessage called")
        return when (error) {
            is ErrorMessage.StringError -> error.message
            is ErrorMessage.IdError -> this@MainActivity.getString(error.message)
        }
    }
}

