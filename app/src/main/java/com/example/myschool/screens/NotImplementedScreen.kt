package com.example.myschool.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun NotImplementedScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize().then(modifier), contentAlignment = Alignment.Center) {
        Text(text = "Not Implemented Yet")
    }
}
