package com.gradlevv.search.data.model

import kotlinx.serialization.SerialName
import com.gradlevv.core.util.DateTimeHelper.simpleDateFormat
import com.gradlevv.search.domain.SearchNewsItem
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("status")
    val status: String?,
    @SerialName("articles")
    val articleList: List<Article>?
) {

    @Serializable
    data class Article(
        @SerialName("source")
        val source: Source?,
        @SerialName("author")
        val author: String?,
        @SerialName("title")
        val title: String?,
        @SerialName("description")
        val description: String?,
        @SerialName("url")
        val url: String?,
        @SerialName("urlToImage")
        val urlToImage: String?,
        @SerialName("publishedAt")
        val publishedAt: String?,
        @SerialName("content")
        val content: String?
    )

    @Serializable
    data class Source(
        @SerialName("id")
        val id: String?,
        @SerialName("name")
        val name: String?
    )
}

fun SearchResponse.Article.toDomain(): SearchNewsItem {
    return SearchNewsItem(
        source = SearchNewsItem.SourceItem(
            id = source?.id ?: "",
            name = source?.name ?: ""
        ),
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        imageUrl = urlToImage ?: "",
        publishedAt = simpleDateFormat(publishedAt ?: "") ?: "",
        content = content ?: ""
    )
}

fun List<SearchResponse.Article>.toDomain(): List<SearchNewsItem> {
    return this.map { it.toDomain() }
}