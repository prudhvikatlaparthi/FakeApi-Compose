package com.pru.composeapp.fakeapi.presentation.posts_master

sealed class PostListEvent {
    object FetchPosts : PostListEvent()
}
