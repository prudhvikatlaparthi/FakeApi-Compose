package com.pru.composeapp.fakeapi.domain.usecases

import com.pru.composeapp.fakeapi.R
import com.pru.composeapp.fakeapi.base.MyApp
import com.pru.composeapp.fakeapi.domain.Repository
import com.pru.composeapp.fakeapi.models.Post
import com.pru.composeapp.fakeapi.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostsUseCase(
    private val repository: Repository
) {
    suspend fun fetchPosts(): Flow<UiState<List<Post>>> =
        flow {
            emit(UiState.Loading())
            kotlin.runCatching {
                repository.getPosts()
            }.onSuccess {
                emit(UiState.Success(it))
            }.onFailure {
                emit(UiState.Error(null ?: MyApp.context.getString(R.string.something_went_wrong)))
            }
        }

}