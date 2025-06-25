package com.gradlevv.sources.domain.model

data class CategoryItem(
    val type: String,
    val categoryName: Int,
    val isChecked: Boolean = false
)