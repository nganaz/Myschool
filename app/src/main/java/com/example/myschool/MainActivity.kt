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
import com.example.myschool.data.QuestionData
import com.example.myschool.data.UserDataRepository
import com.example.myschool.screens.AccountScreen
import com.example.myschool.screens.ChatScreen
import com.example.myschool.screens.EnrollmentScreen
import com.example.myschool.screens.HomeScreen
import com.example.myschool.screens.LoginScreen
import com.example.myschool.screens.NewQuestionScreen
import com.example.myschool.screens.NotificationsScreen
import com.example.myschool.screens.ResponseScreen
import com.example.myschool.screens.SubjectsScreen
import com.example.myschool.screens.WelcomeScreen
import com.example.myschool.screens.subjects.form_1.computerstudies.ComputerStudiesSubjectScreen
import com.example.myschool.screens.subjects.form_1.computerstudies.ComputerStudiesTopicContentScreen
import com.example.myschool.screens.subjects.form_1.english.EnglishSubjectScreen
import com.example.myschool.screens.subjects.form_1.english.grammar.EnglishGrammarScreen
import com.example.myschool.screens.subjects.form_1.english.grammar.EnglishGrammarTopicContentScreen
import com.example.myschool.screens.subjects.form_1.english.literature.EnglishLiteratureScreen
import com.example.myschool.screens.subjects.form_1.english.literature.EnglishLiteratureTopicContentScreen
import com.example.myschool.screens.subjects.form_1.mathematics.MathematicsSubjectScreen
import com.example.myschool.screens.subjects.form_1.mathematics.MathematicsTopicContentScreen
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
    val showBottomBar = currentRoute != "welcome" &&
        currentRoute != "login" &&
        currentRoute != "enrollment" &&
        currentRoute?.startsWith("response") != true &&
        currentRoute != "new_question" &&
        currentRoute?.startsWith("englishSubject") != true &&
        currentRoute?.startsWith("englishGrammar") != true &&
        currentRoute?.startsWith("englishGrammarTopicContent") != true &&
        currentRoute?.startsWith("englishLiterature") != true &&
        currentRoute?.startsWith("englishLiteratureTopicContent") != true &&
        currentRoute?.startsWith("computerStudiesSubject") != true &&
        currentRoute?.startsWith("computerStudiesTopicContent") != true &&
        currentRoute?.startsWith("mathematicsSubject") != true &&
        currentRoute?.startsWith("mathematicsTopicContent") != true

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
                                val route = when {
                                    subject.id.startsWith("eng") -> "englishSubject/$form"
                                    subject.id.startsWith("cs") -> "computerStudiesSubject/$form"
                                    subject.id.startsWith("math") -> "mathematicsSubject/$form"
                                    else -> ""
                                }
                                if (route.isNotEmpty()) {
                                    navController.navigate(route)
                                }
                            },
                            navController = navController
                        )
                    }
                }
                composable(
                    "englishSubject/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    EnglishSubjectScreen(navController = navController, form = form)
                }
                composable(
                    "computerStudiesSubject/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    ComputerStudiesSubjectScreen(navController = navController, form = form)
                }
                composable(
                    "mathematicsSubject/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    MathematicsSubjectScreen(navController = navController, form = form)
                }
                composable(
                    "computerStudiesTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    ComputerStudiesTopicContentScreen(navController = navController, topicId = topicId)
                }
                composable(
                    "mathematicsTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    MathematicsTopicContentScreen(navController = navController, topicId = topicId)
                }
                composable(
                    "englishGrammar/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    EnglishGrammarScreen(navController = navController, form = form)
                }
                composable(
                    "englishGrammarTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    EnglishGrammarTopicContentScreen(navController = navController, topicId = topicId)
                }
                composable(
                    "englishLiterature/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    EnglishLiteratureScreen(navController = navController, form = form)
                }
                composable(
                    "englishLiteratureTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    EnglishLiteratureTopicContentScreen(navController = navController, topicId = topicId)
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
                composable(
                    "response/{questionId}",
                    arguments = listOf(navArgument("questionId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val questionId = backStackEntry.arguments?.getInt("questionId")
                    val question = QuestionData.questions.find { it.id == questionId }
                    if (question != null) {
                        ResponseScreen(navController = navController, question = question)
                    }
                }
                composable("new_question") {
                    NewQuestionScreen(navController = navController)
                }
            }
        }
    }
}
