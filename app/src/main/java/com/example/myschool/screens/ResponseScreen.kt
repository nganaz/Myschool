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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myschool.R
import com.example.myschool.data.Question
import com.example.myschool.data.Response
import com.example.myschool.screens.viewmodel.ResponseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponseScreen(navController: NavController, questionId: String, responseViewModel: ResponseViewModel = viewModel()) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val question by responseViewModel.question.collectAsState()
    val responses by responseViewModel.responses.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var responseToDelete by remember { mutableStateOf<String?>(null) }

    if (showDialog && responseToDelete != null) {
        ConfirmationDialog(
            onConfirm = {
                responseViewModel.deleteResponse(responseToDelete!!)
                showDialog = false
                responseToDelete = null
            },
            onDismiss = {
                showDialog = false
                responseToDelete = null
            },
            title = "Delete Response",
            text = "Are you sure you want to delete this response?"
        )
    }

    LaunchedEffect(questionId) {
        responseViewModel.fetchQuestion(questionId)
        responseViewModel.fetchResponses(questionId)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Response") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            question?.let { q ->
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        QuestionDetails(question = q, onLikeClicked = { 
                            // TODO: Implement like question in ResponseViewModel
                        })
                    }
                    item {
                        Text("${responses.size} Response${if (responses.size != 1) "s" else ""}", fontWeight = FontWeight.Bold)
                    }
                    items(responses) { response ->
                        ResponseItem(
                            response = response,
                            onDeleteClick = {
                                responseToDelete = response.id
                                showDialog = true
                            },
                            onLikeClicked = { isLiked ->
                                responseViewModel.likeResponse(response.id, isLiked)
                            },
                            onReplyClicked = {
                                responseViewModel.addReplyToResponse(response.id)
                            }
                        )
                    }
                }
                ResponseInput(responseViewModel, q.id)
            }
        }
    }
}

@Composable
fun QuestionDetails(question: Question, onLikeClicked: (Boolean) -> Unit) {
    val user = Firebase.auth.currentUser
    val isLiked = question.likedBy.contains(user?.uid)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = question.subject,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(question.question, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.welcomepage), // Replace with actual image
                        contentDescription = "Author image",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(question.author, fontWeight = FontWeight.Bold)
                        Text(question.date, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onLikeClicked(!isLiked) }) {
                        Icon(
                            imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Likes", 
                            tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant, 
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(question.likes.toString(), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun ResponseItem(
    response: Response,
    onDeleteClick: () -> Unit,
    onLikeClicked: (Boolean) -> Unit,
    onReplyClicked: () -> Unit
) {
    val user = Firebase.auth.currentUser
    val isLiked = response.likedBy.contains(user?.uid)

    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.welcomepage), // Replace with actual image
            contentDescription = "Author image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(response.author, fontWeight = FontWeight.Bold)
                    Text(response.date, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { 
                        onLikeClicked(!isLiked)
                     }) {
                        Icon(
                            imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder, 
                            contentDescription = "Likes", 
                            tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant, 
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Text(response.likes.toString(), color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
                    Box {
                        IconButton(onClick = onReplyClicked) {
                            Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Reply", tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(16.dp))
                        }
                        if (response.replies > 0) {
                            Text(
                                text = response.replies.toString(),
                                color = Color.Green,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .background(Color.Transparent, CircleShape)
                                    .padding(2.dp)
                            )
                        }
                    }
                    if (user?.uid == response.authorId) {
                        IconButton(onClick = onDeleteClick) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete response", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(response.response)
        }
    }
}

@Composable
fun ResponseInput(responseViewModel: ResponseViewModel, questionId: String) {
    var text by remember { mutableStateOf("") }
    val user = Firebase.auth.currentUser
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Write your response") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val newResponse = Response(
                    questionId = questionId,
                    author = user?.displayName ?: "Anonymous",
                    authorId = user?.uid ?: "",
                    authorImageUrl = "",
                    date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
                    response = text,
                    likes = 0,
                    replies = 0
                )
                responseViewModel.addResponse(newResponse) {
                    text = ""
                }
             },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Send")
        }
    }
}
