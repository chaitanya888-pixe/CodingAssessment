package com.sample.chaitanyasampleapp.domain.repository

import com.sample.chaitanyasampleapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getListData(country: String): Flow<Result<List<Article>>>
}