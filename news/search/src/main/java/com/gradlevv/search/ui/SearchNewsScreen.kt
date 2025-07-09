package com.gradlevv.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
        val cardContainerMinHeight: Dp,
        val cardImageMinHeight: Dp,
        val cardCornerRadius: Dp,
        val spacer: Dp,
        val spacer2: Dp,
        val textSizes: TextSizes
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
        cardContentColor = ColorPrimary,
        titleTextColor = ColorOnBackground100,
        descriptionTextColor = ColorOnBackground70,
        buttonTextColor = ColorPrimary,
        buttonIconColor = ColorPrimary
    )

    @Composable
    @ReadOnlyComposable
    fun sizes(
        cardContainerMinHeight: Dp = 170.dp,
        cardImageMinHeight: Dp = 130.dp,
        cardCornerRadius: Dp = 16.dp,
        spacer: Dp = 8.dp,
        spacer2: Dp = 30.dp
    ) = Sizes(
        cardContainerMinHeight,
        cardImageMinHeight,
        cardCornerRadius,
        spacer,
        spacer2,
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

    val colors = SearchComponentDefaults.colors()
    val sizes = SearchComponentDefaults.sizes()

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
            EmptyComponent()
        },
        content = {
            MainSearchComponent(
                data = uiState.items,
                onItemClick = onNavigateToDetail,
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
    onItemClick: () -> Unit,
    colors: SearchComponentDefaults.Colors,
    sizes: SearchComponentDefaults.Sizes
) {

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2)
    ) {
        items(items = data, key = { it.title }) {
            SearchItemComponent(item = it, onClick = onItemClick, colors = colors, sizes = sizes)
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
            contentColor = colors.cardContentColor,
        ),
        shape = RoundedCornerShape(sizes.cardCornerRadius),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(sizes.cardContainerMinHeight)
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = item.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier.heightIn(sizes.cardImageMinHeight)
            )

            Spacer(
                modifier = Modifier.size(sizes.spacer)
            )

            Text(
                text = item.title,
                color = colors.titleTextColor,
                fontSize = sizes.textSizes.titleTextSize,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(
                modifier = Modifier.size(sizes.spacer)
            )

            Text(
                text = item.description,
                color = colors.descriptionTextColor,
                fontSize = sizes.textSizes.descriptionTextSize,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(
                modifier = Modifier.size(sizes.spacer2)
            )

            Button(
                onClick = onClick,
                colors = ButtonColors(
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
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

@Preview
@Composable
fun SearchItemComponentPreview() {
    SearchItemComponent(
        item = SearchNewsItem(
            source = SearchNewsItem.SourceItem(id = "", name = "bbc"),
            author = "Test",
            title = "Test",
            description = "Test",
            url = "",
            imageUrl = "",
            publishedAt = "",
            content = ""
        ),
        onClick = {},
        colors = SearchComponentDefaults.colors(),
        sizes = SearchComponentDefaults.sizes()
    )
}