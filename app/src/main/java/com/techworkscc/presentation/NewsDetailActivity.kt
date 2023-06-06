package com.techworkscc.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.techworkscc.R
import com.techworkscc.domain.NewsVO

class NewsDetailActivity : AppCompatActivity() {

    companion object {
        val DETAILS_BUNDLE_DATA_LABEL = "detailsbundle"
    }

    var tvTitle: TextView? = null
    var ivCover: ImageView? = null
    var tvDescription: TextView? = null
    var tvContent: TextView? = null
    var newsData: NewsVO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_detail)
    }

    override fun onStart() {
        super.onStart()
        val source = intent.extras?.getString(DETAILS_BUNDLE_DATA_LABEL)
        newsData = Gson().fromJson(source, NewsVO::class.java)
        loadViews()
        loadDataToScreen()
    }

    private fun loadViews() {
        tvTitle = findViewById(R.id.tvTitle)
        ivCover = findViewById(R.id.ivNews)
        tvDescription = findViewById(R.id.tvDescription)
        tvContent = findViewById(R.id.tvContent)
    }

    private fun loadDataToScreen() {
        tvTitle?.text = newsData?.title
        ivCover?.let {
            Glide.with(this)
                .load(newsData?.urlToImage).into(it)
        }
        tvDescription?.text = newsData?.description
        tvContent?.text = newsData?.content
    }
}

