package com.softwareforpeople.lmplayer.ui.screens.List

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(isExtended: Boolean) {
    val headerHeight by animateDpAsState(
        targetValue = if (isExtended) 400.dp else 56.dp,
        label = "headerHeightAnimation"
    )
    val textSize by animateFloatAsState(
        targetValue = if (isExtended) 30f else 20f,
        label = "textSizeAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
            .padding(16.dp)
    ) {
        Text(
            text = "Заголовок",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = if (isExtended) 30.dp else 0.dp)
                .then(if (isExtended) Modifier.weight(1f) else Modifier),
            textAlign = TextAlign.Center,
            fontSize = textSize.sp
        )
        if (true) {
            // Кнопка Play (пока просто текст)
            Text(
                text = "Play",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Icon(Icons.Filled.MoreVert, contentDescription = "More")
        }
    }
}