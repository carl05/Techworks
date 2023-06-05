package com.techworkscc

import android.os.Bundle
import android.widget.Toast
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
        val recyclerView: RecyclerView = findViewById(R.id.rv_news)

        newsViewModel.newsLivewData.observe(this) {
            val newsAdapter = HeadLinesAdapter(it.listNewsVO)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = newsAdapter
        }
        newsViewModel.loadNews()
    }
}

