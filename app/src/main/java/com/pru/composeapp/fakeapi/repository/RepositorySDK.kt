package com.pru.composeapp.fakeapi.repository

import com.pru.composeapp.fakeapi.models.Post
import com.pru.composeapp.fakeapi.remote.APIService
import com.pru.composeapp.fakeapi.utils.APIConstants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositorySDK @Inject constructor(private val apiService: APIService) {

    suspend fun getPosts(): List<Post> {
        return apiService.makeGetCall(endPoint = APIConstants.kPost)
    }
}