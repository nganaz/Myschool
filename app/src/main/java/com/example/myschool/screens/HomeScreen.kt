package com.example.myschool.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myschool.R
import com.example.myschool.data.DataSource
import com.example.myschool.data.UserDataRepository
import com.example.myschool.data.searchAllTopics
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

// A helper function to safely convert the form string to an integer
private fun getGradeLevelFromForm(form: String?): Int? {
    if (form == null) return null
    // This will handle strings like "Form 1" and extract the number
    return form.filter { it.isDigit() }.toIntOrNull()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainNavController: NavController,
    modifier: Modifier = Modifier
) {

    val selectedForm = UserDataRepository.getSelectedForm()
    val gradeLevel = getGradeLevelFromForm(selectedForm)
    val subjects = if (gradeLevel != null) DataSource.getSubjectsForGrade(gradeLevel) else emptyList()
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val allForms = DataSource.getAllForms()
    val otherForms = allForms.filter { it != selectedForm }
    var expanded by remember { mutableStateOf(false) }
    val searchResults = if (searchText.text.isNotBlank()) searchAllTopics(searchText.text) else emptyList()
    val keyboardController = LocalSoftwareKeyboardController.current


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                ImageCarouselCard(form = selectedForm)
                Spacer(modifier = Modifier.height(16.dp))
                Box {
                    TextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            expanded = it.text.isNotBlank()
                        },
                        placeholder = { Text("Search topics") },
                        trailingIcon = {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Search",
                                modifier = Modifier.clickable { expanded = true }
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                expanded = true
                                keyboardController?.hide()
                            }
                        )
                    )
                    DropdownMenu(
                        expanded = expanded && searchResults.isNotEmpty(),
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        searchResults.take(5).forEach { topic ->
                            DropdownMenuItem(
                                text = { Text(topic.name) },
                                onClick = {
                                    val route = when {
                                        topic.id.startsWith("cs") -> "computerStudiesTopicContent/${topic.id}"
                                        topic.id.startsWith("math") -> "mathematicsTopicContent/${topic.id}"
                                        topic.id.startsWith("phy") -> "physicsTopicContent/${topic.id}"
                                        topic.id.startsWith("hist") -> "historyTopicContent/${topic.id}"
                                        topic.id.startsWith("chem") -> "chemistryTopicContent/${topic.id}"
                                        topic.id.startsWith("agric") -> "agricultureTopicContent/${topic.id}"
                                        topic.id.startsWith("ss") -> "socialStudiesTopicContent/${topic.id}"
                                        topic.id.startsWith("bio") -> "biologyTopicContent/${topic.id}"
                                        topic.id.startsWith("eng_gram") -> "englishGrammarTopicContent/${topic.id}"
                                        topic.id.startsWith("play") -> "englishLiteratureTopicContent/${topic.id}"
                                        topic.id.startsWith("short_stories") -> "englishLiteratureTopicContent/${topic.id}"
                                        topic.id.startsWith("poetry") -> "englishLiteratureTopicContent/${topic.id}"
                                        topic.id.startsWith("novel") -> "englishLiteratureTopicContent/${topic.id}"
                                        else -> ""
                                    }
                                    if (route.isNotEmpty()) {
                                        mainNavController.navigate(route)
                                    }
                                    searchText = TextFieldValue("")
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My Subjects",
                    style = MaterialTheme.typography.titleLarge
                )
                TextButton(
                    onClick = { mainNavController.navigate("subjects/$selectedForm") },
                ) {
                    Text("See all >")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(subjects.take(3)) { subject ->
                    SubjectCard(
                        subject = subject,
                        onSubjectClick = { clickedSubject ->
                            val route = when {
                                clickedSubject.id.startsWith("eng") -> "englishSubject/$selectedForm"
                                clickedSubject.id.startsWith("cs") -> "computerStudiesSubject/$selectedForm"
                                clickedSubject.id.startsWith("math") -> "mathematicsSubject/$selectedForm"
                                clickedSubject.id.startsWith("phy") -> "physicsSubject/$selectedForm"
                                clickedSubject.id.startsWith("ss") -> "socialStudiesSubject/$selectedForm"
                                clickedSubject.id.startsWith("hist") -> "historySubject/$selectedForm"
                                clickedSubject.id.startsWith("bio") -> "biologySubject/$selectedForm"
                                clickedSubject.id.startsWith("chem") -> "chemistrySubject/$selectedForm"
                                clickedSubject.id.startsWith("agric") -> "agricultureSubject/$selectedForm"
                                else -> ""
                            }
                            if (route.isNotEmpty()) {
                                mainNavController.navigate(route)
                            }
                        },
                        modifier = Modifier.width(280.dp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Explore",
                    style = MaterialTheme.typography.titleLarge
                )
                TextButton(
                    onClick = { mainNavController.navigate("enrollment") },
                ) {
                    Text("See all >")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(otherForms.take(4)) { form ->
                    Box(
                        modifier = Modifier
                            .width(180.dp)
                    ) {
                        ExploreCard(
                            title = form,
                            subtitle = "Explore other classes",
                            onClick = { mainNavController.navigate("enrollment") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageCarouselCard(form: String?) {
    val images = listOf(
        R.drawable.bookshelf,
        R.drawable.examroom,
        R.drawable.books
    )
    val user = Firebase.auth.currentUser
    val userName = user?.displayName?.split(" ")?.get(0)


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val pagerState = rememberPagerState(pageCount = { images.size })

            LaunchedEffect(key1 = pagerState) {
                while (true) {
                    delay(2000)
                    val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                    pagerState.animateScrollToPage(nextPage)
                }
            }

            HorizontalPager(state = pagerState) {
                Image(
                    painter = painterResource(id = images[it]),
                    contentDescription = "Carousel Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hi ${userName?:"student"}, Welcome to ${form ?: "MySchool"}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
