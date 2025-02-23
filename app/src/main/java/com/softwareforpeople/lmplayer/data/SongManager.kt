package com.softwareforpeople.lmplayer.data

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.ui.input.key.type
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SongManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("SongData", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val songListKey = "songList"

    // загрузка списка треков
    fun loadSongs(): MutableList<Song> {
        val json = sharedPreferences.getString(songListKey, null)
        return if (json != null) {
            val type = object : TypeToken<MutableList<Song>>() {}.type
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    // сохранение списка треков
    fun saveSongs(songs: List<Song>) {
        val json = gson.toJson(songs)
        sharedPreferences.edit().putString(songListKey, json).apply()
    }

    // добавление трека
    fun addSong(song: Song) {
        val songs = loadSongs().toMutableList()
        val newSong = song.copy(ID = getNextAvailableId(songs))
        songs.add(newSong)
        recalculateIds(songs)
        saveSongs(songs)
    }

    // удаление трека
    fun removeSong(songId: UInt) {
        val songs = loadSongs().toMutableList()
        songs.removeAll { it.ID == songId }
        recalculateIds(songs)
        saveSongs(songs)
    }

    // получение URI трека по ID
    fun getSongById(songId: UInt): Song? {
        val songs = loadSongs()
        return songs.find { it.ID == songId }
    }

    // пересчет ID треков
    private fun recalculateIds(songs: MutableList<Song>) {
        songs.sortBy { it.ID }
        songs.forEachIndexed { index, song ->
            song.ID = (index + 1).toUInt()
        }
    }

    // получение следующего доступного ID
    private fun getNextAvailableId(songs: List<Song>): UInt {
        return if (songs.isEmpty()) {
            1U
        } else {
            songs.maxOf { it.ID } + 1U
        }
    }
}