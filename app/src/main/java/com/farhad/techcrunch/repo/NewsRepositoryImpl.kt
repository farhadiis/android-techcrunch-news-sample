package com.farhad.techcrunch.repo

import androidx.annotation.WorkerThread
import com.farhad.techcrunch.api.ApiInterface
import com.farhad.techcrunch.model.NewsItem
import com.farhad.techcrunch.model.NewsObject
import kotlinx.coroutines.flow.Flow


class NewsRepositoryImpl(private val newsDao: NewsDao, private val apiInterface: ApiInterface) :
    NewsRepository {

    override val allFlowNews: Flow<List<NewsItem>> = newsDao.getFlowNews()

    @WorkerThread
    override suspend fun remoteNews(): NewsObject {
        return apiInterface.getTechCrunchNews()
    }

    @WorkerThread
    override suspend fun allNews(): List<NewsItem> {
        return newsDao.getNews()
    }

    @WorkerThread
    override suspend fun insert(newsItem: NewsItem) {
        newsDao.insert(newsItem)
    }

    @WorkerThread
    override suspend fun insert(newsItems: List<NewsItem>) {
        newsDao.insert(newsItems)
    }

    @WorkerThread
    override suspend fun deleteAll() {
        newsDao.deleteAll()
    }
}