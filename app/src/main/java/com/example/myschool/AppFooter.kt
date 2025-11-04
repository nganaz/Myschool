package com.example.myschool

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myschool.data.UserDataRepository

@Composable
fun AppFooter(navController: NavHostController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val selectedForm = UserDataRepository.getSelectedForm()

        val itemColors = NavigationBarItemDefaults.colors(
            unselectedIconColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
            unselectedTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        )

        NavigationBarItem(
            colors = itemColors,
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentDestination?.hierarchy?.any { it.route == "main" } == true,
            onClick = {
                navController.navigate("main") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            colors = itemColors,
            icon = { Icon(Icons.Filled.Book, contentDescription = "Subjects") },
            label = { Text("Subjects") },
            selected = currentDestination?.hierarchy?.any {
                it.route == "subjects/{form}" ||
                it.route?.startsWith("subjectDetails/") == true ||
                it.route?.startsWith("topic/") == true
            } == true,
            onClick = {
                if(selectedForm != null) {
                    navController.navigate("subjects/$selectedForm") {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )

        NavigationBarItem(
            colors = itemColors,
            icon = { Icon(Icons.Filled.Chat, contentDescription = "Chat") },
            label = { Text("Chat") },
            selected = currentDestination?.hierarchy?.any { it.route == "chat" } == true,
            onClick = {
                navController.navigate("chat") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            colors = itemColors,
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Account") },
            label = { Text("Account") },
            selected = currentDestination?.hierarchy?.any { it.route == "account" } == true,
            onClick = {
                navController.navigate("account") {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}