package com.sample.chaitanyasampleapp.domain.usecase

import com.sample.chaitanyasampleapp.data.model.Article
import com.sample.chaitanyasampleapp.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val repository: DataRepository
) {
    operator fun invoke(country: String): Flow<Result<List<Article>>> = repository.getListData(country)
}