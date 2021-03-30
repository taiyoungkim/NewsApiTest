package com.example.newsapitest.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.newsapitest.api.model.NewsModel
import com.example.newsapitest.util.BaseViewModel
import kotlinx.coroutines.*

class NewsViewModel : BaseViewModel() {
    private var _newsList = SearchNewsLiveData<ArrayList<NewsModel>>()

    val repo = NewsRepository.newInstance(_newsList)

    var newsList: LiveData<ArrayList<NewsModel>> = _newsList

    var start = 1;

    init {
        viewModelScope.launch {
            Log.d(this.javaClass.name, "NewsViewModel >> init setp~~~~")
        }
    }

    fun getQuery(query: String) {
        if (query.isNotEmpty()) {
            repo.getNewsList(query, 10, start, "date", false)
        }
    }

    fun addQuery(query: String) {
        if (query.isNotEmpty()) {
            repo.getNewsList(query, 10, start * 10 + 1, "date", true)
//            val handler = android.os.Handler()
//            handler.postDelayed({
//                notifyChange()
                start++
//            }, 1000)
        }
    }

    inner class SearchNewsLiveData<T>() : MutableLiveData<T>() {
        override fun onActive() {
            super.onActive()

        }
    }
}