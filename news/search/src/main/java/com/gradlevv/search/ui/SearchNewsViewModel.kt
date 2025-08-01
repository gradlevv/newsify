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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : ViewModel() {

    private val _searchNewsList = MutableStateFlow(SearchNewsState.Empty)
    val searchNewsList = _searchNewsList.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        searchNews()
    }

    @OptIn(FlowPreview::class)
    private fun searchNews() {
        viewModelScope.launch {
            _searchQuery.debounce(500)
                .onEach { search ->
                    val job = launch {

                        _searchNewsList.value = SearchNewsState(isLoading = true)

                        when (val result = searchNewsUseCase(tag = search)) {

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

                    job.join()

                }.collect()
        }
    }

    fun setSearchValue(search: String) {
        _searchQuery.update { search }
    }

    fun resetSearchValue() {
        _searchQuery.update { "" }
    }
}