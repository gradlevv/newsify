package com.gradlevv.sources.data.model

import com.gradlevv.newsify.news.sources.R

sealed class CategoryType(
    val type: Int,
    val categoryName: Int
) {
    object Business : CategoryType(
        type = R.string.sources_business_type,
        categoryName = R.string.sources_business_name
    )

    object Entertainment : CategoryType(
        type = R.string.sources_entertainment_type,
        categoryName = R.string.sources_entertainment_name
    )

    object General : CategoryType(
        type = R.string.sources_general_type,
        categoryName = R.string.sources_general_name
    )

    object Health : CategoryType(
        type = R.string.sources_health_type,
        categoryName = R.string.sources_health_name
    )

    object Science : CategoryType(
        type = R.string.sources_science_type,
        categoryName = R.string.sources_science_name
    )

    object Sports : CategoryType(
        type = R.string.sources_sports_type,
        categoryName = R.string.sources_sports_name
    )

    object Technology : CategoryType(
        type = R.string.sources_technology_type,
        categoryName = R.string.sources_technology_name
    )

}