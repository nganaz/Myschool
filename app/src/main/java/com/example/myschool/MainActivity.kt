package com.example.myschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myschool.data.UserDataRepository
import com.example.myschool.screens.AccountScreen
import com.example.myschool.screens.ChatScreen
import com.example.myschool.screens.EnrollmentScreen
import com.example.myschool.screens.HomeScreen
import com.example.myschool.screens.LoginScreen
import com.example.myschool.screens.NotificationsScreen
import com.example.myschool.screens.SubjectsScreen
import com.example.myschool.screens.WelcomeScreen
import com.example.myschool.screens.subjects.form_1.english.EnglishSubjectScreen
import com.example.myschool.screens.subjects.form_1.english.grammar.EnglishGrammarTopicContentScreen
import com.example.myschool.ui.theme.MySchoolTheme
import kotlinx.coroutines.launch

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
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showAppHeader = currentRoute == "main"
    val showBottomBar = currentRoute != "welcome" && currentRoute != "login" && currentRoute != "enrollment"

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text("Welcome") },
                    selected = currentRoute == "welcome",
                    onClick = { navController.navigate("welcome"); scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Login") },
                    selected = currentRoute == "login",
                    onClick = { navController.navigate("login"); scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Enrollment") },
                    selected = currentRoute == "enrollment",
                    onClick = { navController.navigate("enrollment"); scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Main App") },
                    selected = currentRoute == "main",
                    onClick = { navController.navigate("main"); scope.launch { drawerState.close() } }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (showAppHeader) {
                    AppHeader(
                        onNotificationsClick = { navController.navigate("notifications") }
                    )
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    AppFooter(navController = navController)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = if (UserDataRepository.hasEnrolled()) "main" else "welcome",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("welcome") {
                    WelcomeScreen(onGetStartedClicked = { navController.navigate("login") })
                }
                composable("login") {
                    LoginScreen(
                        onLoginSuccess = { navController.navigate("enrollment") },
                        navController = navController
                    )
                }
                composable("enrollment") {
                    EnrollmentScreen(
                        onFormSelected = {
                            UserDataRepository.saveSelectedForm(it)
                            navController.navigate("main") {
                                popUpTo("welcome") { inclusive = true }
                            }
                        },
                        navController = navController
                    )
                }
                composable("main") {
                    HomeScreen(
                        mainNavController = navController
                    )
                }
                composable(
                    "subjects/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    if (form != null) {
                        SubjectsScreen(
                            form = form,
                            onSubjectClick = { subject ->
                                if (subject.id == "english") {
                                    navController.navigate("englishSubject")
                                } else {
                                    // Handle other subjects here
                                }
                            },
                            navController = navController
                        )
                    }
                }
                composable("englishSubject") {
                    EnglishSubjectScreen(navController = navController)
                }
                composable("englishGrammar") {
                    com.example.myschool.screens.subjects.form_1.english.grammar.EnglishGrammarScreen(navController = navController)
                }
                composable(
                    "englishGrammarTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    EnglishGrammarTopicContentScreen(navController = navController, topicId = topicId)
                }
                composable("chat") {
                    ChatScreen(navController = navController)
                }
                composable("account") {
                    AccountScreen(navController = navController)
                }
                composable("notifications") {
                    NotificationsScreen(navController = navController)
                }
            }
        }
    }
}
