package com.farhad.techcrunch.viewmodel

import androidx.lifecycle.*
import com.farhad.techcrunch.model.NewsItem
import com.farhad.techcrunch.repo.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val allNews: LiveData<List<NewsItem>> = repository.allNews.asLiveData()

    fun dd() = viewModelScope.launch {
        val s = repository.getNewsObject()
    }

    fun insert(newsItem: NewsItem) = viewModelScope.launch {
        repository.insert(newsItem)
    }
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