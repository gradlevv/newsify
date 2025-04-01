package com.gradlevv.list.data.model

import androidx.annotation.DrawableRes
import com.gradlevv.newsify.ui.R

sealed class CategoryType(
    @DrawableRes
    val icon: Int,
    val type: Int,
    val categoryName: Int
) {
    object Business : CategoryType(
        icon = R.drawable.ic_business,
        type = com.gradlevv.newsify.news.list.R.string.news_list_business_type,
        categoryName = com.gradlevv.newsify.news.list.R.string.news_list_business_name
    )

    object Entertainment : CategoryType(
        icon = R.drawable.ic_entertainment,
        type = com.gradlevv.newsify.news.list.R.string.news_list_entertainment_type,
        categoryName = com.gradlevv.newsify.news.list.R.string.news_list_entertainment_name
    )

    object General : CategoryType(
        icon = R.drawable.ic_general,
        type = com.gradlevv.newsify.news.list.R.string.news_list_general_type,
        categoryName = com.gradlevv.newsify.news.list.R.string.news_list_general_name
    )

    object Health : CategoryType(
        icon = R.drawable.ic_health,
        type = com.gradlevv.newsify.news.list.R.string.news_list_health_type,
        categoryName = com.gradlevv.newsify.news.list.R.string.news_list_health_name
    )

    object Science : CategoryType(
        icon = R.drawable.ic_science,
        type = com.gradlevv.newsify.news.list.R.string.news_list_science_type,
        categoryName = com.gradlevv.newsify.news.list.R.string.news_list_science_name
    )

    object Sports : CategoryType(
        icon = R.drawable.ic_sports,
        type = com.gradlevv.newsify.news.list.R.string.news_list_sports_type,
        categoryName = com.gradlevv.newsify.news.list.R.string.news_list_sports_name
    )

    object Technology : CategoryType(
        icon = R.drawable.ic_technology,
        type = com.gradlevv.newsify.news.list.R.string.news_list_technology_type,
        categoryName = com.gradlevv.newsify.news.list.R.string.news_list_technology_name
    )

}