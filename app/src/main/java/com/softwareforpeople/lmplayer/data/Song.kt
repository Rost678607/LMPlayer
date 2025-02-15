package com.softwareforpeople.lmplayer.data

import android.net.Uri

data class Song(
    var ID: UInt,
    var Name: String,
    var Author: String,
    var URI: Uri,
    val Duration: UInt,
    var Rating: UInt
)