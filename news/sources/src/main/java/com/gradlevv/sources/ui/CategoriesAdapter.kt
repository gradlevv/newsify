package com.gradlevv.sources.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gradlevv.core.util.dp
import com.gradlevv.sources.domain.CategoryItem
import com.gradlevv.sources.ui.component.RadioThumb


class CategoriesAdapter(
    private val itemClick: (position: Int, row: CategoryItem) -> Unit
) : ListAdapter<CategoryItem, CategoriesAdapter.CategoryViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = RadioThumb(parent.context)
        view.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        ).apply {
            rightMargin = 4.dp()
            leftMargin = 4.dp()
        }
        return CategoryViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryViewHolder(
        private val view: RadioThumb,
        private val itemClick: (position: Int, row: CategoryItem) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(categoryItem: CategoryItem) {

            view.isChecked = categoryItem.isChecked

            view.setOnClickListener {
                itemClick(adapterPosition, categoryItem)
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