package com.example.newsapitest.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.newsapitest.api.model.NewsModel
import com.example.newsapitest.ui.adapter.NewsAdapter
import com.example.newsapitest.ui.adapter.ViewPagerAdapter
import com.example.newsapitest.ui.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object BindingManagers {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView : ImageView, url : String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    // 해당 클래스 내부에서 관리하는것이 나을지 아니면 별도로 관리하는게 나을지
    @JvmStatic
    @BindingAdapter("items")
    fun setBindItem(view: RecyclerView, items: LiveData<ArrayList<NewsModel>>) {
        view.adapter?.run {
            if (this is NewsAdapter) {
                if (items.value != null)
                    this.items = items.value!!
                else this.items = ArrayList<NewsModel>()

                this.notifyDataSetChanged()
            }
        }
    }

    @JvmStatic
    @BindingConversion
    fun toSimpleString(date: Date) : String {
        val format = SimpleDateFormat("yyyy/MM/dd")
        return format.format(date)
    }

    @JvmStatic
    @BindingConversion
    fun double2String(number: Double): String = "$number"
}