package com.pru.composeapp.fakeapi.remote

import com.pru.composeapp.fakeapi.BuildConfig
import com.pru.composeapp.fakeapi.utils.APIConstants.kBaseUrl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIService @Inject constructor() {
    var httpClient = HttpClient(Android) {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            serializer = KotlinxSerializer(json = json)
        }

        engine {
            connectTimeout = 30_000
            socketTimeout = 30_000
        }

        if (BuildConfig.DEBUG) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }
        private set

    @Throws(Exception::class)
    suspend inline fun <reified T> makePostCall(
        rawBody: Any,
        endPoint: String
    ): T {
        val response: T = httpClient.post {
            url(url = URLBuilder(kBaseUrl + endPoint).build())
            headers {
                append("Content-Type", "application/json")
            }
            body = rawBody
        }
        return response
    }

    @Throws(Exception::class)
    suspend inline fun <reified T> makeGetCall(
        endPoint: String
    ): T {
        val response: T = httpClient.get {
            url(url = URLBuilder(kBaseUrl + endPoint).build())
            headers {
                append("Content-Type", "application/json")
            }
        }
        return response
    }

}