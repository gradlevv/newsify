package com.gradlevv.list.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.gradlevv.list.ui.NewsListViewModel
import com.gradlevv.list.ui.top.NewsGraph
import com.gradlevv.newsify.news.list.R
import com.gradlevv.ui.component.ErrorComponent
import com.gradlevv.ui.theme.ColorBackground
import com.gradlevv.ui.theme.ColorOnBackground100
import com.gradlevv.ui.theme.ColorOnBackground70
import com.gradlevv.ui.theme.ColorPrimary
import com.gradlevv.ui.theme.ColorPrimaryBackground
import com.gradlevv.ui.theme.ColorSurface

@Composable
fun NewsDetailScreen(navController: NavHostController) {

    val parentEntry = remember(navController) { navController.getBackStackEntry(NewsGraph.route) }

    val viewModel: NewsListViewModel = hiltViewModel(parentEntry)
    val detail by viewModel.newsDetailItem.collectAsState()


    NewsDetailContent(
        detail = detail,
        onReadArticleClick = { viewModel.onReadArticleClick(detail ?: return@NewsDetailContent) }
    )


}

@Composable
fun NewsDetailContent(
    detail: TopHeadLinesItem?,
    onReadArticleClick: () -> Unit,
    errorContent: @Composable () -> Unit = {
        ErrorComponent()
    },
    mainContent: @Composable () -> Unit = {
        detail?.let {
            NewsDetailComponent(item = it, onReadArticleClick = onReadArticleClick)
        } ?: errorContent()
    }
) {

    when (detail) {
        null -> errorContent()
        else -> mainContent()
    }

}

@Composable
fun NewsDetailComponent(
    item: TopHeadLinesItem,
    onReadArticleClick: () -> Unit
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

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(
                onClick = onReadArticleClick,
                modifier = Modifier
                    .padding(all = 16.dp)
                    .background(
                        shape = RoundedCornerShape(14.dp),
                        color = ColorPrimaryBackground
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    painter =
                        painterResource(
                            com.gradlevv.newsify.ui.R.drawable.ic_link_14
                        ),
                    contentDescription = null,
                    tint = ColorPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(
                        R.string.news_list_read_full_article
                    ),
                    color = ColorPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            HorizontalDivider(
                modifier = Modifier
                    .background(ColorBackground)
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 16.dp)

            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp,
                        bottom = 16.dp
                    )
            ) {

                Text(
                    text = item.author,
                    color = ColorOnBackground70,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                )

                Row(
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter =
                            painterResource(
                                com.gradlevv.newsify.ui.R.drawable.ic_history_16
                            ),
                        contentDescription = null,
                        tint = ColorOnBackground70,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
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