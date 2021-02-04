package com.farhad.techcrunch

import android.app.Application
import com.farhad.techcrunch.repo.NewsRepository

class TechApp : Application() {

    /*val applicationScope = CoroutineScope(SupervisorJob())*/
    val apiInterface by lazy { RetrofitClient.apiInterface }
    val database by lazy { TechRoomDatabase.getDatabase(this) }
    val repository by lazy { NewsRepository(database.newsDao(), apiInterface) }

    override fun onCreate() {
        super.onCreate()
    }
}