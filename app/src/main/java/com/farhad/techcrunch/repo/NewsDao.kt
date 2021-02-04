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
    fun getNews(): Flow<List<NewsItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(newsItem: NewsItem)

    @Query("DELETE FROM news")
    suspend fun deleteAll()
}