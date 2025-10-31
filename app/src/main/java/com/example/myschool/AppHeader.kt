package com.example.myschool

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Box // Needed to wrap the icon and menu
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.myschool.ui.theme.MySchoolTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(
    onMenuClick: () -> Unit, // Note: We will replace this, but leave it for now
    onNotificationsClick: () -> Unit
) {
    // 1. Add state to track if the menu is expanded
    var menuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current // For showing Toast messages

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFADD8E6),
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        title = {
            Text("MySchool")
        },
        navigationIcon = {
            // 2. Wrap the icon in a Box to anchor the DropdownMenu
            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu Icon"
                    )
                }

                // 3. This is the Dropdown Menu itself
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false } // Close if clicked outside
                ) {
                    // 4. These are the menu items
                    DropdownMenuItem(
                        text = { Text("Profile") },
                        onClick = {
                            Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show()
                            menuExpanded = false // Close the menu
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Settings") },
                        onClick = {
                            Toast.makeText(context, "Settings clicked", Toast.LENGTH_SHORT).show()
                            menuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Log Out") },
                        onClick = {
                            Toast.makeText(context, "Log Out clicked", Toast.LENGTH_SHORT).show()
                            menuExpanded = false
                        }
                    )
                }
            }
        },
        actions = {
            // The notification icon remains the same
            IconButton(onClick = onNotificationsClick) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification Icon"
                )
            }
        }
    )
}

@Preview
@Composable
fun AppHeaderPreview() {
    MySchoolTheme {
        AppHeader(onMenuClick = {}, onNotificationsClick = {})
    }
}
