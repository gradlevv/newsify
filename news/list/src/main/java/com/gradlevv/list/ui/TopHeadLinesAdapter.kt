package com.gradlevv.list.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.util.dp
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.list.ui.component.TopHeadLineRowView

class TopHeadLinesAdapter(
    private val itemClick: (position: Int, row: TopHeadLinesItem) -> Unit
) :
    ListAdapter<TopHeadLinesItem, TopHeadLinesAdapter.TopHeadLinesViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadLinesViewHolder {
        val view = TopHeadLineRowView(parent.context)
        view.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        ).apply {
            topMargin = 8.dp()
            rightMargin = 8.dp()
            leftMargin = 8.dp()
        }
        return TopHeadLinesViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: TopHeadLinesViewHolder, position: Int) {
        holder.view.bind(getItem(position))
    }

    class TopHeadLinesViewHolder(
        val view: TopHeadLineRowView,
        private val itemClick: (position: Int, row: TopHeadLinesItem) -> Unit
    ) : RecyclerView.ViewHolder(view) {


        fun bind(row: TopHeadLinesItem) {
            view.bind(row)

            view.setOnClickListener {
                itemClick(adapterPosition, row)
            }
        }

    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<TopHeadLinesItem>() {
            override fun areItemsTheSame(
                oldItem: TopHeadLinesItem,
                newItem: TopHeadLinesItem
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: TopHeadLinesItem,
                newItem: TopHeadLinesItem
            ): Boolean {
                return oldItem == newItem
            }
        }

    }
}
