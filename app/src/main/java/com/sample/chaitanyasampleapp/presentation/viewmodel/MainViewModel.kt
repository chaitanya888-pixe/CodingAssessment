package com.sample.chaitanyasampleapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.chaitanyasampleapp.data.model.Article
import com.sample.chaitanyasampleapp.domain.usecase.GetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val DEFAULT_COUNTRY = "us"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(DataState())
    val state: StateFlow<DataState> = _state

    private val _selectedArticle = MutableStateFlow<Article?>(null)
    var selectedArticle: StateFlow<Article?> = _selectedArticle

/** Set Selected item model*/
    fun setSelectedData(article: Article) {
        _selectedArticle.value = article
    }

    init {
        fetchListData()
    }
/** Fetch the list data*/
     fun fetchListData(country: String = DEFAULT_COUNTRY) {
        _state.value = _state.value.copy(isLoading = true)
        getListUseCase(country)
            .onEach { result ->
                _state.value = when {
                    result.isSuccess -> {
                        val filteredArticles = result.getOrDefault(emptyList()).filter { article ->
                            !article.urlToImage.isNullOrEmpty()
                        }
                        DataState(articles = filteredArticles)
                        }
                    else -> DataState(error = result.exceptionOrNull()?.message ?: "Unknown Error")
                }
            }
            .launchIn(viewModelScope)
    }
}

data class DataState(
    val articles: List<Article> = emptyList(),
    val error: String? = null,
    var isLoading: Boolean = false
)
