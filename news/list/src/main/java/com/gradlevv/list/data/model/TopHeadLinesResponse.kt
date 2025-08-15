package com.gradlevv.list.data.model

import kotlinx.serialization.SerialName
import com.gradlevv.core.util.DateTimeHelper.simpleDateFormat
import com.gradlevv.list.domain.TopHeadLinesItem
import kotlinx.serialization.Serializable


@Serializable
data class TopHeadLinesResponse(
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

fun TopHeadLinesResponse.Article.toDomain(): TopHeadLinesItem {
    return TopHeadLinesItem(
        source = TopHeadLinesItem.SourceItem(
            id = source?.id ?: System.currentTimeMillis().toString(),
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

fun List<TopHeadLinesResponse.Article>.toDomain(): List<TopHeadLinesItem> {
    return this.map { it.toDomain() }
}