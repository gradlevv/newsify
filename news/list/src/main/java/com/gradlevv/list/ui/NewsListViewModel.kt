package com.gradlevv.list.ui

import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Resource
import com.gradlevv.list.data.source.NewsListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val repository: NewsListRepository
) : BaseViewModel() {

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() {

        viewModelScope.launch {

            when(val result = repository.getTopHeadLines()){

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