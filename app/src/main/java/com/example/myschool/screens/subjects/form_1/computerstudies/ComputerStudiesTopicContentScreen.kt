package com.example.myschool.screens.subjects.form_1.computerstudies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myschool.data.getComputerStudiesTopic
import com.example.myschool.screens.StyledContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComputerStudiesTopicContentScreen(navController: NavController, topicId: String?) {
    val topic = getComputerStudiesTopic(topicId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(topic?.name ?: "Topic Content") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            topic?.let {
                StyledContent(it.content)
            }
        }
    }
}
