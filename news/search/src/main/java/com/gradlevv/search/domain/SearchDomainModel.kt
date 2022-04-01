package com.gradlevv.search.domain

data class SearchDomainModel(
    val tag: String,
    val from: String,
    val to: String,
    val sortedBy: String
)