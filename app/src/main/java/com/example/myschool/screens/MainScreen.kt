package com.example.myschool.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
// The modifier with padding is passed in from the Scaffold
fun MainScreen(innerNavController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = innerNavController,
        startDestination = "home",
        modifier = modifier // This modifier contains the padding from the Scaffold's top bar
    ) {
        // Route 1: Home Screen
        composable("home") {
            // Pass the controller so that subject cards can be clickable
            HomeScreen(navController = innerNavController)
        }

        // Route 2: Notifications Screen
        composable("notifications") {
            NotificationsScreen()
        }

        // Route 3: Subject Details Screen
        composable(
            route = "subject/{subjectId}",
            arguments = listOf(navArgument("subjectId") { type = NavType.StringType })
        ) { backStackEntry ->
            SubjectDetailsScreen(
                subjectId = backStackEntry.arguments?.getString("subjectId")
            )
        }

        // You can add more main screens here like "profile", "settings", etc.
    }
}
