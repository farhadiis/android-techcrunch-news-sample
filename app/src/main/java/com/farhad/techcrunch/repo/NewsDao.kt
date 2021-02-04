package com.farhad.techcrunch.repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farhad.techcrunch.model.NewsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news ORDER BY published_at DESC")
    fun getFlowNews(): Flow<List<NewsItem>>

    @Query("SELECT * FROM news ORDER BY published_at DESC")
    suspend fun getNews(): List<NewsItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(newsItem: NewsItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(newsItems: List<NewsItem>)

    @Query("DELETE FROM news")
    suspend fun deleteAll()
}