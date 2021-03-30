package com.example.newsapitest.api.model

import com.example.newsapitest.api.BaseApi
import java.util.*
import kotlin.collections.ArrayList

data class NewsApiModel(
    var lastBuildDate: Date,
    var total: Int,
    var start: Int,
    var display: Int,
    var items: ArrayList<NewsModel>? = null
): BaseApi()
