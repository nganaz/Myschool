package com.example.myschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myschool.data.UserDataRepository
import com.example.myschool.screens.EnrollmentScreen
import com.example.myschool.screens.MainScreen
import com.example.myschool.screens.WelcomeScreen
import com.example.myschool.ui.theme.MySchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySchoolTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    // This is the TOP-LEVEL navigator.
    val navController = rememberNavController()

    val startDestination = if (UserDataRepository.hasEnrolled()) "main" else "welcome"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Route for screens WITHOUT the main header
        composable("welcome") {
            WelcomeScreen(onGetStartedClicked = { navController.navigate("enrollment") })
        }
        composable("enrollment") {
            EnrollmentScreen(
                onFormSelected = { selectedForm ->
                    UserDataRepository.saveSelectedForm(selectedForm)
                    navController.navigate("main") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        // Route for the entire MAIN APP, which has the header
        composable("main") {
            val innerNavController = rememberNavController()
            Scaffold(
                topBar = {
                    AppHeader(
                        onMenuClick = { /* ... */ },
                        onNotificationsClick = {
                            // Use the inner controller to navigate to notifications
                            innerNavController.navigate("notifications")
                        }
                    )
                }
            ) { innerPadding ->
                // Your MainScreen composable is used here!
                MainScreen(
                    innerNavController = innerNavController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
