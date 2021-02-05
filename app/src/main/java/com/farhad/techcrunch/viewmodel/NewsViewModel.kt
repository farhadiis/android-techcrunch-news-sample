package com.farhad.techcrunch.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.farhad.techcrunch.model.NewsItem
import com.farhad.techcrunch.repo.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val news: LiveData<NewsStatus> by lazy {
        val liveData = MutableLiveData<NewsStatus>()
        viewModelScope.launch {
            val news = repository.allNews()
            liveData.value = if (news.isEmpty()) NewsStatus.Loading else NewsStatus.Success(news)
            try {
                val remoteNews = repository.remoteNews()
                if (remoteNews.articles.isNotEmpty()) {
                    repository.deleteAll()
                    repository.insert(remoteNews.articles)
                    liveData.value = NewsStatus.Success(remoteNews.articles)
                } else {
                    throw Exception("remote news is empty.")
                }
            } catch (e: Exception) {
                Log.e("NewsViewModel", "Fetch", e)
                if (news.isEmpty()) {
                    liveData.value = NewsStatus.Error(e.localizedMessage ?: "Error")
                }
            }
        }
        return@lazy liveData
    }
}

sealed class NewsStatus {
    object Loading : NewsStatus()
    class Success(val news: List<NewsItem>) : NewsStatus()
    class Error(val error: String) : NewsStatus()
}

class NewsViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}