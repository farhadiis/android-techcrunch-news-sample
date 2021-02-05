package com.farhad.techcrunch.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.farhad.techcrunch.model.NewsItem
import com.farhad.techcrunch.model.NewsObject
import com.farhad.techcrunch.repo.NewsRepository
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val newsStateObserver: Observer<NewsStatus> = mock()
    private lateinit var viewModel: NewsViewModel


    @Test
    fun localNewsAvailableTest() {
        val localNews = arrayListOf(NewsItem(), NewsItem())
        val remoteNews = NewsObject(status = "ok", totalResults = 0, articles =  arrayListOf())
        val repoMock: NewsRepository = mock {
            onGeneric { runBlocking { allNews() } } doReturn localNews
            onGeneric { runBlocking { remoteNews() } } doReturn remoteNews
        }
        viewModel = NewsViewModel(repoMock)
        viewModel.news.observeForever(newsStateObserver)
        val status = viewModel.news.getOrAwaitValue()
        if (status is NewsStatus.Success) {
            Assert.assertEquals(localNews, status.news)
        } else {
            throw Exception("is not success")
        }
    }

    @Test
    fun localNewsUnavailableTest() {
        val localNews = arrayListOf<NewsItem>()
        val remoteNews = NewsObject(status = "ok", totalResults = 2, articles =  arrayListOf(NewsItem(), NewsItem()))
        val repoMock: NewsRepository = mock {
            onGeneric { runBlocking { allNews() } } doReturn localNews
            onGeneric { runBlocking { remoteNews() } } doReturn remoteNews
        }
        viewModel = NewsViewModel(repoMock)
        viewModel.news.observeForever(newsStateObserver)
        var status = viewModel.news.getOrAwaitValue()
        Assert.assertTrue(status is NewsStatus.Loading)
        Thread.sleep(1000)
        status = viewModel.news.getOrAwaitValue()
        Assert.assertTrue(status is NewsStatus.Success)
    }
}