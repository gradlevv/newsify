package com.gradlevv.list.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Resource
import com.gradlevv.list.R
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.list.domain.usecase.GetTopHeadLinesUseCase
import com.gradlevv.list.ui.state.TopHeadLinesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val getTopHeadLinesUseCase: GetTopHeadLinesUseCase
) : BaseViewModel() {

    private val _topHeadLinesList = MutableStateFlow(TopHeadLinesState())
    val topHeadLinesList = _topHeadLinesList.asStateFlow()

    private val _newsDetailItem = MutableStateFlow<TopHeadLinesItem?>(null)
    val newsDetailItem = _newsDetailItem.asStateFlow()

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {

        _topHeadLinesList.value = TopHeadLinesState(isLoading = true)
        viewModelScope.launch {

            when (val result = getTopHeadLinesUseCase()) {

                is Resource.Success -> {
                    _topHeadLinesList.value = TopHeadLinesState(items = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _topHeadLinesList.value = TopHeadLinesState(isError = true)
                }
            }

        }

    }

    fun navigateToNewsDetail(topHeadLinesItem: TopHeadLinesItem) {
        _newsDetailItem.value = topHeadLinesItem
        navigate(R.string.news_detail_fragment)
    }
}