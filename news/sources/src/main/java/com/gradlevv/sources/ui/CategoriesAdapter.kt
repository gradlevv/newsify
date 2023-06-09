package com.gradlevv.sources.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.util.dp
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.ui.component.CategoryItemView


class CategoriesAdapter(
    private val itemClick: (position: Int, row: CategoryItem) -> Unit
) : ListAdapter<CategoryItem, CategoriesAdapter.CategoryViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = CategoryItemView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            ).apply {
                rightMargin = 4.dp()
                leftMargin = 4.dp()
            }
        }
        return CategoryViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryViewHolder(
        private val view: CategoryItemView,
        private val itemClick: (position: Int, row: CategoryItem) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: CategoryItem) {

            view.setValue(item)
            view.setOnClickListener {
                itemClick(adapterPosition, item)
            }
        }
    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<CategoryItem>() {
            override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
                return oldItem.type == newItem.type
            }

            override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
                return oldItem == newItem
            }
        }

    }
}