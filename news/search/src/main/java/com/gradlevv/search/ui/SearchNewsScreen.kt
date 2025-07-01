package com.gradlevv.search.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gradlevv.newsify.news.search.R
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.ui.base.ScreenStateHandler
import com.gradlevv.ui.component.ErrorComponent
import com.gradlevv.ui.component.FullScreenCentered
import com.gradlevv.ui.component.LoadingComponent

@Composable
fun SearchNewsScreen(
    navHostController: NavHostController,
    onNavigateToDetail: () -> Unit
) {
    val parentEntity =
        remember(navHostController) { navHostController.getBackStackEntry(SearchNewsGraph.route) }

    val viewModel: SearchNewsViewModel = hiltViewModel(parentEntity)
    val uiState by viewModel.searchNewsList.collectAsState()

    ScreenStateHandler(
        isLoading = uiState.isLoading,
        isError = uiState.isError,
        isEmpty = uiState.items.isEmpty(),
        loadingContent = {
            LoadingComponent()
        },
        errorContent = {
            ErrorComponent()
        },
        emptyContent = {
            EmptyComponent()
        },
        content = {
            MainSearchComponent(
                items = uiState.items,
                onItemClick = onNavigateToDetail
            )
        }
    )
}


@Composable
fun EmptyComponent() {
    FullScreenCentered {
        Text(
            text = stringResource(
                R.string.there_is_nothing_to_show
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun MainSearchComponent(
    items: List<SearchNewsItem>,
    onItemClick: () -> Unit
) {

}