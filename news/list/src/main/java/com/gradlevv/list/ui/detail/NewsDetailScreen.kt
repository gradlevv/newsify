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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
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

object NewsDetailDefaults {

    @Immutable
    data class Sizes(
        val componentSizes: DetailComponentSizes,
        val textSizes: TextSizes
    )

    @Immutable
    data class TextSizes(
        val title: TextUnit,
        val description: TextUnit,
        val readFullArticle: TextUnit,
        val author: TextUnit,
        val publishedAt: TextUnit,
    )

    @Immutable
    data class DetailComponentSizes(
        val cardCorner: Dp,
        val imageHeight: Dp,
        val titleStartPadding: Dp,
        val titleTopPadding: Dp,
        val descriptionTopPadding: Dp,
        val descriptionEndPadding: Dp,
        val descriptionStartPadding: Dp,
        val spacer: Dp,
        val spacer2: Dp,
        val buttonCorner: Dp,
        val buttonPadding: Dp,
        val boxTopPadding: Dp,
        val boxBottomPadding: Dp,
        val authorEndPadding: Dp,
        val publishedAtIconEndPadding: Dp,
        val publishedAtIconStartPadding: Dp,
        val horizontalDividerHeight: Dp,
        val horizontalDividerPadding: Dp,
    )

    @ReadOnlyComposable
    @Composable
    fun sizes() = Sizes(
        textSizes = TextSizes(
            title = 15.sp,
            description = 14.sp,
            readFullArticle = 12.sp,
            author = 12.sp,
            publishedAt = 12.sp
        ),
        componentSizes = DetailComponentSizes(
            cardCorner = 20.dp,
            imageHeight = 180.dp,
            titleStartPadding = 16.dp,
            titleTopPadding = 16.dp,
            descriptionTopPadding = 8.dp,
            descriptionStartPadding = 16.dp,
            descriptionEndPadding = 16.dp,
            spacer = 32.dp,
            spacer2 = 8.dp,
            buttonCorner = 14.dp,
            buttonPadding = 16.dp,
            boxTopPadding = 24.dp,
            boxBottomPadding = 16.dp,
            authorEndPadding = 16.dp,
            publishedAtIconStartPadding = 8.dp,
            publishedAtIconEndPadding = 8.dp,
            horizontalDividerPadding = 16.dp,
            horizontalDividerHeight = 1.dp
        )
    )

    @Immutable
    data class Colors(
        val cardContainerColor: Color,
        val titleTextColor: Color,
        val descriptionTextColor: Color,
        val buttonBackColor: Color,
        val readFullArticleTintColor: Color,
        val readFullArticleTextColor: Color,
        val dividerColor: Color,
        val authorTextColor: Color,
        val publishedAtTintColor: Color,
        val publishedAtTextColor: Color,
    )

    @Composable
    fun colors() = Colors(
        cardContainerColor = ColorSurface,
        titleTextColor = ColorOnBackground100,
        descriptionTextColor = ColorOnBackground100,
        buttonBackColor = ColorPrimaryBackground,
        readFullArticleTintColor = ColorPrimary,
        readFullArticleTextColor = ColorPrimary,
        dividerColor = ColorBackground,
        authorTextColor = ColorOnBackground70,
        publishedAtTextColor = ColorOnBackground70,
        publishedAtTintColor = ColorOnBackground70
    )
}

@Composable
fun NewsDetailScreen(navController: NavHostController) {

    val parentEntry = remember(navController) { navController.getBackStackEntry(NewsGraph.route) }

    val viewModel: NewsListViewModel = hiltViewModel(parentEntry)
    val detail by viewModel.newsDetailItem.collectAsState()

    val sizes = NewsDetailDefaults.sizes()
    val colors = NewsDetailDefaults.colors()

    NewsDetailContent(
        detail = detail,
        onItemClick = {
            viewModel.onReadArticleClick(
                detail ?: return@NewsDetailContent
            )
        },
        sizes = sizes,
        colors = colors
    )

}

@Composable
fun NewsDetailContent(
    detail: TopHeadLinesItem?,
    onItemClick: () -> Unit,
    sizes: NewsDetailDefaults.Sizes,
    colors: NewsDetailDefaults.Colors,
    errorContent: @Composable () -> Unit = {
        ErrorComponent()
    },
    mainContent: @Composable () -> Unit = {
        detail?.let {
            NewsDetailComponent(
                item = it,
                onItemClick = onItemClick,
                sizes = sizes,
                colors = colors
            )
        } ?: errorContent()
    },
) {

    when (detail) {
        null -> errorContent()
        else -> mainContent()
    }

}

@Composable
fun NewsDetailComponent(
    item: TopHeadLinesItem,
    onItemClick: () -> Unit,
    sizes: NewsDetailDefaults.Sizes,
    colors: NewsDetailDefaults.Colors
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colors.cardContainerColor
        ),
        shape = RoundedCornerShape(sizes.componentSizes.cardCorner),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column {
            AsyncImage(
                model = item.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier.height(sizes.componentSizes.imageHeight)
            )

            Text(
                text = item.title,
                color = colors.titleTextColor,
                fontSize = sizes.textSizes.title,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(
                    top = sizes.componentSizes.titleTopPadding,
                    start = sizes.componentSizes.titleStartPadding
                ),
            )

            Text(
                text = item.description,
                color = colors.descriptionTextColor,
                fontSize = sizes.textSizes.description,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(
                    top = sizes.componentSizes.descriptionTopPadding,
                    start = sizes.componentSizes.descriptionStartPadding,
                    end = sizes.componentSizes.descriptionEndPadding
                )
            )

            Spacer(modifier = Modifier.height(sizes.componentSizes.spacer))

            TextButton(
                onClick = onItemClick,
                modifier = Modifier
                    .padding(all = sizes.componentSizes.buttonPadding)
                    .background(
                        shape = RoundedCornerShape(sizes.componentSizes.buttonCorner),
                        color = colors.buttonBackColor
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    painter =
                        painterResource(
                            com.gradlevv.newsify.ui.R.drawable.ic_link_14
                        ),
                    contentDescription = null,
                    tint = colors.readFullArticleTintColor
                )
                Spacer(modifier = Modifier.width(sizes.componentSizes.spacer2))
                Text(
                    text = stringResource(
                        R.string.news_list_read_full_article
                    ),
                    color = colors.readFullArticleTextColor,
                    fontSize = sizes.textSizes.readFullArticle,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(sizes.componentSizes.spacer))

            HorizontalDivider(
                modifier = Modifier
                    .background(colors.dividerColor)
                    .fillMaxWidth()
                    .height(sizes.componentSizes.horizontalDividerHeight)
                    .padding(horizontal = sizes.componentSizes.horizontalDividerPadding)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = sizes.componentSizes.boxTopPadding,
                        bottom = sizes.componentSizes.boxBottomPadding
                    )
            ) {

                Text(
                    text = item.author,
                    color = colors.authorTextColor,
                    fontSize = sizes.textSizes.author,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = sizes.componentSizes.authorEndPadding)
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
                        tint = colors.publishedAtTintColor,
                        modifier = Modifier.padding(
                            start = sizes.componentSizes.publishedAtIconStartPadding,
                            end = sizes.componentSizes.publishedAtIconEndPadding
                        )
                    )
                    Text(
                        text = item.publishedAt,
                        color = colors.publishedAtTextColor,
                        fontSize = sizes.textSizes.publishedAt,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
}