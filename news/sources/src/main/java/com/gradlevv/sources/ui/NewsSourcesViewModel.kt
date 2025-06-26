package com.gradlevv.sources.ui

import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Result
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.usecase.GetCategoryTypeUseCase
import com.gradlevv.sources.domain.usecase.GetSourceListUseCase
import com.gradlevv.sources.ui.state.NewsSourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourcesViewModel @Inject constructor(
    private val getSourceListUseCase: GetSourceListUseCase,
    getCategoryTypeUseCase: GetCategoryTypeUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(NewsSourceState.Empty)
    val uiState = _uiState.asStateFlow()

    private val _categoryList = MutableStateFlow<List<CategoryItem>>(listOf())
    val categoryList = _categoryList.asStateFlow()

    init {
        _categoryList.value = getCategoryTypeUseCase()
        getNewsSourceList()
    }

    private fun getNewsSourceList() {

        _uiState.value = NewsSourceState(isLoading = true)
        val type = _uiState.value.selectedCategory?.type.orEmpty()

        viewModelScope.launch {

            when (val result = getSourceListUseCase(type)) {

                is Result.Success -> {
                    _uiState.value = NewsSourceState(items = result.data ?: emptyList())
                }

                is Result.Error -> {
                    _uiState.value = NewsSourceState(isError = true)
                    errorMessage.value = result.error
                }
            }

        }

    }

    fun categoryChangeClick(item: CategoryItem) {

        if (item.type == _uiState.value.selectedCategory?.type)
            return


        _uiState.update {
            it.copy(
                selectedCategory = item
            )
        }

        _categoryList.update {
            it.map { category ->
                category.copy(
                    isChecked = when {
                        category.type == item.type -> true
                        else -> false
                    }
                )
            }
        }

        viewModelScope.launch {

            when (val result = getSourceListUseCase(type = item.type)) {

                is Result.Success -> {
                    _uiState.update { NewsSourceState(items = result.data.orEmpty()) }
                }

                is Result.Error -> {
                    _uiState.update { it.copy(isError = true) }
                    errorMessage.value = result.error
                }
            }

        }
    }
}