package com.gradlevv.list.ui.category

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.list.ui.NewsListViewModel
import com.gradlevv.list.ui.state.TopHeadLinesState
import com.gradlevv.list.ui.top.ErrorComponent
import com.gradlevv.list.ui.top.LoadingComponent
import com.gradlevv.list.ui.top.NewsGraph
import com.gradlevv.list.ui.top.TopNewsListComponent
import com.gradlevv.ui.theme.ColorOnBackground100


@Composable
fun NewsCategoryScreen(navController: NavHostController) {
    val parentEntry = remember(navController) { navController.getBackStackEntry(NewsGraph.route) }

    val viewModel: NewsListViewModel = hiltViewModel(parentEntry)
    val uiState by viewModel.topHeadLinesList.collectAsState()


    NewsCategoryComponent(
        uiState = uiState,
        onDetailClick = {}
    )

}


@Composable
fun NewsCategoryComponent(
    uiState: TopHeadLinesState,
    onDetailClick: (TopHeadLinesItem) -> Unit,
    loadingContent: @Composable () -> Unit = {
        LoadingComponent()
    },
    errorContent: @Composable () -> Unit = {
        ErrorComponent()
    },
    content: @Composable () -> Unit = {
        SelectedNewsComponent(
            uiState = uiState,
            onDetailClick = onDetailClick
        )
    }
) {
    when {
        uiState.isLoading -> loadingContent()
        uiState.isError -> errorContent()
        else -> content()
    }
}

@Composable
fun SelectedNewsComponent(
    uiState: TopHeadLinesState,
    onDetailClick: (TopHeadLinesItem) -> Unit,
) {
    TopNewsListComponent(
        data = uiState,
        onItemClick = onDetailClick
    ) {
        Text(
            modifier = Modifier.padding(
                start = 16.dp,
            ),
            text = stringResource(uiState.selectedCategory.categoryLabelRes),
            color = ColorOnBackground100,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )
    }
}
