package com.farhad.techcrunch

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farhad.techcrunch.model.NewsItem
import com.farhad.techcrunch.repo.NewsDao

@Database(entities = arrayOf(NewsItem::class), version = 1, exportSchema = false)
public abstract class TechRoomDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: TechRoomDatabase? = null

        fun getDatabase(context: Context): TechRoomDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        TechRoomDatabase::class.java,
                        "news_database"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
        }
    }
}