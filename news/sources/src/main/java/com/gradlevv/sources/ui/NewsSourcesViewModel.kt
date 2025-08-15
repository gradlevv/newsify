package com.gradlevv.sources.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradlevv.core.data.model.Result
import com.gradlevv.core.util.IntentUtils
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.model.SourceItem
import com.gradlevv.sources.domain.usecase.GetCategoryTypeUseCase
import com.gradlevv.sources.domain.usecase.GetSourceListUseCase
import com.gradlevv.sources.domain.usecase.SourceTag
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
    private val intentUtils: IntentUtils,
    getCategoryTypeUseCase: GetCategoryTypeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsSourceState.Empty)
    val uiState = _uiState.asStateFlow()

    private val _categoryList = MutableStateFlow(getCategoryTypeUseCase())
    val categoryList = _categoryList.asStateFlow()

    init {
        getNewsSourceList()
    }

    private fun getNewsSourceList() {

        _uiState.update {
            it.copy(
                isLoading = true,
                isError = false
            )
        }
        val type = _uiState.value.selectedCategory?.type.orEmpty()
        fetchNewsSource(type)
    }

    fun categoryChangeClick(item: CategoryItem) {

        if (item.type == _uiState.value.selectedCategory?.type)
            return


        _uiState.update {
            it.copy(
                selectedCategory = item,
                isError = false
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

        fetchNewsSource(type = item.type)

    }

    private fun fetchNewsSource(type: String) {

        viewModelScope.launch {

            when (val result = getSourceListUseCase(SourceTag(type))) {

                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = false,
                            items = result.data.orEmpty()
                        )
                    }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isError = true,
                            isLoading = false
                        )
                    }
                }
            }

        }
    }

    fun openWebsite(item: SourceItem) {
        intentUtils.openLinkInDeviceBrowser(item.url)
    }
}