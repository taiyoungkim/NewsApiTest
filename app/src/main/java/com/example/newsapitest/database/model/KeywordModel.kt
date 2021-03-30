package com.example.newsapitest.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keyword_table")
data class KeywordModel(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "keyword1") val keyword1: String?,
        @ColumnInfo(name = "keyword2") val keyword2: String?,
        @ColumnInfo(name = "keyword3") val keyword3: String?,
        @ColumnInfo(name = "time") val time: Int?
)
