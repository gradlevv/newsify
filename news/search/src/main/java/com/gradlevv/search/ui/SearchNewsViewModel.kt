package com.gradlevv.search.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradlevv.core.data.model.Result
import com.gradlevv.search.domain.usecase.SearchNewsUseCase
import com.gradlevv.search.ui.state.SearchNewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : ViewModel() {

    private val _searchNewsList = MutableStateFlow(SearchNewsState.Empty)
    val searchNewsList = _searchNewsList.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        viewModelScope.launch {
            _searchQuery.debounce(500)
                .distinctUntilChanged()
                .collectLatest {
                    searchNews(it)
                }
        }
    }

    private fun searchNews(tag: String) {

        viewModelScope.launch {

            _searchNewsList.update { SearchNewsState(isLoading = true) }

            when (val result = searchNewsUseCase(tag = tag)) {

                is Result.Success -> {
                    _searchNewsList.update {
                        SearchNewsState(items = result.data.orEmpty())
                    }
                }

                is Result.Error -> {
                    _searchNewsList.update {
                        SearchNewsState(isError = true)
                    }
                }
            }
        }

    }

    fun setSearchValue(search: String) {
        _searchQuery.update { search }
    }

    fun resetSearchValue() {
        _searchQuery.update { "" }
    }
}