
package com.example.newsapitest.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapitest.database.model.KeywordModel
import com.example.newsapitest.util.BaseViewModel

class MainViewModel(
        private val mainRepository: MainRepository
) : BaseViewModel() {
    var keywords: LiveData<List<KeywordModel>> = mainRepository.getAllKeywords()
}