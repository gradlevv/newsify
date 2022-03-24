package com.gradlevv.core.mapper

interface BaseDataMapper<From,To> {
    fun mapTo(from: From): To
}