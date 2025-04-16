package com.softwareforpeople.lmplayer.data

import android.net.Uri

data class Song(
    // song
    val ID: Long,
    var Name: String,
    var Author: String = "Unknown",
    var URI: Uri,
    val Duration: UInt,

    // individual parameters
    var Rating: Float = 90F,                   //  0-100
    var Speed: Float = 1.0F,

    // file
    var FileName: String,
    val FileSize: Int,
    val FileType: String,
    var FilePath: String
)