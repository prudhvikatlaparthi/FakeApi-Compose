package com.pru.composeapp.fakeapi.domain

import com.pru.composeapp.fakeapi.models.Post

interface Repository {
    suspend fun getPosts(): List<Post>
}