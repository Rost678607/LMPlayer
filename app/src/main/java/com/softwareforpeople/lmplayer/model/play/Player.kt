package com.softwareforpeople.lmplayer.model.play

import android.net.Uri
import android.media.MediaPlayer
import android.provider.Contacts.Intents.UI

class Player(songID: UInt) {
    private val mp = MediaPlayer()
    var currentSongID: UInt = 0U

    //fun getUriByID(songID: UInt): Uri {}

    fun play(newSongID: UInt) {
        mp?.stop()
        mp?.reset()
        currentSongID = newSongID

    }
}