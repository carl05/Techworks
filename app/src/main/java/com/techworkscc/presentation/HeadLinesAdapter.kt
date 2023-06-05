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

    /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder)
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvTitle: TextView
            val ivCover: ImageView

            init {
                // Define click listener for the ViewHolder's View
                tvTitle = view.findViewById(R.id.tvTitle)
                ivCover = view.findViewById(R.id.ivNews)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.news_head_item, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.tvTitle.text = data[position].title

            Glide.with(viewHolder.ivCover.context)
                .load(data[position].imageCover).into(viewHolder.ivCover)

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = data.size

    }
