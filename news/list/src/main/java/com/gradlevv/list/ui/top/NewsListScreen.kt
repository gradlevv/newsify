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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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


object NewsComponentDefaults {

    @Immutable
    data class Sizes(
        val typeList: TypeListSizes,
        val typeItem: TypeItemSizes,
        val topNewsList: TopNewsListSizes,
        val topNewsItem: TopNewsItemSizes,
        val title: TitleSizes,
        val textSizes: TextSizes
    )

    @Immutable
    data class TypeListSizes(
        val textStartPadding: Dp,
        val textStartPadding2: Dp,
        val textEndPadding: Dp,
        val textPadding: Dp,
        val verticalSpace: Dp,
    )

    @Immutable
    data class TypeItemSizes(
        val bottomPadding: Dp,
        val size: Dp,
        val corner: Dp,
    )

    @Immutable
    data class TopNewsListSizes(
        val startPadding: Dp,
        val endPadding: Dp,
        val topPadding: Dp,
        val verticalSpace: Dp,
        val itemCornerRadius: Dp,
        val itemSpace: Dp,
        val itemSpace2: Dp,
    )

    @Immutable
    data class TopNewsItemSizes(
        val cornerRadius: Dp,
        val imageHeight: Dp,
        val titleTopPadding: Dp,
        val titleStartPadding: Dp,
        val descriptionTopPadding: Dp,
        val descriptionStartPadding: Dp,
        val descriptionEndPadding: Dp,
        val actionRowTopPadding: Dp,
        val rightActionSpacing: Dp,
        val leftActionSpacing: Dp,
    )

    @Immutable
    data class TitleSizes(
        val startPadding: Dp
    )

    @Immutable
    data class TextSizes(
        val title: TextUnit,
        val description: TextUnit,
        val readMore: TextUnit,
        val publishedAt: TextUnit,
    )

    @Immutable
    data class Colors(
        val titleColors: TitleColors,
        val typeListColors: TypeListColors,
        val typeItemColors: TypeItemColors,
        val topNewsItemColors: TopNewsItemColors
    )

    @Immutable
    data class TitleColors(
        val textColor: Color,
    )

    @Immutable
    data class TypeListColors(
        val textColor: Color,
    )

    @Immutable
    data class TypeItemColors(
        val textColor: Color,
    )

    @Immutable
    data class TopNewsItemColors(
        val cardContainerColor: Color,
        val titleTextColor: Color,
        val descriptionTextColor: Color,
        val readMoreTextColor: Color,
        val readMoreIconTintColor: Color,
        val publishedAtIconTintColor: Color,
        val publishedAtTextColor: Color,
    )

    @ReadOnlyComposable
    @Composable
    fun sizes() = Sizes(
        typeList = TypeListSizes(
            textStartPadding = 12.dp,
            textStartPadding2 = 8.dp,
            textEndPadding = 8.dp,
            textPadding = 12.dp,
            verticalSpace = 8.dp
        ),
        typeItem = TypeItemSizes(
            bottomPadding = 4.dp,
            size = 95.dp,
            corner = 16.dp
        ),
        topNewsList = TopNewsListSizes(
            startPadding = 8.dp,
            endPadding = 8.dp,
            topPadding = 32.dp,
            verticalSpace = 12.dp,
            itemCornerRadius = 20.dp,
            itemSpace = 4.dp,
            itemSpace2 = 8.dp
        ), topNewsItem = TopNewsItemSizes(
            cornerRadius = 20.dp,
            imageHeight = 180.dp,
            titleTopPadding = 16.dp,
            titleStartPadding = 16.dp,
            descriptionTopPadding = 8.dp,
            descriptionStartPadding = 16.dp,
            descriptionEndPadding = 16.dp,
            actionRowTopPadding = 24.dp,
            rightActionSpacing = 4.dp,
            leftActionSpacing = 8.dp
        ),
        title = TitleSizes(
            startPadding = 12.dp
        ),
        textSizes = TextSizes(
            title = 15.sp,
            description = 14.sp,
            publishedAt = 12.sp,
            readMore = 12.sp
        )
    )

    @ReadOnlyComposable
    @Composable
    fun colors() = Colors(
        titleColors = TitleColors(
            textColor = ColorOnBackground100,
        ),
        typeListColors = TypeListColors(
            textColor = ColorOnBackground100
        ),
        typeItemColors = TypeItemColors(
            textColor = ColorOnBackground70
        ),
        topNewsItemColors = TopNewsItemColors(
            cardContainerColor = ColorSurface,
            titleTextColor = ColorOnBackground100,
            descriptionTextColor = ColorOnBackground100,
            readMoreTextColor = ColorPrimary,
            readMoreIconTintColor = ColorPrimary,
            publishedAtTextColor = ColorOnBackground70,
            publishedAtIconTintColor = ColorOnBackground70,
        )
    )
}


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

    val sizes = NewsComponentDefaults.sizes()
    val colors = NewsComponentDefaults.colors()

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
                },
                sizes = sizes,
                colors = colors
            )
        }
    )


}

