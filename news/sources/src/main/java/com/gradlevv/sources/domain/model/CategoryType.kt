package com.gradlevv.sources.domain.model

import com.gradlevv.newsify.news.sources.R

sealed class CategoryType(
    val type: String,
    val categoryName: Int
) {
    object Business : CategoryType(
        type = "business",
        categoryName = R.string.sources_business_name
    )

    object Entertainment : CategoryType(
        type = "entertainment",
        categoryName = R.string.sources_entertainment_name
    )

    object General : CategoryType(
        type = "general",
        categoryName = R.string.sources_general_name
    )

    object Health : CategoryType(
        type = "health",
        categoryName = R.string.sources_health_name
    )

    object Science : CategoryType(
        type = "science",
        categoryName = R.string.sources_science_name
    )

    object Sports : CategoryType(
        type = "sports",
        categoryName = R.string.sources_sports_name
    )

    object Technology : CategoryType(
        type = "technology",
        categoryName = R.string.sources_technology_name
    )

}