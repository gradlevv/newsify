package com.gradlevv.list.ui

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import coil3.compose.AsyncImage
import com.gradlevv.list.domain.CategoryItem
import com.gradlevv.list.domain.TopHeadLinesItem
import com.gradlevv.list.ui.state.TopHeadLinesState
import com.gradlevv.newsify.news.list.R
import com.gradlevv.ui.component.FullScreenCentered
import com.gradlevv.ui.theme.ColorOnBackground100
import com.gradlevv.ui.theme.ColorOnBackground70
import com.gradlevv.ui.theme.ColorPrimary
import com.gradlevv.ui.theme.ColorSurface


@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel = hiltViewModel(),
    onNavigateToDetail: () -> Unit
) {

    val data by viewModel.categoryList.collectAsState()
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
                TypeItemListComponent(
                    data = data,
                    onTypeClick = {
                        viewModel.categoryChangeClick(it)
                        onNavigateToDetail()
                    }
                )
                TopNewsListComponent(
                    data = topHeadLines,
                    onItemClick = onNavigateToDetail
                )
            }
        }
    }

}

@Composable
fun TypeItemListComponent(
    modifier: Modifier = Modifier,
    data: List<CategoryItem>,
    onTypeClick: (item: CategoryItem) -> Unit
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
            items(data, key = { it.type }) { item ->
                TypeItemComponent(item = item, onTypeClick = onTypeClick)
            }
        }
    }

}

@Composable
fun TypeItemComponent(
    modifier: Modifier = Modifier,
    item: CategoryItem,
    onTypeClick: (item: CategoryItem) -> Unit
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
            text = stringResource(item.categoryName),
            color = ColorOnBackground70
        )
    }
}

@Composable
fun TopNewsListComponent(
    modifier: Modifier = Modifier,
    data: TopHeadLinesState,
    onItemClick: () -> Unit
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
        items(items = items, key = { it.title }) { item ->
            TopNewsComponent(item, onItemClick)
        }
    }

}

@Composable
fun TopNewsComponent(
    item: TopHeadLinesItem,
    onItemClick: () -> Unit
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
                    onClick = { onItemClick() },
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
                    onClick = { onItemClick() },
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