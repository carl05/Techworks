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

class HeadLinesAdapter( private var data: List<NewsVO> = emptyList()): RecyclerView.Adapter<HeadLinesAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvTitle: TextView
            val ivCover: ImageView

            init {
                tvTitle = view.findViewById(R.id.tvTitle)
                ivCover = view.findViewById(R.id.ivNews)
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.news_head_item, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.tvTitle.text = data[position].title
            Glide.with(viewHolder.ivCover.context)
                .load(data[position].imageCover).into(viewHolder.ivCover)

        }
        override fun getItemCount() = data.size

    }
