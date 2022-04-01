package com.gradlevv.search.ui

import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Resource
import com.gradlevv.core.util.Constants.DATE_FORMAT
import com.gradlevv.core.util.Constants.SORT_BY
import com.gradlevv.search.domain.SearchDomainModel
import com.gradlevv.search.domain.usecase.SearchNewsUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : BaseViewModel() {

    private val defaultSearchTag = "Android"

    init {
        searchNews(defaultSearchTag)
    }

    fun searchNews(tag: String) {

        viewModelScope.launch {

            val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)
            val date = Date()

            val request = SearchDomainModel(
                tag = tag,
                from = simpleDateFormat.format(date),
                to = simpleDateFormat.format(date),
                sortedBy = SORT_BY
            )

            when (val result = searchNewsUseCase.invoke(request = request)) {

                is Resource.Success -> {
                }

                is Resource.Error -> {

                }
            }

        }

    }
}