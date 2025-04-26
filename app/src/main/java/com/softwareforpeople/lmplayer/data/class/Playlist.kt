package com.softwareforpeople.lmplayer.data.`class`

import com.softwareforpeople.lmplayer.data.Song

data class Playlist(
    var name: String,
    var tracks: List<Song>,

    )