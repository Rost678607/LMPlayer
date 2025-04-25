package com.softwareforpeople.lmplayer.data

import android.net.Uri

data class Song(
    var ID: String,
    var Name: String,
    var Author: String = "Unknown",
    var URI: Uri,
    val Duration: UInt,
    var Rating: Byte = 90,                   //  0-100
    var Speed: Float = 1.0F,
    var isFavorite: Boolean = false
)
