package com.sample.chaitanyasampleapp.data.repository

import com.sample.chaitanyasampleapp.BuildConfig
import com.sample.chaitanyasampleapp.data.api.ApiService
import com.sample.chaitanyasampleapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : DataRepository {
    override fun getListData(country: String): Flow<Result<List<Article>>> = flow {
        val response = apiService.getTopHeadlines(country, apiKey = BuildConfig.API_KEY)
        if (response.status == "ok") {
            emit(Result.success(response.articles))
        } else {
            emit(Result.failure(Exception("Error fetching articles")))
        }
    }.catch { e ->
        emit(Result.failure(e))
    }
}