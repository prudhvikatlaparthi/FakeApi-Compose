package com.pru.composeapp.fakeapi.data

import com.pru.composeapp.fakeapi.data.remote.APIService
import com.pru.composeapp.fakeapi.domain.Repository
import com.pru.composeapp.fakeapi.models.Post
import com.pru.composeapp.fakeapi.utils.APIConstants
import javax.inject.Inject
import javax.inject.Singleton

class RepositorySDK (private val apiService: APIService) : Repository {

    override suspend fun getPosts(): List<Post> {
        return apiService.makeGetCall(endPoint = APIConstants.kPost)
    }
}