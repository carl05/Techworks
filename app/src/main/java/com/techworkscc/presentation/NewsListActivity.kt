package com.techworkscc.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.techworkscc.R
import com.techworkscc.domain.NewsVO
import com.techworkscc.presentation.NewsDetailActivity.Companion.DETAILS_BUNDLE_DATA_LABEL
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListActivity : AppCompatActivity() {

    val newsViewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_list)
        title = ""
    }

    override fun onStart() {
        super.onStart()
        val recyclerView: RecyclerView = findViewById(R.id.rv_news)
        recyclerView.layoutManager = LinearLayoutManager(this)
        newsViewModel.newsLivewData.observe(this) {
            title = it.listNewsVO.firstOrNull()?.sourceName
            val newsAdapter = NewsAdapter(it.listNewsVO){
                goToDetailsActivity(it)
            }
            recyclerView.adapter = newsAdapter
        }
        newsViewModel.loadNews()
    }

    private fun goToDetailsActivity(newsVO: NewsVO) {
        val intent = Intent(this, NewsDetailActivity::class.java).apply {
            putExtra(DETAILS_BUNDLE_DATA_LABEL, Gson().toJson(newsVO))
        }
        startActivity(intent)
    }
}

