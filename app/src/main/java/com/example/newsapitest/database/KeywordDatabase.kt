package com.example.newsapitest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.newsapitest.database.dao.KeywordDao
import com.example.newsapitest.database.model.KeywordModel

@Database(entities = [KeywordModel::class], version = 1, exportSchema = false)
abstract class KeywordDatabase : RoomDatabase() {

    abstract fun keywordDao(): KeywordDao

    companion object {
        val DB_NAME = "keyword-db"

        private var instance: KeywordDatabase? = null

        fun getInstance(context: Context): KeywordDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): KeywordDatabase {
            return Room.databaseBuilder(context.applicationContext, KeywordDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }
    }
}