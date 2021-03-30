package com.example.newsapitest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.newsapitest.database.KeywordDatabase
import com.example.newsapitest.database.model.KeywordModel
import com.example.newsapitest.ui.adapter.ViewPagerAdapter
import com.example.newsapitest.ui.viewmodel.MainRepository
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = KeywordDatabase.getInstance(this).keywordDao()
        val repository = MainRepository.newInstance(dao)
//        var viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        repository.keywords.observe(this, { t ->
            val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, t)
            view_pager.adapter = viewPagerAdapter

            tabs.setupWithViewPager(view_pager)
        })
    }
}