package com.pru.composeapp.fakeapi.presentation.posts_master

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pru.composeapp.fakeapi.R
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
                        navController.navigate(PostDetailsScreenDestination(post = it))
                    }
                }
            }
        }
    } ?: ErrorView(message = stringResource(R.string.no_posts))
}

