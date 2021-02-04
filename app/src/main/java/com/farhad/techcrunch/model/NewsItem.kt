package com.farhad.techcrunch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "news")
data class NewsItem(
    @PrimaryKey
    @ColumnInfo(name = "uid") val uid: Int = Random().nextInt(),
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "author") val author: String? = null,
    @ColumnInfo(name = "url") val url: String? = null,
    @ColumnInfo(name = "image_url") val urlToImage: String? = null,
    @ColumnInfo(name = "published_at") val publishedAt: String? = null,
    @ColumnInfo(name = "content") val content: String? = null,
    @ColumnInfo(name = "description") val description: String? = null
)