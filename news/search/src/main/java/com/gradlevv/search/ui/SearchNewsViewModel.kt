package com.gradlevv.search.ui

import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Resource
import com.gradlevv.core.util.Constants.DATE_FORMAT
import com.gradlevv.core.util.Constants.SORT_BY
import com.gradlevv.search.domain.SearchDomainModel
import com.gradlevv.search.domain.usecase.SearchNewsUseCase
import com.gradlevv.search.ui.state.SearchNewsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : BaseViewModel() {

    private val _searchNewsList = MutableStateFlow(SearchNewsState())
    val searchNewsList = _searchNewsList.asStateFlow()

    var searchTag = "Android"

    init {
        searchNews()
    }

    fun searchNews() {

        _searchNewsList.value = SearchNewsState(isLoading = true)

        viewModelScope.launch {

            val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)
            val date = Date()

            val request = SearchDomainModel(
                tag = searchTag,
                from = simpleDateFormat.format(date),
                to = simpleDateFormat.format(date),
                sortedBy = SORT_BY
            )

            when (val result = searchNewsUseCase(request = request)) {

                is Resource.Success -> {
                    _searchNewsList.value = SearchNewsState(items = result.data ?: arrayListOf())
                }

                is Resource.Error -> {
                    _searchNewsList.value = SearchNewsState(isError = true)
                }
            }

        }

    }
}