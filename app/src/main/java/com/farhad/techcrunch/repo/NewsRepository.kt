package com.farhad.techcrunch.repo

import androidx.annotation.WorkerThread
import com.farhad.techcrunch.api.ApiInterface
import com.farhad.techcrunch.model.NewsItem
import com.farhad.techcrunch.model.NewsObject
import kotlinx.coroutines.flow.Flow


class NewsRepository(private val newsDao: NewsDao, private val apiInterface: ApiInterface) {

    val allNews: Flow<List<NewsItem>> = newsDao.getNews()

    @WorkerThread
    suspend fun getNewsObject(): NewsObject {
        return apiInterface.getTechCrunchNews()
    }

    @WorkerThread
    suspend fun insert(newsItem: NewsItem) {
        newsDao.insert(newsItem)
    }
}