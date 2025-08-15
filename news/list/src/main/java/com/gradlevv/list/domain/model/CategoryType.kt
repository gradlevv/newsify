package com.gradlevv.list.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gradlevv.newsify.ui.R

sealed class CategoryType(
    @DrawableRes
    val icon: Int,
    val type: String,
    @StringRes
    val categoryLabelRes: Int
) {
    object Business : CategoryType(
        icon = R.drawable.ic_business,
        type = "business",
        categoryLabelRes = com.gradlevv.newsify.news.list.R.string.news_list_business_name
    )

    object Entertainment : CategoryType(
        icon = R.drawable.ic_entertainment,
        type = "entertainment",
        categoryLabelRes = com.gradlevv.newsify.news.list.R.string.news_list_entertainment_name
    )

    object General : CategoryType(
        icon = R.drawable.ic_general,
        type = "general",
        categoryLabelRes = com.gradlevv.newsify.news.list.R.string.news_list_general_name
    )

    object Health : CategoryType(
        icon = R.drawable.ic_health,
        type = "health",
        categoryLabelRes = com.gradlevv.newsify.news.list.R.string.news_list_health_name
    )

    object Science : CategoryType(
        icon = R.drawable.ic_science,
        type = "science",
        categoryLabelRes = com.gradlevv.newsify.news.list.R.string.news_list_science_name
    )

    object Sports : CategoryType(
        icon = R.drawable.ic_sports,
        type = "sports",
        categoryLabelRes = com.gradlevv.newsify.news.list.R.string.news_list_sports_name
    )

    object Technology : CategoryType(
        icon = R.drawable.ic_technology,
        type = "technology",
        categoryLabelRes = com.gradlevv.newsify.news.list.R.string.news_list_technology_name
    )

}