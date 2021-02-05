package com.farhad.techcrunch.repo

import com.farhad.techcrunch.model.NewsItem
import com.farhad.techcrunch.model.NewsObject
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    val allFlowNews: Flow<List<NewsItem>>
    suspend fun deleteAll()
    suspend fun insert(newsItem: NewsItem)
    suspend fun insert(newsItems: List<NewsItem>)
    suspend fun allNews(): List<NewsItem>
    suspend fun remoteNews(): NewsObject


}