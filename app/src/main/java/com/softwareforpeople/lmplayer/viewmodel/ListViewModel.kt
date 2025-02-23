package com.softwareforpeople.lmplayer.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softwareforpeople.lmplayer.data.Song
import com.softwareforpeople.lmplayer.data.SongManager

class ListViewModel(context: Context) : ViewModel() {

    private val songManager = SongManager(context)

    private val songs = MutableLiveData<List<Song>>(songManager.loadSongs())
    val Songs: LiveData<List<Song>> = this.songs

    fun addSong(song: Song) {
        songManager.addSong(song)
        this.songs.value = songManager.loadSongs()
    }

    fun removeSong(songId: UInt) {
        songManager.removeSong(songId)
        this.songs.value = songManager.loadSongs()
    }

    fun loadSongs() {
        this.songs.value = songManager.loadSongs()
    }
}