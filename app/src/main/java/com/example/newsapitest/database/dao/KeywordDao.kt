package com.example.newsapitest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.newsapitest.database.model.KeywordModel

@Dao
interface KeywordDao {

    @Query("SELECT * FROM keyword_table ORDER BY id ASC")
    fun getKeywords() : LiveData<List<KeywordModel>>

    @Insert(onConflict = REPLACE)
    fun keywordInsert(keywordModel: KeywordModel)

    @Delete
    fun deleteKeyword (keywordModel: KeywordModel)

    @Update
    fun updateKeyword (keywordModel: KeywordModel)
}