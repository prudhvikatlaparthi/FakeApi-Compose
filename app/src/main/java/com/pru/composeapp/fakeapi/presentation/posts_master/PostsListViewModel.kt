package com.pru.composeapp.fakeapi.presentation.posts_master

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pru.composeapp.fakeapi.domain.usecases.PostsUseCase
import com.pru.composeapp.fakeapi.models.Post
import com.pru.composeapp.fakeapi.utils.UiState
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsListViewModel @Inject constructor(
    private val postsUseCase: Lazy<PostsUseCase>
) : ViewModel() {
    private val _postsState = mutableStateOf<UiState<List<Post>>>(UiState.Initial())
    val postsState: State<UiState<List<Post>>> = _postsState
    var refreshData: Boolean = true

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
        postsUseCase.get().fetchPosts().collect {
            _postsState.value = it
        }
    }
}