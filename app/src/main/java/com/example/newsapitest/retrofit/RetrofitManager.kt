package com.example.newsapitest.retrofit

import com.example.newsapitest.api.model.NewsApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitManager {
    @GET("search/news")
    fun getNews(
            @Query("query") query: String,
            @Query("display") display: Int,
            @Query("start") start: Int,
            @Query("sort") sort: String
    ): Call<NewsApiModel>
}