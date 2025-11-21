package com.example.myschool

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myschool.screens.viewmodel.NotificationViewModel
import com.example.myschool.ui.theme.MySchoolTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(
    onNotificationsClick: () -> Unit,
    onHelpClick: () -> Unit,
    onAboutClick: () -> Unit,
    notificationViewModel: NotificationViewModel = viewModel()
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val notificationCount by notificationViewModel.notificationCount.collectAsState()

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.School,
                    contentDescription = "MySchool App Icon"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("MySchool")
            }
        },
        actions = {
            IconButton(onClick = onNotificationsClick) {
                BadgedBox(badge = { 
                    if (notificationCount > 0) {
                        Badge { Text(notificationCount.toString()) }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notification Icon"
                    )
                }
            }

            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "More Options"
                    )
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Help") },
                        onClick = {
                            onHelpClick()
                            menuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("About") },
                        onClick = {
                            onAboutClick()
                            menuExpanded = false
                        }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun AppHeaderPreview() {
    MySchoolTheme {
        AppHeader(onNotificationsClick = {}, onHelpClick = {}, onAboutClick = {})
    }
}
