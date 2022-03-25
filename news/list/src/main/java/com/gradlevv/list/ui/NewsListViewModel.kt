package com.gradlevv.list.ui

import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Resource
import com.gradlevv.list.data.TopHeadLinesMapper
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

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {

        viewModelScope.launch {

            when(val result = getTopHeadLinesUseCase()){

                is Resource.Success -> {
                   if (result.data != null){

                   }
                }

                is Resource.Error -> {

                }
            }

        }

    }
}