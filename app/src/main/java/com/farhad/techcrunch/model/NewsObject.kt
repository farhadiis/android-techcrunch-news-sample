package com.farhad.techcrunch.model

data class NewsObject(
    val articles: List<NewsItem>,
    val totalResults: Int,
    val status: String
)