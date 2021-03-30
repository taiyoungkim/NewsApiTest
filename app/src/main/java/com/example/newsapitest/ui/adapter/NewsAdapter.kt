package com.example.newsapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapitest.R
import com.example.newsapitest.api.model.NewsModel
import com.example.newsapitest.databinding.ItemNewsPreviewBinding
import androidx.databinding.library.baseAdapters.BR
import com.example.newsapitest.databinding.ItemLoadingBinding

class NewsAdapter(val clickListener: ItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    class ItemClickListener(val clickListener: (newsModel: NewsModel) -> Unit) {
        fun onClick(newsModel: NewsModel) = clickListener(newsModel)
    }

    var items = ArrayList<NewsModel>()
        set(value) {
            items.clear()
            items.addAll(value)
        }

    inner class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: ItemNewsPreviewBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        fun bind(item: NewsModel, clickListener: ItemClickListener) {
            binding?.setVariable(BR.newsItem, item)
            binding?.itemClick = clickListener
        }
    }

    // 아이템뷰에 프로그레스바가 들어가는 경우
    inner class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding: ItemLoadingBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)
        }

        fun bind(item: NewsModel) {
            binding?.setVariable(BR.newsItem, item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (items.get(position).title.equals(" ")) {
            return VIEW_TYPE_LOADING
        }
        return VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View

        if (viewType === VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_preview, parent, false)
            return NewsHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            return LoadingHolder(view)
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is NewsHolder) {
            holder.bind(items[position], clickListener)
        } else if(holder is LoadingHolder){

        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}