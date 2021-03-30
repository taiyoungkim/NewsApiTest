package com.example.newsapitest.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.newsapitest.database.model.KeywordModel
import com.example.newsapitest.ui.NewsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, private val keywords: List<KeywordModel?>?) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        return NewsFragment.newInstance(keywords?.get(position)?.keyword1!!)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return keywords?.get(position)?.title
    }

    override fun getCount(): Int {
        return keywords!!.size
    }
}