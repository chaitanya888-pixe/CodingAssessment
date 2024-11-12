package com.sample.chaitanyasampleapp.data.model

data class APIResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String
)

data class Source(
    val id: String?,
    val name: String
)

val fakeData = listOf(Article(source = Source(id="name", name = "sample"), author = "disha",title="first", description = "jbjbjhjhvhvjhvhjv", url = "", urlToImage = "url", publishedAt = "koti"))