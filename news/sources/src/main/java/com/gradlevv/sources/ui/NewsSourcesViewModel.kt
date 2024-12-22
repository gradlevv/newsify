package com.gradlevv.sources.ui

import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Result
import com.gradlevv.sources.domain.usecase.GetCategoryTypeUseCase
import com.gradlevv.sources.domain.usecase.GetSourceListUseCase
import com.gradlevv.sources.ui.state.NewsSourceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourcesViewModel @Inject constructor(
    private val getSourceListUseCase: GetSourceListUseCase,
    getCategoryTypeUseCase: GetCategoryTypeUseCase
) : BaseViewModel() {

    private val _sourceList = MutableStateFlow(NewsSourceState.Empty)
    val sourceList = _sourceList.asStateFlow()

    val categoryList = getCategoryTypeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    init {
        getNewsSourceList()
    }

    private fun getNewsSourceList() {

        _sourceList.value = NewsSourceState(isLoading = true)

        viewModelScope.launch {

            when (val result = getSourceListUseCase("")) {

                is Result.Success -> {
                    _sourceList.value = NewsSourceState(items = result.data ?: emptyList())
                }

                is Result.Error -> {
                    _sourceList.value = NewsSourceState(isError = true)
                    errorMessage.value = result.error
                }
            }

        }

    }

    fun categoryChangeClick(type: String) {

        _sourceList.update { it.copy(isLoading = true) }

        viewModelScope.launch {

            when (val result = getSourceListUseCase(type = type)) {

                is Result.Success -> {
                    _sourceList.update { NewsSourceState(items = result.data.orEmpty()) }
                }

                is Result.Error -> {
                    _sourceList.update { it.copy(isError = true) }
                    errorMessage.value = result.error
                }
            }

        }
    }
}