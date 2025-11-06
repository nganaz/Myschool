package com.example.myschool.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myschool.data.UserDataRepository

@Composable
fun MainScreen(
    mainNavController: NavHostController,
) {
    val selectedForm = UserDataRepository.getSelectedForm()

    Column(modifier = Modifier.padding(16.dp)) {
        ExploreCard(
            title = "Explore Subjects",
            subtitle = "All My Subjects",
            onClick = {
                if (selectedForm != null) {
                    mainNavController.navigate("subjects/$selectedForm")
                }
            }
        )
    }
}