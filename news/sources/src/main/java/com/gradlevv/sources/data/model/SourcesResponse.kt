package com.gradlevv.sources.data.model

import com.google.gson.annotations.SerializedName
import com.gradlevv.sources.domain.model.SourceItem

data class SourcesResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("sources")
    val sourceList: List<SourceItemDto>?
)

data class SourceItemDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("country")
    val country: String?
)

fun SourceItemDto.toDomain(): SourceItem? {
    if (name.isNullOrEmpty() ||
        description.isNullOrEmpty() ||
        url.isNullOrEmpty() ||
        category.isNullOrEmpty() ||
        language.isNullOrEmpty() ||
        country.isNullOrEmpty()
    ) return null

    return SourceItem(
        name = name,
        description = description,
        url = url,
        category = category,
        language = language,
        country = country
    )
}

fun List<SourceItemDto>.toDomain(): List<SourceItem> {
    return this.mapNotNull { it.toDomain() }
}
