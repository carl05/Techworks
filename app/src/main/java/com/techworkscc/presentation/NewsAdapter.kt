package com.techworkscc.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techworkscc.R
import com.techworkscc.domain.NewsVO

class NewsAdapter(private var data: List<NewsVO> = emptyList(), val listener: (NewsVO) -> Unit): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

        class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvTitle: TextView
            val ivCover: ImageView

            init {
                tvTitle = view.findViewById(R.id.tvTitle)
                ivCover = view.findViewById(R.id.ivNews)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewsViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.news_list_item, viewGroup, false)

            return NewsViewHolder(view)
        }

        override fun onBindViewHolder(newsViewHolder: NewsViewHolder, position: Int) {
            newsViewHolder.tvTitle.text = data[position].title

            Glide.with(newsViewHolder.ivCover.context)
                .load(data[position].urlToImage).into(newsViewHolder.ivCover)

            newsViewHolder.tvTitle.setOnClickListener { onClickAction(data[position]) }
            newsViewHolder.ivCover.setOnClickListener { onClickAction(data[position]) }

        }
        fun onClickAction(newsVO: NewsVO){
            listener.invoke(newsVO)
        }
        override fun getItemCount() = data.size

    }
