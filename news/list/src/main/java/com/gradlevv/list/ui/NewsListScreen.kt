package com.gradlevv.list.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.gradlevv.ui.theme.ColorOnBackground100
import com.gradlevv.ui.theme.ColorOnBackground70
import com.gradlevv.ui.theme.ColorPrimary


@Composable
fun NewsListScreen(viewModel: NewsListViewModel = hiltViewModel()) {

    val data by viewModel.categoryList.collectAsState()
    val topHeadLines by viewModel.topHeadLinesList.collectAsState()

    Column {
        TypeItemListComponent(data = data)
        TopNewsLisComponent(
            data = topHeadLines,
        )
    }

}

@Composable
fun TypeItemListComponent(
    modifier: Modifier = Modifier,
    data: List<CategoryItem>
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = stringResource(R.string.news_list_categories_title),
            color = ColorOnBackground100
        )
        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
        ) {
            items(data, key = { it.type }) { item ->
                TypeItemComponent(item = item)
            }
        }
    }

}

@Composable
fun TypeItemComponent(
    modifier: Modifier = Modifier,
    item: CategoryItem
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(item.icon),
            contentDescription = "",
            modifier = Modifier
                .padding(bottom = 4.dp)
                .size(95.dp)
                .clip(RoundedCornerShape(20.dp)),
        )
        Text(text = stringResource(item.categoryName), color = ColorOnBackground70)
    }
}

@Composable
fun TopNewsLisComponent(
    modifier: Modifier = Modifier,
    data: TopHeadLinesState
) {

    val items = data.items

    Column(modifier = modifier.padding(start = 8.dp, end = 8.dp)) {
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = stringResource(R.string.news_list_lines_title),
            color = ColorOnBackground100
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = items, key = { it.title }) { item ->
                TopNewsComponent(modifier = modifier, item)
            }
        }
    }

}

@Composable
fun TopNewsComponent(
    modifier: Modifier = Modifier,
    item: TopHeadLinesItem
) {

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.fillMaxWidth()
    ) {

        Column(modifier = modifier) {
            AsyncImage(
                model = item.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = "",
                modifier = Modifier.height(180.dp)
            )

            Text(
                text = item.title,
                color = ColorOnBackground100,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
            )

            Text(
                text = item.description,
                color = ColorOnBackground100,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = {}) {
                    Text(
                        stringResource(
                            R.string.news_list_read_more
                        ),
                        color = ColorPrimary
                    )
                    Icon(
                        painter =
                            painterResource(com.gradlevv.newsify.ui.R.drawable.ic_arrow_right),
                        contentDescription = "",
                        tint = ColorPrimary
                    )
                }
            }
        }
    }
}