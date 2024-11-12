package com.sample.chaitanyasampleapp.data.api

import com.sample.chaitanyasampleapp.data.model.APIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String
    ): APIResponse
}