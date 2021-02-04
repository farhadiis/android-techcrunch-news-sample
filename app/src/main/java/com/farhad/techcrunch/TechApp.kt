package com.farhad.techcrunch

import android.app.Application
import com.farhad.techcrunch.repo.NewsRepository

class TechApp : Application() {

    /*val applicationScope = CoroutineScope(SupervisorJob())*/
    private val apiInterface by lazy { RetrofitClient.apiInterface }
    private val database by lazy { TechRoomDatabase.getDatabase(this) }
    val repository by lazy { NewsRepository(database.newsDao(), apiInterface) }

    companion object {
        lateinit var instance: TechApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}