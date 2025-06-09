package com.gradlevv.list.ui

import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Result
import com.gradlevv.list.domain.CategoryItem
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.list.domain.usecase.GetCategoryTypeUseCase
import com.gradlevv.list.domain.usecase.GetTopHeadLinesUseCase
import com.gradlevv.list.ui.state.TopHeadLinesState
import com.gradlevv.newsify.core.R
import com.gradlevv.ui.utils.navOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getTopHeadLinesUseCase: GetTopHeadLinesUseCase,
    getCategoryTypeUseCase: GetCategoryTypeUseCase
) : BaseViewModel() {

    private val _topHeadLinesList = MutableStateFlow(TopHeadLinesState.Empty)
    val topHeadLinesList = _topHeadLinesList.asStateFlow()

    private val _newsDetailItem = MutableStateFlow<TopHeadLinesItem?>(null)
    val newsDetailItem = _newsDetailItem.asStateFlow()

    private val _categoryList = MutableStateFlow<List<CategoryItem>>(listOf())
    val categoryList = _categoryList.asStateFlow()

    private var category = "general"


    init {

        _categoryList.value = getCategoryTypeUseCase()
        getTopHeadlines()
    }

    private fun getTopHeadlines() {

        _topHeadLinesList.update { TopHeadLinesState(isLoading = true) }
        viewModelScope.launch {

            when (val result = getTopHeadLinesUseCase(category)) {

                is Result.Success -> {
                    _topHeadLinesList.update {
                        TopHeadLinesState(
                            items = result.data ?: emptyList()
                        )
                    }
                }

                is Result.Error -> {
                    _topHeadLinesList.update { TopHeadLinesState(isError = true) }
                    errorMessage.value = result.error
                }
            }

        }

    }

    fun navigateToNewsDetail(topHeadLinesItem: TopHeadLinesItem) {
        _newsDetailItem.update { topHeadLinesItem }
        navigate(R.string.news_detail_fragment, navOptions)
    }

    fun categoryChangeClick(selectedCategory: CategoryItem) {
        category = selectedCategory.type
        getTopHeadlines()
    }
}