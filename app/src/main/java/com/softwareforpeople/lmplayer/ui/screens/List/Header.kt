package com.softwareforpeople.lmplayer.ui.screens.List

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(isExtended: Boolean) {
    val headerHeight by animateDpAsState(
        targetValue = if (isExtended) LocalConfiguration.current.screenHeightDp.dp * 0.4f else 56.dp,
        label = "headerHeightAnimation"
    )
    val titleSize by animateFloatAsState(
        targetValue = if (isExtended) 30f else 20f,
        label = "textSizeAnimation"
    )
    val playTextAlpha by animateFloatAsState(
        targetValue = if (isExtended) 1f else 0f,
        label = "playButtonTextAlphaAnimation"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "Моя музыка",
                modifier = Modifier
                    .fillMaxWidth()
                    .then(if (isExtended) Modifier.weight(1f) else Modifier),
                textAlign = TextAlign.Start,
                fontSize = titleSize.sp
            )

            Text(
                text = "Play",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .alpha(playTextAlpha),
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }

            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.Filled.MoreVert,
                    contentDescription = "More"
                )
            }
        }
    }
}