package com.gradlevv.list.ui.top

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.list.domain.model.CategoryType
import com.gradlevv.list.ui.NewsListViewModel
import com.gradlevv.list.ui.state.TopHeadLinesState
import com.gradlevv.newsify.news.list.R
import com.gradlevv.ui.base.ScreenStateHandler
import com.gradlevv.ui.component.ErrorComponent
import com.gradlevv.ui.component.LoadingComponent
import com.gradlevv.ui.theme.ColorOnBackground100
import com.gradlevv.ui.theme.ColorOnBackground70
import com.gradlevv.ui.theme.ColorPrimary
import com.gradlevv.ui.theme.ColorSurface


@Composable
fun NewsListScreen(
    navController: NavHostController,
    onNavigateToDetail: () -> Unit,
    onNavigateToCategory: () -> Unit
) {

    val parentEntry = remember(navController) { navController.getBackStackEntry(NewsGraph.route) }
    val viewModel: NewsListViewModel = hiltViewModel(parentEntry)

    val types by viewModel.categoryList.collectAsState()
    val uiState by viewModel.topHeadLinesList.collectAsState()

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
            // todo
        },
        content = {
            TopNewsContent(
                types = types,
                uiState = uiState,
                onNavigateToDetailClick = {
                    viewModel.navigateToNewsDetail(it)
                    onNavigateToDetail()
                },
                onCategoryClick = {
                    viewModel.categoryChangeClick(it)
                    onNavigateToCategory()
                }
            )
        }
    )


}

@Composable
fun TopNewsContent(
    types: List<CategoryType> = listOf(),
    uiState: TopHeadLinesState,
    onNavigateToDetailClick: (TopHeadLinesItem) -> Unit,
    onCategoryClick: (CategoryType) -> Unit
) {
    Column {
        TypeItemListComponent(
            types = types,
            onTypeClick = {
                onCategoryClick(it)
            }
        )
        TopNewsListComponent(
            data = uiState,
            onItemClick = onNavigateToDetailClick
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 12.dp,
                ),
                text = stringResource(
                    R.string.news_list_lines_title
                ),
                color = ColorOnBackground100,
            )
        }
    }

}

@Composable
fun TypeItemListComponent(
    modifier: Modifier = Modifier,
    types: List<CategoryType>,
    onTypeClick: (item: CategoryType) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = stringResource(
                R.string.news_list_categories_title
            ),
            color = ColorOnBackground100
        )
        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                start = 8.dp, end = 8.dp
            )
        ) {
            items(types, key = { it.type }) { item ->
                TypeItemComponent(item = item, onTypeClick = onTypeClick)
            }
        }
    }

}

@Composable
fun TypeItemComponent(
    modifier: Modifier = Modifier,
    item: CategoryType,
    onTypeClick: (item: CategoryType) -> Unit
) {
    Column(
        modifier = modifier.clickable {
            onTypeClick(item)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(item.icon),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .size(95.dp)
                .clip(RoundedCornerShape(20.dp)),
        )
        Text(
            text = stringResource(item.categoryLabelRes),
            color = ColorOnBackground70
        )
    }
}

@Composable
fun TopNewsListComponent(
    modifier: Modifier = Modifier,
    data: TopHeadLinesState,
    onItemClick: (TopHeadLinesItem) -> Unit,
    topContent: @Composable () -> Unit
) {

    val items = data.items

    LazyColumn(
        modifier = modifier.padding(
            start = 8.dp,
            end = 8.dp,
            top = 32.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            topContent()
        }
        items(items = items, key = { it.title }) { item ->
            TopNewsComponent(item, onItemClick)
        }
    }

}

@Composable
fun TopNewsComponent(
    item: TopHeadLinesItem,
    onItemClick: (TopHeadLinesItem) -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = ColorSurface
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column {
            AsyncImage(
                model = item.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier.height(180.dp)
            )

            Text(
                text = item.title,
                color = ColorOnBackground100,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(
                    top = 16.dp,
                    start = 16.dp
                ),
            )

            Text(
                text = item.description,
                color = ColorOnBackground100,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(
                    top = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                TextButton(
                    onClick = { onItemClick(item) },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(
                        stringResource(
                            R.string.news_list_read_more
                        ),
                        color = ColorPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter =
                            painterResource(
                                com.gradlevv.newsify.ui.R.drawable.ic_arrow_right
                            ),
                        contentDescription = "",
                        tint = ColorPrimary
                    )
                }

                TextButton(
                    onClick = { onItemClick(item) },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter =
                            painterResource(
                                com.gradlevv.newsify.ui.R.drawable.ic_history_16
                            ),
                        contentDescription = null,
                        tint = ColorOnBackground70
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.publishedAt,
                        color = ColorOnBackground70,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
}