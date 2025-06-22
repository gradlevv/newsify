package com.gradlevv.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gradlevv.newsify.ui.R
import com.gradlevv.ui.theme.ColorOnBackground100
import com.gradlevv.ui.theme.ColorPrimary

@Composable
fun LoadingComponent() {

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

@Composable
fun ErrorComponent() {

    FullScreenCentered {
        Text(
            text = stringResource(
                R.string.something_gets_wrong
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }

}