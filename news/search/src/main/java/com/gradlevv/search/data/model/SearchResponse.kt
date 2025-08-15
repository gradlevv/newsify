package com.gradlevv.search.data.model

import com.google.gson.annotations.SerializedName
import com.gradlevv.core.util.DateTimeHelper.simpleDateFormat
import com.gradlevv.search.domain.SearchNewsItem


data class SearchResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("articles")
    val articleList: List<Article>?
) {
    data class Article(
        @SerializedName("source")
        val source: Source?,
        @SerializedName("author")
        val author: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("urlToImage")
        val urlToImage: String?,
        @SerializedName("publishedAt")
        val publishedAt: String?,
        @SerializedName("content")
        val content: String?
    )

    data class Source(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
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