package com.gradlevv.sources.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.util.dp
import com.gradlevv.sources.domain.SourceItemDomainModel
import com.gradlevv.sources.ui.component.NewsSourceItemView

class NewsSourcesAdapter(
    private val itemClick: (position: Int, item: SourceItemDomainModel) -> Unit
) : ListAdapter<SourceItemDomainModel, NewsSourcesAdapter.NewsSourceViewHolder>(
    DIFF_UTIL
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder {
        val view = NewsSourceItemView(context = parent.context)
        view.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            64.dp()
        ).apply {
            topMargin = 8.dp()
        }
        return NewsSourceViewHolder(view = view, itemClick = itemClick)
    }

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsSourceViewHolder(
        private val view: NewsSourceItemView,
        private val itemClick: (position: Int, item: SourceItemDomainModel) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: SourceItemDomainModel) {
            view.setValues(item)
            view.setOnClickListener {
                itemClick(adapterPosition, item)
            }
        }

    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<SourceItemDomainModel>() {
            override fun areItemsTheSame(
                oldItem: SourceItemDomainModel,
                newItem: SourceItemDomainModel
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: SourceItemDomainModel,
                newItem: SourceItemDomainModel
            ): Boolean {
                return oldItem == newItem
            }
        }

    }
}