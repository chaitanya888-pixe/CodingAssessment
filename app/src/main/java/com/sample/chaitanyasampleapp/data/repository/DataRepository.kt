package com.sample.chaitanyasampleapp.data.repository

import com.sample.chaitanyasampleapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getListData(country: String): Flow<Result<List<Article>>>
}