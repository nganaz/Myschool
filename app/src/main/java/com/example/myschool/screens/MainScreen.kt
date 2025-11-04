package com.example.myschool.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MainScreen(
    mainNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    HomeScreen(
        mainNavController = mainNavController,
        modifier = modifier
    )
}
