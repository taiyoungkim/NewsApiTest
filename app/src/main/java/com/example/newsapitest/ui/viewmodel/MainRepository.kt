package com.example.newsapitest.ui.viewmodel

import android.content.Context
import com.example.newsapitest.database.KeywordDatabase
import com.example.newsapitest.database.dao.KeywordDao
import com.example.newsapitest.database.model.KeywordModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainRepository private constructor(private val keywordDao: KeywordDao){
    fun getAllKeywords() = keywordDao.getKeywords()

    val keywords = getAllKeywords()

    companion object{

        @Volatile
        private var instance: MainRepository? = null

        fun newInstance(keywordDao: KeywordDao) =
                instance ?: synchronized(this) {
                    instance ?: MainRepository(keywordDao).also { instance = it }
                }
    }

//    fun initializeDB(context: Context) : KeywordDatabase {
//        return KeywordDatabase.getInstance(context)
//    }

//    fun getAllKeyword(context: Context) : List<KeywordModel> {
//        keywordDatabase = initializeDB(context)
//        GlobalScope.launch(Dispatchers.IO) {
//            keywords = keywordDatabase!!.keywordDao().getKeywords()
//        }
//        return keywords
//    }

//    fun updateKeyword(context: Context, keywordModel: KeywordModel) {
//        keywordDatabase = initializeDB(context)
//        keywordDatabase!!.keywordDao().updateKeyword(keywordModel)
//    }
//
//    fun deleteKeyword(context: Context, keywordModel: KeywordModel) {
//        keywordDatabase = initializeDB(context)
//        keywordDatabase!!.keywordDao().deleteKeyword(keywordModel)
//    }
//
//    fun keywordInsert(context: Context, keywordModel: KeywordModel) {
//        keywordDatabase = initializeDB(context)
//        GlobalScope.launch(Dispatchers.IO) {
////            keywordDatabase!!.clearAllTables()
//            keywordDatabase!!.keywordDao().keywordInsert(keywordModel)
//        }
//    }
}