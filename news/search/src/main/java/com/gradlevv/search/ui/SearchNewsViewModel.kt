package com.gradlevv.search.ui

import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Result
import com.gradlevv.core.util.Constants.DATE_FORMAT
import com.gradlevv.core.util.Constants.SORT_BY
import com.gradlevv.search.domain.SearchDomainModel
import com.gradlevv.search.domain.usecase.SearchNewsUseCase
import com.gradlevv.search.ui.state.SearchNewsState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : BaseViewModel() {

    private val _searchNewsList = MutableStateFlow(SearchNewsState.Empty)
    val searchNewsList = _searchNewsList.asStateFlow()

    private val searchQuery = MutableStateFlow("")

    init {
        searchNews()
    }

    @OptIn(FlowPreview::class)
    private fun searchNews() {
        viewModelScope.launch {
            searchQuery.debounce(500)
                .onEach { search ->
                    val job = launch {

                        _searchNewsList.value = SearchNewsState(isLoading = true)

                        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)
                        val date = Date()

                        val request = SearchDomainModel(
                            tag = search.ifEmpty { SEARCH_TAG },
                            from = simpleDateFormat.format(date),
                            to = simpleDateFormat.format(date),
                            sortedBy = SORT_BY
                        )

                        when (val result = searchNewsUseCase(request = request)) {

                            is Result.Success -> {
                                _searchNewsList.value =
                                    SearchNewsState(items = result.data.orEmpty())
                            }

                            is Result.Error -> {
                                _searchNewsList.value = SearchNewsState(isError = true)
                                errorMessage.value = result.error
                            }
                        }
                    }

                    job.join()

                }.collect()
        }
    }

    fun setSearchValue(search: String?) {

        searchQuery.value = search ?: return

    }

    companion object {
        const val SEARCH_TAG = "Android"
    }
}