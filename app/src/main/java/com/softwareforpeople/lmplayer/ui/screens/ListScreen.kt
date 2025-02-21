package com.softwareforpeople.lmplayer.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Entity
import androidx.room.PrimaryKey

@Composable
fun listScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(100) {
            Item(it)
        }
    }
    addSong(onClick = {})
}

@Composable
fun Item(it: Int) {
    Text(text = "Item $it", modifier = Modifier.padding(16.dp))
}

@Composable
fun addSong(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 30.dp,
                    bottom = 70.dp
                )
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add song"
            )
        }
    }
}
@Entity
data class PlaylistEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val trackIds: List<Int> // Или преобразуйте в строку при сохранении
)