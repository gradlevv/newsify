package com.gradlevv.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.gradlevv.newsify.news.search.R
import com.gradlevv.search.domain.SearchNewsItem
import com.gradlevv.ui.base.ScreenStateHandler
import com.gradlevv.ui.component.ErrorComponent
import com.gradlevv.ui.component.FullScreenCentered
import com.gradlevv.ui.component.LoadingComponent
import com.gradlevv.ui.theme.ColorOnBackground100
import com.gradlevv.ui.theme.ColorOnBackground70
import com.gradlevv.ui.theme.ColorPrimary
import com.gradlevv.ui.theme.ColorSurface

object SearchComponentDefaults {

    @Immutable
    data class Colors(
        val cardContentColor: Color,
        val titleTextColor: Color,
        val descriptionTextColor: Color,
        val buttonTextColor: Color,
        val buttonIconColor: Color,
    )

    @Immutable
    data class Sizes(
        val card: CardSizes,
        val spacing: Spacing,
        val textSizes: TextSizes
    )

    @Immutable
    data class CardSizes(
        val containerMinHeight: Dp,
        val imageMinHeight: Dp,
        val cornerRadius: Dp,
    )

    @Immutable
    data class Spacing(
        val default: Dp,
        val secondary: Dp,
    )

    @Immutable
    data class TextSizes(
        val titleTextSize: TextUnit,
        val descriptionTextSize: TextUnit,
        val buttonTextSize: TextUnit
    )

    @Composable
    @ReadOnlyComposable
    fun colors() = Colors(
        cardContentColor = ColorSurface,
        titleTextColor = ColorOnBackground100,
        descriptionTextColor = ColorOnBackground70,
        buttonTextColor = ColorPrimary,
        buttonIconColor = ColorPrimary
    )

    @Composable
    @ReadOnlyComposable
    fun sizes() = Sizes(
        card = CardSizes(
            containerMinHeight = 170.dp,
            imageMinHeight = 130.dp,
            cornerRadius = 16.dp
        ),
        spacing = Spacing(
            default = 8.dp,
            secondary = 30.dp
        ),
        textSizes = TextSizes(
            titleTextSize = 12.sp,
            descriptionTextSize = 12.sp,
            buttonTextSize = 12.sp,
        )
    )
}

@Composable
fun SearchNewsScreen(
    navHostController: NavHostController,
    onNavigateToDetail: () -> Unit
) {
    val parentEntity =
        remember(navHostController) { navHostController.getBackStackEntry(SearchNewsGraph.route) }

    val viewModel: SearchNewsViewModel = hiltViewModel(parentEntity)
    val uiState by viewModel.searchNewsList.collectAsState()

    val searchQuery by viewModel.searchQuery.collectAsState()

    val colors = SearchComponentDefaults.colors()
    val sizes = SearchComponentDefaults.sizes()

    ScreenStateHandler(
        isLoading = uiState.isInitialLoading,
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
                data = uiState.items,
                isSearching = uiState.isSearching,
                text = searchQuery,
                onItemClick = onNavigateToDetail,
                onValueChange = viewModel::setSearchValue,
                onClearSearchClick = viewModel::resetSearchValue,
                colors = colors,
                sizes = sizes
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
    data: List<SearchNewsItem>,
    isSearching: Boolean,
    text: String,
    onItemClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onClearSearchClick: () -> Unit,
    colors: SearchComponentDefaults.Colors,
    sizes: SearchComponentDefaults.Sizes
) {

    Column {

        SearchComponent(
            text = text,
            onValueChange = onValueChange,
            onDeleteClick = onClearSearchClick
        )

        if (isSearching) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(72.dp),
                color = ColorOnBackground100,
                trackColor = ColorPrimary,
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items = data) {
                    SearchItemComponent(
                        item = it,
                        onClick = onItemClick,
                        colors = colors,
                        sizes = sizes
                    )
                }
            }
        }
    }
}

@Composable
fun SearchItemComponent(
    item: SearchNewsItem,
    onClick: () -> Unit,
    colors: SearchComponentDefaults.Colors,
    sizes: SearchComponentDefaults.Sizes
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = ColorSurface,
        ),
        shape = RoundedCornerShape(sizes.card.cornerRadius),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.heightIn(sizes.card.imageMinHeight)
            )

            Spacer(
                modifier = Modifier.size(sizes.spacing.default)
            )

            Text(
                text = item.title,
                color = colors.titleTextColor,
                fontSize = sizes.textSizes.titleTextSize,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Spacer(
                modifier = Modifier.size(sizes.spacing.secondary)
            )

            Text(
                text = item.description,
                color = colors.descriptionTextColor,
                fontSize = sizes.textSizes.descriptionTextSize,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )

            Spacer(
                modifier = Modifier.size(sizes.spacing.secondary)
            )

            TextButton(
                onClick = onClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(R.string.search_read_more),
                    color = colors.buttonTextColor,
                    fontSize = sizes.textSizes.buttonTextSize,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Icon(
                    painter =
                        painterResource(
                            com.gradlevv.newsify.ui.R.drawable.ic_arrow_right
                        ),
                    tint = ColorPrimary,
                    contentDescription = null
                )
            }

        }

    }
}

@Composable
fun SearchComponent(
    text: String,
    onValueChange: (String) -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    stringResource(R.string.search_search),
                    color = ColorOnBackground70
                )
            },
            textStyle = TextStyle(
                color = ColorPrimary,
                fontWeight = FontWeight.Medium
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(com.gradlevv.newsify.ui.R.drawable.ic_search_stroke),
                    contentDescription = "",
                    tint = ColorPrimary
                )
            },
            maxLines = 1,
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
        )

        TextButton(
            onClick = onDeleteClick,
            modifier = Modifier.height(56.dp)
        ) {
            Text(
                stringResource(R.string.search_cancel),
                color = ColorOnBackground70
            )
        }
    }
}

@Preview
@Composable
fun SearchComponentPreview() {
    SearchComponent(
        text = "",
        onDeleteClick = {},
        onValueChange = {}
    )
}

@Composable
fun SearchItemComponentPreview() {
    SearchItemComponent(
        item = SearchNewsItem(
            source = SearchNewsItem.SourceItem(
                id = "",
                name = "BBC News"
            ),
            author = "",
            title = "Search for survivors after Houthis sink Red Sea cargo ship",
            description = "At least three of the 25 people on board the Eternity C were killed after it was attacked by the Yemen-based group.",
            url = "https://www.bbc.com/news/articles/c3071vp2d8yo",
            imageUrl = "https://ichef.bbci.co.uk/news/1024/branded_news/3bf2/live/c08c0e10-5cdc-11f0-b5c5-012c5796682d.jpg",
            publishedAt = "2025-07-09T16:29:58Z",
            content = "Six crew members have been recovered and at least three others killed after a cargo ship was attacked by Yemen's Houthis and sank in the Red Sea, a European naval mission says.\r\nThe Liberian-flagged,… [+3645 chars]"
        ),
        onClick = {},
        colors = SearchComponentDefaults.colors(),
        sizes = SearchComponentDefaults.sizes()
    )
}