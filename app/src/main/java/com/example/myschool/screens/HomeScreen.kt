package com.example.myschool.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myschool.R
import com.example.myschool.data.DataSource
import com.example.myschool.data.UserDataRepository
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

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8))
    ) {
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                ImageCarouselCard(form = selectedForm)
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.height(50.dp)) {
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("Search topics") },
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
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
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.DarkGray
                    )
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
                    Box(modifier = Modifier.width(280.dp)) {
                        SubjectCard(
                            subject = subject,
                            onSubjectClick = { mainNavController.navigate("subjectDetails/${it.id}") })
                    }
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
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.DarkGray
                    )
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
        R.drawable.seetop,
        R.drawable.examroom,
        R.drawable.books
    )

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
                    text = "Welcome to ${form ?: "MySchool"}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
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
