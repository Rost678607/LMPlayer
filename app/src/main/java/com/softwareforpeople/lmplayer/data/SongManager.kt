package com.softwareforpeople.lmplayer.data

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

class SongManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("SongData", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val songListKey = "songList"

    // Кэшированный список песен
    private var cachedSongs: MutableList<Song>? = null

    // Загрузка списка треков
    fun loadSongs(): List<Song> {
        if (cachedSongs == null) {
            val json = sharedPreferences.getString(songListKey, null)
            cachedSongs = if (json != null) {
                try {
                    val type = object : TypeToken<MutableList<Song>>() {}.type
                    gson.fromJson(json, type)
                } catch (e: Exception) {
                    mutableListOf()
                }
            } else {
                mutableListOf()
            }
        }
        return cachedSongs!!.toList()
    }

    // Сохранение списка треков
    private fun saveSongs(songs: List<Song>) {
        val json = gson.toJson(songs)
        sharedPreferences.edit() { putString(songListKey, json) }
        cachedSongs = songs.toMutableList()
    }

    // Добавление трека
    fun addSong(song: Song) {
        val songs = loadSongs().toMutableList()
        val newId = getNextAvailableId(songs)
        val newSong = song.copy(ID = newId)
        songs.add(newSong)
        saveSongs(songs)
    }

    // Удаление трека
    fun removeSong(songId: Long) {
        val songs = loadSongs().toMutableList()
        songs.removeAll { it.ID == songId }
        saveSongs(songs)
    }

    // Получение трека по ID
    fun getSongById(songId: Long): Song? {
        return loadSongs().find { it.ID == songId }
    }

    // Получение URI трека по ID
    fun getSongUriById(songId: Long): Uri {
        return getSongById(songId)?.URI ?: Uri.EMPTY
    }

    // Получение рейтинга трека по ID
    fun getSongRatingById(songId: Long): Float {
        return getSongById(songId)?.Rating ?: 0.0F
    }

    // Установка рейтинга трека
    fun setSongRating(songId: Long, rating: Float) {
        val songs = loadSongs().toMutableList()
        val song = songs.find { it.ID == songId }
        if (song != null) {
            song.Rating = rating
            saveSongs(songs)
        }
    }

    // Получение следующего доступного ID
    private fun getNextAvailableId(songs: List<Song>): Long {
        return if (songs.isEmpty()) {
            1L
        } else {
            songs.maxOf { it.ID } + 1L
        }
    }
}