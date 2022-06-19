package com.pru.composeapp.fakeapi.presentation.posts_master

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pru.composeapp.fakeapi.models.Post
import com.pru.composeapp.fakeapi.presentation.destinations.PostDetailsScreenDestination
import com.pru.composeapp.fakeapi.utils.UiState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient

@Composable
@Destination(start = true)
fun PostsMasterScreen(
    viewModel: PostsListViewModel = hiltViewModel(),
    navController: DestinationsNavigator,
    resultRecipient: ResultRecipient<PostDetailsScreenDestination, Boolean>
) {
    LaunchedEffect(true) {
        viewModel.triggerEvent(event = PostListEvent.FetchPosts)
    }
    resultRecipient.onNavResult(listener = { result ->
        when (result) {
            is NavResult.Canceled -> {

            }
            is NavResult.Value -> {
                viewModel.refreshData = true
                viewModel.triggerEvent(event = PostListEvent.FetchPosts)
            }
        }
    })
    when (val postsState = viewModel.postsState.value) {
        is UiState.Initial -> Unit
        is UiState.Loading -> LoadingView(Modifier.fillMaxSize())
        is UiState.Success -> PostsList(Modifier.fillMaxSize(), postsState.data, navController)
        is UiState.Error -> ErrorView(message = postsState.message!!)
    }
}

@Composable
fun PostsList(
    modifier: Modifier = Modifier,
    data: List<Post>?,
    navController: DestinationsNavigator
) {
    val lazyListState = rememberLazyListState()
    data?.let { items ->
        Box(modifier = modifier.padding(start = 10.dp, end = 10.dp)) {
            LazyColumn(state = lazyListState) {
                items(count = items.size) { pos ->
                    PostListItem(items[pos], pos == items.size - 1) {
                        navController.navigate(
                            PostDetailsScreenDestination(post = it), builder = {
                                Log.i("Prudhvi Log", "PostsList: ")
                            }
                        )
                    }
                }
            }
        }
    } ?: ErrorView(message = "No posts")
}

@Composable
fun ErrorView(modifier: Modifier = Modifier, message: String) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = message)
    }
}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(modifier = Modifier) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Loading")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListItem(post: Post, lastItem: Boolean = false, onTap: ((post: Post) -> Unit)? = null) {
    Card(
        modifier = Modifier.padding(top = 10.dp, bottom = if (lastItem) 10.dp else 0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (onTap == null) 0.dp else 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White), onClick = {
            onTap?.invoke(post)
        }
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Id : ${post.id}",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Title : ${post.title}",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Body : ${post.body}",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}