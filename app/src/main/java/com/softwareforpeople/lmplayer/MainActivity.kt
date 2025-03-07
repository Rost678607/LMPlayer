package com.softwareforpeople.lmplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.softwareforpeople.lmplayer.ui.screens.ListScreen
import com.softwareforpeople.lmplayer.utils.PermissionManager

class MainActivity : ComponentActivity() {

    private lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = MusicRepository()
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(MusicViewModel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = TracksAdapter(emptyList()) { track ->
            viewModel.toggleFavorite(track.id)
        }
        recyclerView.adapter = adapter

        viewModel.allTracks.observe(this) { tracks ->
            adapter.tracks = tracks
            adapter.notifyDataSetChanged()
        }

        // Например, можно добавить тестовые треки
        val testTrack = Track(1, "Test Track", "Artist", "uri")
        viewModel.addTrack(testTrack)

        enableEdgeToEdge()

        permissionManager = PermissionManager(this)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                ) {
                    ListScreen()
                }
            }
        }
    }
}