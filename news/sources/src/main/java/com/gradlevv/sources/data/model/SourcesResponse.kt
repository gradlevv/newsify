package com.gradlevv.sources.data.model

import kotlinx.serialization.SerialName
import com.gradlevv.sources.domain.model.SourceItem
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    @SerialName("status")
    val status: String?,
    @SerialName("sources")
    val sourceList: List<SourceItemDto>?
)

@Serializable
data class SourceItemDto(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("category")
    val category: String?,
    @SerialName("language")
    val language: String?,
    @SerialName("country")
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
