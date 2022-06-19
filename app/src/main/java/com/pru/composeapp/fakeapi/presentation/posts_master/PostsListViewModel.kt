package com.pru.composeapp.fakeapi.presentation.posts_master

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pru.composeapp.fakeapi.models.Post
import com.pru.composeapp.fakeapi.repository.RepositorySDK
import com.pru.composeapp.fakeapi.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsListViewModel @Inject constructor(
    private val repositorySDK: RepositorySDK
) : ViewModel() {
    private val _postsState = mutableStateOf<UiState<List<Post>>>(UiState.Initial())
    val postsState: State<UiState<List<Post>>> = _postsState
    var refreshData: Boolean = true

    init {
        Log.i("Prudhvi Log", ": ")
    }

    fun triggerEvent(event: PostListEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                PostListEvent.FetchPosts -> fetchPosts()
            }
        }
    }

    private suspend fun fetchPosts() {
        if (!refreshData) {
            return
        }
        refreshData = false
        _postsState.value = UiState.Loading()
        kotlin.runCatching {
            repositorySDK.getPosts()
        }.onSuccess {
            _postsState.value = UiState.Success(it)
        }.onFailure {
            _postsState.value = UiState.Error(it.message ?: "Something went wrong")
        }
    }
}