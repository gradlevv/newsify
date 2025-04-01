package com.gradlevv.search.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.gradlevv.core.base.BaseViewModel
import com.gradlevv.core.data.model.Result
import com.gradlevv.core.util.Constants.DATE_FORMAT
import com.gradlevv.core.util.Constants.SORT_BY
import com.gradlevv.search.R
import com.gradlevv.search.domain.SearchDomainModel
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.domain.usecase.SearchNewsUseCase
import com.gradlevv.search.ui.state.SearchNewsState
import com.gradlevv.ui.utils.navOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCase: SearchNewsUseCase
) : BaseViewModel() {

    private val _searchNewsList = MutableStateFlow(SearchNewsState.Empty)
    val searchNewsList = _searchNewsList.asStateFlow()

    private val _newsDetailItem = MutableStateFlow<SearchNewsItem?>(null)
    val newsDetailItem = _newsDetailItem.asStateFlow()

    private val searchQuery = MutableStateFlow("")

    init {
        searchNews()
        Log.d("TAG", "init")
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

    fun navigateToDetailFragment(item: SearchNewsItem) {
        _newsDetailItem.value = item
        navigate(R.string.search_news_detail_fragment, navOptions)
    }

    companion object {
        const val SEARCH_TAG = "Android"
    }
}