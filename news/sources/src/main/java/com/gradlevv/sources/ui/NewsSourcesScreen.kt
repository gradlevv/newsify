package com.gradlevv.sources.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
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
import com.gradlevv.newsify.news.sources.R
import com.gradlevv.sources.domain.model.CategoryItem
import com.gradlevv.sources.domain.model.SourceItem
import com.gradlevv.ui.base.ScreenStateHandler
import com.gradlevv.ui.component.ErrorComponent
import com.gradlevv.ui.component.FullScreenCentered
import com.gradlevv.ui.component.LoadingComponent
import com.gradlevv.ui.theme.ColorBackground
import com.gradlevv.ui.theme.ColorOnBackground100
import com.gradlevv.ui.theme.ColorOnBackground70
import com.gradlevv.ui.theme.ColorPrimary


object SourcesComponentsDefaults {

    @Immutable
    data class Colors(
        val titleColor: Color,
        val selectedTypeBackColor: Color,
        val unselectedTypeBackColor: Color,
        val selectedTextColor: Color,
        val unselectedTextColor: Color,
        val sourceBackColor: Color,
        val sourceTextColor: Color,
        val iconTintColor: Color,
        val arrowIconTintColor: Color,
    )

    @Immutable
    data class Sizes(
        val screenPadding: Dp,
        val titleTopPadding: Dp,
        val titleFontSize: TextUnit,
        val categoryItemSpacing: Dp,
        val categoryItemPadding: PaddingValues,
        val listVerticalSpacing: Dp,
        val roundShapeCorner: Dp,
        val itemMinHeight: Dp,
        val itemIconSize: Dp,
        val itemIconStartPadding: Dp,
        val itemTextStartPadding: Dp,
        val itemTextFontSize: TextUnit,
        val itemArrowEndPadding: Dp,
        val itemArrowIconSize: Dp,
        val spacerHeight: Dp,
        val spacerHeight2: Dp,
        val sourceItemTextSize: TextUnit,
        val sourceItemPadding: Dp,
        val sourceItemPadding2: Dp,
    )

    @Composable
    @ReadOnlyComposable
    fun colors(
        titleColor: Color = ColorOnBackground100,
        selectedTypeBackColor: Color = ColorPrimary,
        unselectedTypeBackColor: Color = Color.Transparent,
        selectedTextBackColor: Color = ColorBackground,
        unselectedTextBackColor: Color = ColorOnBackground70,
        sourceBackColor: Color = ColorBackground,
        sourceTextColor: Color = ColorOnBackground100,
        iconTintColor: Color = ColorOnBackground70,
        arrowIconTintColor: Color = ColorPrimary,
    ) = Colors(
        titleColor,
        selectedTypeBackColor,
        unselectedTypeBackColor,
        selectedTextBackColor,
        unselectedTextBackColor,
        sourceBackColor,
        sourceTextColor,
        iconTintColor,
        arrowIconTintColor,
    )

    @Composable
    @ReadOnlyComposable
    fun sizes(
        screenPadding: Dp = 16.dp,
        titleTopPadding: Dp = 18.dp,
        titleFontSize: TextUnit = 24.sp,
        categoryItemSpacing: Dp = 16.dp,
        categoryItemPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        listVerticalSpacing: Dp = 16.dp,
        roundShapeCorner: Dp = 16.dp,
        itemMinHeight: Dp = 56.dp,
        itemIconSize: Dp = 24.dp,
        itemIconStartPadding: Dp = 16.dp,
        itemTextStartPadding: Dp = 52.dp,
        itemTextFontSize: TextUnit = 15.sp,
        itemArrowEndPadding: Dp = 20.dp,
        itemArrowIconSize: Dp = 24.dp,
        spacerHeight: Dp = 18.dp,
        spacerHeight2: Dp = 16.dp,
        sourceItemTextSize: TextUnit = 14.sp,
        sourceItemPadding: Dp = 12.dp,
        sourceItemPadding2: Dp = 6.dp,
    ) = Sizes(
        screenPadding,
        titleTopPadding,
        titleFontSize,
        categoryItemSpacing,
        categoryItemPadding,
        listVerticalSpacing,
        roundShapeCorner,
        itemMinHeight,
        itemIconSize,
        itemIconStartPadding,
        itemTextStartPadding,
        itemTextFontSize,
        itemArrowEndPadding,
        itemArrowIconSize,
        spacerHeight,
        spacerHeight2,
        sourceItemTextSize,
        sourceItemPadding,
        sourceItemPadding2
    )

}


@Composable
fun NewsSourcesScreen() {

    val viewModel = hiltViewModel<NewsSourcesViewModel>()

    val categories by viewModel.categoryList.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    val colors = SourcesComponentsDefaults.colors()
    val sizes = SourcesComponentsDefaults.sizes()

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
            MainComponent(
                categories = categories,
                items = uiState.items,
                onSourceItemClick = {},
                onCategoryItemClick = viewModel::categoryChangeClick,
                colors = colors,
                sizes = sizes
            )
        },
    )
}

