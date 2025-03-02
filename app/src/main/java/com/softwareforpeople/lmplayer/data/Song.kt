package com.softwareforpeople.lmplayer.data

import android.net.Uri

data class Song(
    var ID: UInt,
    var Name: String,
    var Author: String = "Unknown",
    var URI: Uri,
    val Duration: UInt,
    var Rating: Byte = 90,                   //  0-100
    var Speed: Float = 1.0F
)

data class Playlist(
    val name: String,
    val tracks: List<Track>
)

data class Track(
    val title: String,
    val artist: String,
    val uri: String
)