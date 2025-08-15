package com.gradlevv.search.domain.usecase

import com.gradlevv.core.data.model.Result
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.search.domain.SearchNewsRepository
import com.gradlevv.search.util.DateProvider
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.text.ifEmpty

class SearchNewsUseCase @Inject constructor(
    private val repository: SearchNewsRepository,
    private val dateProvider: DateProvider
) {
    suspend operator fun invoke(tag: String): Result<List<SearchNewsItem>> {

        val today = dateProvider.today()
        val yesterday = today.minusDays(1)

        val from = yesterday.format(formatter)
        val to = today.format(formatter)

        val request = Params(
            tag = tag.trim().ifEmpty { DEFAULT_TAG },
            from = from,
            to = to,
            sortedBy = DEFAULT_SORT
        )

        return repository.searchNews(params = request)
    }

    data class Params(
        val tag: String,
        val from: String,
        val to: String,
        val sortedBy: String
    )

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd"
        private const val DEFAULT_SORT = "popularity"
        private const val DEFAULT_TAG = "Iran"
        private val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    }
}