@Composable
fun EmptyComponent() {
    FullScreenCentered {
        Text(
            text = stringResource(
                R.string.sources_there_is_nothing_to_show
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun MainComponent(
    categories: List<CategoryItem>,
    items: List<SourceItem>,
    onSourceItemClick: (SourceItem) -> Unit,
    onCategoryItemClick: (CategoryItem) -> Unit,
    colors: SourcesComponentsDefaults.Colors,
    sizes: SourcesComponentsDefaults.Sizes
) {

    Column {
        Text(
            modifier = Modifier.padding(
                start = sizes.screenPadding,
            ),
            text = stringResource(R.string.sources_title),
            color = colors.titleColor,
            fontSize = sizes.titleFontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )

        Spacer(modifier = Modifier.height(sizes.spacerHeight))

        SourceTypeListComponent(
            categories = categories,
            onCategoryItemClick,
            colors = colors,
            sizes = sizes
        )

        Spacer(modifier = Modifier.size(sizes.spacerHeight2))

        SourceItemListComponent(
            items = items,
            onSourceItemClick,
            colors,
            sizes = sizes
        )
    }
}

@Composable
fun SourceTypeListComponent(
    categories: List<CategoryItem>,
    onItemClick: (CategoryItem) -> Unit,
    colors: SourcesComponentsDefaults.Colors,
    sizes: SourcesComponentsDefaults.Sizes
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(sizes.categoryItemSpacing),
        contentPadding = sizes.categoryItemPadding
    ) {
        items(categories, key = { it.type }) {
            SourceTypeItemComponent(it, onItemClick, colors, sizes = sizes)
        }
    }
}

@Composable
fun SourceTypeItemComponent(
    item: CategoryItem,
    onItemClick: (CategoryItem) -> Unit,
    colors: SourcesComponentsDefaults.Colors,
    sizes: SourcesComponentsDefaults.Sizes
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(sizes.roundShapeCorner))
            .background(
                if (item.isChecked)
                    colors.selectedTypeBackColor
                else
                    colors.unselectedTypeBackColor
            )
            .clickable(
                onClick = { onItemClick(item) },
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Text(
            modifier = Modifier.padding(
                start = sizes.sourceItemPadding,
                top = sizes.sourceItemPadding2,
                end = sizes.sourceItemPadding,
                bottom = sizes.sourceItemPadding2
            ),
            text = stringResource(item.categoryName),
            fontSize = sizes.sourceItemTextSize,
            color = if (item.isChecked)
                colors.selectedTextColor
            else
                colors.unselectedTextColor
        )
    }
}

@Composable
fun SourceItemListComponent(
    items: List<SourceItem>,
    onSourceItemClick: (SourceItem) -> Unit,
    colors: SourcesComponentsDefaults.Colors,
    sizes: SourcesComponentsDefaults.Sizes
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(sizes.listVerticalSpacing),
    ) {
        items(items = items, key = { it.name }) {
            SourceItemComponent(
                it,
                onSourceItemClick,
                colors,
                sizes = sizes
            )
        }
    }
}

@Composable
fun SourceItemComponent(
    item: SourceItem,
    onSourceItemClick: (SourceItem) -> Unit,
    colors: SourcesComponentsDefaults.Colors,
    sizes: SourcesComponentsDefaults.Sizes
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(sizes.itemMinHeight)
            .clip(RoundedCornerShape(sizes.roundShapeCorner))
            .background(colors.sourceBackColor)
            .clickable(
                onClick = { onSourceItemClick(item) },
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            ),
    ) {
        Icon(
            painter = painterResource(com.gradlevv.newsify.ui.R.drawable.ic_global_fill),
            tint = colors.iconTintColor,
            contentDescription = null,
            modifier = Modifier
                .padding(start = sizes.itemIconStartPadding)
                .size(sizes.itemIconSize)
                .align(Alignment.CenterStart)
        )
        Text(
            text = item.name,
            color = colors.sourceTextColor,
            fontWeight = FontWeight.Normal,
            fontSize = sizes.itemTextFontSize,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = sizes.itemTextStartPadding)
        )
        Icon(
            painter = painterResource(
                com.gradlevv.newsify.ui.R.drawable.ic_arrow_right
            ),
            tint = colors.arrowIconTintColor,
            contentDescription = null,
            modifier = Modifier
                .padding(end = sizes.itemArrowEndPadding)
                .size(sizes.itemArrowIconSize)
                .align(Alignment.CenterEnd)
        )
    }
}

@Preview
@Composable
fun SourceItemComponentPreview() {
    SourceItemComponent(
        item = SourceItem(
            name = "test",
            description = "test test",
            url = "",
            category = "",
            language = "",
            country = ""
        ),
        onSourceItemClick = {},
        colors = SourcesComponentsDefaults.colors(),
        sizes = SourcesComponentsDefaults.sizes()
    )
}

