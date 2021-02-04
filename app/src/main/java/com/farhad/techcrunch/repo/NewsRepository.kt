package com.farhad.techcrunch.repo

import androidx.annotation.WorkerThread
import com.farhad.techcrunch.api.ApiInterface
import com.farhad.techcrunch.model.NewsItem
import com.farhad.techcrunch.model.NewsObject
import kotlinx.coroutines.flow.Flow


class NewsRepository(private val newsDao: NewsDao, private val apiInterface: ApiInterface) {

    val allFlowNews: Flow<List<NewsItem>> = newsDao.getFlowNews()

    @WorkerThread
    suspend fun remoteNews(): NewsObject {
        return apiInterface.getTechCrunchNews()
    }

    @WorkerThread
    suspend fun allNews(): List<NewsItem> {
        return newsDao.getNews()
    }

    @WorkerThread
    suspend fun insert(newsItem: NewsItem) {
        newsDao.insert(newsItem)
    }

    @WorkerThread
    suspend fun insert(newsItems: List<NewsItem>) {
        newsDao.insert(newsItems)
    }

    @WorkerThread
    suspend fun deleteAll() {
        newsDao.deleteAll()
    }
}