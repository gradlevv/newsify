package com.gradlevv.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gradlevv.newsify.news.list.R
import com.gradlevv.ui.component.FullScreenCentered
import com.gradlevv.ui.theme.ColorOnBackground100
import com.gradlevv.ui.theme.ColorPrimary

@Composable
fun NewsDetailScreen(navController: NavHostController) {

    val parentEntry = remember(navController) { navController.getBackStackEntry(NewsGraph.route) }

    val viewModel: NewsListViewModel = hiltViewModel(parentEntry)
    val topHeadLines by viewModel.topHeadLinesList.collectAsState()


    when {

        topHeadLines.isLoading -> {

            FullScreenCentered {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(72.dp)
                        .align(Alignment.Center),
                    color = ColorOnBackground100,
                    trackColor = ColorPrimary,
                )
            }

        }

        topHeadLines.isError -> {

            FullScreenCentered {
                Text(
                    text = stringResource(
                        R.string.news_list_something_gets_wrong
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        topHeadLines.items.isNotEmpty() -> {
            Column {
                TopNewsListComponent(
                    data = topHeadLines,
                    onItemClick = {}
                )
            }
        }
    }

}


