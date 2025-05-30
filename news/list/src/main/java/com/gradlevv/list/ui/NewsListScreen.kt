package com.gradlevv.list.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradlevv.list.domain.CategoryItem
import com.gradlevv.newsify.news.list.R
import com.gradlevv.ui.theme.ColorOnBackground100
import com.gradlevv.ui.theme.ColorOnBackground70


@Composable
fun NewsListScreen(viewModel: NewsListViewModel = hiltViewModel()) {

    val data by viewModel.categoryList.collectAsState()

    TypeItemListComponent(data = data)
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
