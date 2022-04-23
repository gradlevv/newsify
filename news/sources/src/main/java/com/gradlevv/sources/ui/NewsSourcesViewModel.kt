package com.gradlevv.sources.ui

import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Resource
import com.gradlevv.sources.domain.GetSourceListUseCase
import com.gradlevv.sources.ui.state.NewsSourceState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourcesViewModel @Inject constructor(
    private val getSourceListUseCase: GetSourceListUseCase
) : BaseViewModel() {

    private val _sourceList = MutableStateFlow(NewsSourceState())
    val sourceList = _sourceList.asStateFlow()

    init {
        getNewsSourceList()
    }

    private fun getNewsSourceList() {

        _sourceList.value = NewsSourceState(isLoading = true)

        viewModelScope.launch {

            when(val result = getSourceListUseCase()){

                is Resource.Success -> {
                    _sourceList.value = NewsSourceState(items = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _sourceList.value = NewsSourceState(isError = true)
                    errorMessage.value = result.error
                }
            }

        }

    }
}