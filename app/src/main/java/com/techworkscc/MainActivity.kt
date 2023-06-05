package com.techworkscc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techworkscc.presentation.HeadLinesAdapter
import com.techworkscc.presentation.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val newsViewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    override fun onStart() {
        super.onStart()
        val recyclerView: RecyclerView = findViewById(R.id.rv_news)
        recyclerView.layoutManager = LinearLayoutManager(this)
        newsViewModel.newsLivewData.observe(this) {
            val newsAdapter = HeadLinesAdapter(it.listNewsVO)
            recyclerView.adapter = newsAdapter
        }
        newsViewModel.loadNews()
    }
}

