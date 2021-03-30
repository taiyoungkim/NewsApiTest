package com.example.newsapitest.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsapitest.api.model.NewsApiModel
import com.example.newsapitest.api.model.NewsModel
import com.example.newsapitest.database.KeywordDatabase
import com.example.newsapitest.database.model.KeywordModel
import com.example.newsapitest.retrofit.RetrofitManager
import com.example.newsapitest.retrofit.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(
    val newsList: MutableLiveData<ArrayList<NewsModel>>
) {
    var retrofit: RetrofitManager

    companion object{
        fun newInstance(
            newsList: MutableLiveData<ArrayList<NewsModel>>
        ) = NewsRepository(newsList)
    }

    init {
        Log.d(this.javaClass.name, "NewsRepository init step")
        retrofit = RetrofitService.getInstance()
    }

    fun getNewsList(query: String, display: Int, start: Int, sort: String, add: Boolean) {
        retrofit.getNews(query, display, start, sort).enqueue(object : Callback<NewsApiModel>{
            override fun onResponse(call: Call<NewsApiModel>, response: Response<NewsApiModel>) {
                val model = response.body()?.items
                if (model != null) {

                    val loading = NewsModel(" ","","","","")
                    model.add(loading)

                    if (add) {
                        newsList.value!!.removeAt(newsList.value!!.lastIndex)
                        newsList.value!!.addAll(model)
                        newsList.postValue(newsList.value)
                    }
                    else
                        newsList.postValue(model)

                    Log.d(this.javaClass.name, "NewsRepository get NewsList success $add")
                }
            }

            override fun onFailure(call: Call<NewsApiModel>, t: Throwable) {
                Log.d(this.javaClass.name, "NewsRepository get NewsList failed")
                t.stackTrace
            }
        })
    }
}