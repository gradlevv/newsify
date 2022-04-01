package com.gradlevv.search.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.util.dp
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.ui.component.SearchNewsRowView

class SearchNewsAdapter(
    private val itemClick: (position: Int, row: SearchNewsItem) -> Unit
) :
    ListAdapter<SearchNewsItem, SearchNewsAdapter.SearchNewsViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNewsViewHolder {
        val view = SearchNewsRowView(parent.context)
        view.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        ).apply {
            topMargin = 8.dp()
            rightMargin = 8.dp()
            leftMargin = 8.dp()
        }
        return SearchNewsViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: SearchNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchNewsViewHolder(
        val view: SearchNewsRowView,
        private val itemClick: (position: Int, row: SearchNewsItem) -> Unit
    ) : RecyclerView.ViewHolder(view) {


        fun bind(row: SearchNewsItem) {
            view.bind(row)

            view.setOnClickListener {
                itemClick(adapterPosition, row)
            }
        }

    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<SearchNewsItem>() {
            override fun areItemsTheSame(
                oldItem: SearchNewsItem,
                newItem: SearchNewsItem
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: SearchNewsItem,
                newItem: SearchNewsItem
            ): Boolean {
                return oldItem == newItem
            }
        }

    }
}
