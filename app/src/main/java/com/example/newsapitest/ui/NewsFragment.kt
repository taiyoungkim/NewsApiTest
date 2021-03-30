package com.example.newsapitest.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapitest.R
import com.example.newsapitest.databinding.FragmentNewsBinding
import com.example.newsapitest.ui.adapter.NewsAdapter
import com.example.newsapitest.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {

    init {
        lifecycleScope.launch {
            whenStarted {
                Log.d(this.javaClass.name, "NewsFragment init setp~~~~(started)")
            }

            whenResumed {
                Log.d(this.javaClass.name, "NewsFragment init setp~~~~(resumed)")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(selectedKeywords: String): NewsFragment {
            return NewsFragment().apply {
                arguments = Bundle().apply {
                    putString("selected_keyword", selectedKeywords)
                }
            }
        }
    }

    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(this.javaClass.name, "NewsFragment onCreateView step")

        val binding: FragmentNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java).apply {
            getQuery(arguments?.getString("selected_keyword")!!)
        }
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

//        viewModel.getKeywords(this.requireContext())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            Log.d(this.javaClass.name, "NewsFragment onActivityCreated step")
            val adapter = NewsAdapter(NewsAdapter.ItemClickListener { newsModel ->
                Toast.makeText(context, "${newsModel.originallink}", Toast.LENGTH_LONG).show()
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(newsModel.originallink)
                startActivity(i)
            })

            adapter.setHasStableIds(true)
            rvItems.adapter = adapter

            // add
            rvItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (!rvItems.canScrollVertically(1)) {
                        viewModel.addQuery(arguments?.getString("selected_keyword")!!)
                    }
                }
            })
        }
    }
}