@Composable
fun TopNewsContent(
    types: List<CategoryType>,
    uiState: TopHeadLinesState,
    onNavigateToDetailClick: (TopHeadLinesItem) -> Unit,
    onCategoryClick: (CategoryType) -> Unit,
    sizes: NewsComponentDefaults.Sizes,
    colors: NewsComponentDefaults.Colors
) {
    Column {
        TypeItemListComponent(
            types = types,
            onTypeClick = {
                onCategoryClick(it)
            },
            colors = colors,
            sizes = sizes
        )
        TopNewsListComponent(
            items = uiState.items,
            onItemClick = onNavigateToDetailClick,
            colors = colors,
            sizes = sizes
        ) {
            Text(
                modifier = Modifier.padding(
                    start = sizes.title.startPadding,
                ),
                text = stringResource(
                    R.string.news_list_lines_title
                ),
                color = colors.titleColors.textColor,
            )
        }
    }

}

@Composable
fun TypeItemListComponent(
    modifier: Modifier = Modifier,
    types: List<CategoryType>,
    onTypeClick: (item: CategoryType) -> Unit,
    sizes: NewsComponentDefaults.Sizes,
    colors: NewsComponentDefaults.Colors
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = sizes.typeList.textStartPadding),
            text = stringResource(
                R.string.news_list_categories_title
            ),
            color = colors.typeListColors.textColor
        )
        LazyRow(
            modifier = Modifier.padding(top = sizes.typeList.textPadding),
            horizontalArrangement = Arrangement.spacedBy(sizes.typeList.verticalSpace),
            contentPadding = PaddingValues(
                start = sizes.typeList.textStartPadding2, end = sizes.typeList.textEndPadding
            )
        ) {
            items(types, key = { it.type }) { item ->
                TypeItemComponent(
                    item = item,
                    onTypeClick = onTypeClick,
                    colors = colors,
                    sizes = sizes
                )
            }
        }
    }

}

@Composable
fun TypeItemComponent(
    modifier: Modifier = Modifier,
    item: CategoryType,
    onTypeClick: (item: CategoryType) -> Unit,
    sizes: NewsComponentDefaults.Sizes,
    colors: NewsComponentDefaults.Colors
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
                .padding(bottom = sizes.typeItem.bottomPadding)
                .size(sizes.typeItem.size)
                .clip(RoundedCornerShape(sizes.typeItem.corner)),
        )
        Text(
            text = stringResource(item.categoryLabelRes),
            color = colors.typeItemColors.textColor
        )
    }
}

@Composable
fun TopNewsListComponent(
    modifier: Modifier = Modifier,
    items: List<TopHeadLinesItem>,
    onItemClick: (TopHeadLinesItem) -> Unit,
    sizes: NewsComponentDefaults.Sizes,
    colors: NewsComponentDefaults.Colors,
    topContent: @Composable () -> Unit
) {


    LazyColumn(
        modifier = modifier.padding(
            start = sizes.topNewsList.startPadding,
            end = sizes.topNewsList.endPadding,
            top = sizes.topNewsList.topPadding
        ),
        verticalArrangement = Arrangement.spacedBy(sizes.topNewsList.verticalSpace),
    ) {
        item {
            topContent()
        }
        items(items = items, key = { it.title }) { item ->
            TopNewsComponent(
                item,
                onItemClick,
                colors = colors,
                sizes = sizes
            )
        }
    }

}

@Composable
fun TopNewsComponent(
    item: TopHeadLinesItem,
    onItemClick: (TopHeadLinesItem) -> Unit,
    colors: NewsComponentDefaults.Colors,
    sizes: NewsComponentDefaults.Sizes
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colors.topNewsItemColors.cardContainerColor
        ),
        shape = RoundedCornerShape(sizes.topNewsItem.cornerRadius),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column {
            AsyncImage(
                model = item.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier.height(sizes.topNewsItem.imageHeight)
            )

            Text(
                text = item.title,
                color = colors.topNewsItemColors.titleTextColor,
                fontSize = sizes.textSizes.title,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(
                    top = sizes.topNewsItem.titleTopPadding,
                    start = sizes.topNewsItem.titleStartPadding
                ),
            )

            Text(
                text = item.description,
                color = colors.topNewsItemColors.descriptionTextColor,
                fontSize = sizes.textSizes.description,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(
                    top = sizes.topNewsItem.descriptionTopPadding,
                    start = sizes.topNewsItem.descriptionStartPadding,
                    end = sizes.topNewsItem.descriptionEndPadding
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = sizes.topNewsItem.actionRowTopPadding)
            ) {
                TextButton(
                    onClick = { onItemClick(item) },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(
                        stringResource(
                            R.string.news_list_read_more
                        ),
                        color = colors.topNewsItemColors.readMoreTextColor,
                        fontSize = sizes.textSizes.readMore,
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.width(sizes.topNewsItem.rightActionSpacing))
                    Icon(
                        painter =
                            painterResource(
                                com.gradlevv.newsify.ui.R.drawable.ic_arrow_right
                            ),
                        contentDescription = "",
                        tint = colors.topNewsItemColors.readMoreIconTintColor
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
                        tint = colors.topNewsItemColors.publishedAtIconTintColor
                    )
                    Spacer(modifier = Modifier.width(sizes.topNewsItem.leftActionSpacing))
                    Text(
                        text = item.publishedAt,
                        color = colors.topNewsItemColors.publishedAtTextColor,
                        fontSize = sizes.textSizes.publishedAt,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
}