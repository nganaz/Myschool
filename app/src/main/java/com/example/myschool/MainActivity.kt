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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myschool.data.UserDataRepository
import com.example.myschool.screens.AccountScreen
import com.example.myschool.AppFooter
import com.example.myschool.AppHeader
import com.example.myschool.screens.ChatScreen
import com.example.myschool.screens.EnrollmentScreen
import com.example.myschool.screens.HomeScreen
import com.example.myschool.screens.InfoScreen
import com.example.myschool.screens.LoginScreen
import com.example.myschool.screens.NewQuestionScreen
import com.example.myschool.screens.NotificationScreen
import com.example.myschool.screens.ResponseScreen
import com.example.myschool.screens.SubjectsScreen
import com.example.myschool.screens.WelcomeScreen
import com.example.myschool.screens.subjects.agriculture.AgricultureSubjectScreen
import com.example.myschool.screens.subjects.agriculture.AgricultureTopicContentScreen
import com.example.myschool.screens.subjects.biology.BiologySubjectScreen
import com.example.myschool.screens.subjects.biology.BiologyTopicContentScreen
import com.example.myschool.screens.subjects.chemistry.ChemistrySubjectScreen
import com.example.myschool.screens.subjects.chemistry.ChemistryTopicContentScreen
import com.example.myschool.screens.subjects.computerstudies.ComputerStudiesSubjectScreen
import com.example.myschool.screens.subjects.computerstudies.ComputerStudiesTopicContentScreen
import com.example.myschool.screens.subjects.english.grammar.EnglishGrammarScreen
import com.example.myschool.screens.subjects.english.grammar.EnglishGrammarTopicContentScreen
import com.example.myschool.screens.subjects.english.literature.EnglishLiteratureScreen
import com.example.myschool.screens.subjects.english.literature.EnglishLiteratureTopicContentScreen
import com.example.myschool.screens.subjects.english.EnglishSubjectScreen
import com.example.myschool.screens.subjects.history.HistorySubjectScreen
import com.example.myschool.screens.subjects.history.HistoryTopicContentScreen
import com.example.myschool.screens.subjects.mathematics.MathematicsSubjectScreen
import com.example.myschool.screens.subjects.mathematics.MathematicsTopicContentScreen
import com.example.myschool.screens.subjects.physics.PhysicsSubjectScreen
import com.example.myschool.screens.subjects.physics.PhysicsTopicContentScreen
import com.example.myschool.screens.subjects.socialstudies.SocialStudiesSubjectScreen
import com.example.myschool.screens.subjects.socialstudies.SocialStudiesTopicContentScreen
import com.example.myschool.ui.theme.MySchoolTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        UserDataRepository.initialize(this)
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }
            MySchoolTheme(darkTheme = isDarkMode) {
                AppNavigation(
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = { isDarkMode = !isDarkMode }
                )
            }
        }
    }
}

@Composable
fun AppNavigation(isDarkMode: Boolean, onToggleDarkMode: () -> Unit) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showAppHeader = currentRoute == "main"
    val showBottomBar = currentRoute == "main" ||
        currentRoute == "subjects/{form}" ||
        currentRoute == "chat" ||
        currentRoute == "account"

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
                        onNotificationsClick = { navController.navigate("notifications") },
                        onHelpClick = { navController.navigate("info/Help") },
                        onAboutClick = { navController.navigate("info/About") }
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
                startDestination = if (UserDataRepository.hasSeenWelcome()) "login" else "welcome",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("welcome") {
                    WelcomeScreen(onGetStartedClicked = {
                        UserDataRepository.saveWelcomeSeen()
                        navController.navigate("login")
                    })
                }
                composable("login") {
                    LoginScreen(
                        onLoginSuccess = { isNewUser ->
                            if (isNewUser || !UserDataRepository.hasEnrolled()) {
                                navController.navigate("enrollment")
                            } else {
                                navController.navigate("main") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        },
                        navController = navController
                    )
                }
                composable("enrollment") {
                    EnrollmentScreen(
                        onFormSelected = {
                            UserDataRepository.saveSelectedForm(it)
                            navController.navigate("main") {
                                popUpTo("login") { inclusive = true }
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
                                    subject.id.startsWith("phy") -> "physicsSubject/$form"
                                    subject.id.startsWith("ss") -> "socialStudiesSubject/$form"
                                    subject.id.startsWith("hist") -> "historySubject/$form"
                                    subject.id.startsWith("bio") -> "biologySubject/$form"
                                    subject.id.startsWith("chem") -> "chemistrySubject/$form"
                                    subject.id.startsWith("agric") -> "agricultureSubject/$form"
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
                    "physicsSubject/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    PhysicsSubjectScreen(navController = navController, form = form)
                }
                composable(
                    "socialStudiesSubject/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    SocialStudiesSubjectScreen(navController = navController, form = form)
                }
                composable(
                    "historySubject/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    HistorySubjectScreen(navController = navController, form = form)
                }
                composable(
                    "biologySubject/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    BiologySubjectScreen(navController = navController, form = form)
                }
                composable(
                    "chemistrySubject/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    ChemistrySubjectScreen(navController = navController, form = form)
                }
                composable(
                    "agricultureSubject/{form}",
                    arguments = listOf(navArgument("form") { type = NavType.StringType })
                ) { backStackEntry ->
                    val form = backStackEntry.arguments?.getString("form")
                    AgricultureSubjectScreen(navController = navController, form = form)
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
                    "physicsTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    PhysicsTopicContentScreen(navController = navController, topicId = topicId)
                }
                composable(
                    "historyTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    HistoryTopicContentScreen(navController = navController, topicId = topicId)
                }
                composable(
                    "chemistryTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    ChemistryTopicContentScreen(navController = navController, topicId = topicId)
                }
                composable(
                    "agricultureTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    AgricultureTopicContentScreen(navController = navController, topicId = topicId)
                }
                composable(
                    "socialStudiesTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    SocialStudiesTopicContentScreen(navController = navController, topicId = topicId)
                }
                composable(
                    "biologyTopicContent/{topicId}",
                    arguments = listOf(navArgument("topicId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val topicId = backStackEntry.arguments?.getString("topicId")
                    BiologyTopicContentScreen(navController = navController, topicId = topicId)
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
                    AccountScreen(
                        navController = navController,
                        onSignOut = {
                            navController.navigate("login") {
                                popUpTo("main") { inclusive = true }
                            }
                        },
                        isDarkMode = isDarkMode,
                        onToggleDarkMode = onToggleDarkMode
                    )
                }
                composable("notifications") {
                    NotificationScreen(navController = navController)
                }
                composable(
                    "response/{questionId}",
                    arguments = listOf(navArgument("questionId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val questionId = backStackEntry.arguments?.getString("questionId")
                    if (questionId != null) {
                        ResponseScreen(navController = navController, questionId = questionId)
                    }
                }
                composable("new_question") {
                    NewQuestionScreen(navController = navController)
                }
                composable(
                    "info/{infoType}",
                    arguments = listOf(navArgument("infoType") { type = NavType.StringType })
                ) { backStackEntry ->
                    val infoType = backStackEntry.arguments?.getString("infoType")
                    InfoScreen(navController = navController, infoType = infoType)
                }
            }
        }
    }
}
