package com.farhad.techcrunch

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.farhad.techcrunch.viewmodel.NewsViewModel
import com.farhad.techcrunch.viewmodel.NewsViewModelFactory

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory((application as TechApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsViewModel.allNews.observe(this) { news ->
            news.let { Log.d("ewdewd", "" + it.size + "") }
        }
        newsViewModel.dd()
    }
}