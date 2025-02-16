package com.softwareforpeople.lmplayer.data

import android.net.Uri

data class Song(
    var ID: UInt,
    var Name: String,
    var Author: String = "Unknown",
    var URI: Uri,
    val Duration: UInt,
    var Rating: UByte = 60U,
    var Speed: Float = 1.0F
)