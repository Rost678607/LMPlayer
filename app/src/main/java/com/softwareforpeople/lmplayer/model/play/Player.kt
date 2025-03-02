package com.softwareforpeople.lmplayer.model.play

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.softwareforpeople.lmplayer.data.SongManager

class Player(val context: Context) {
    private val mp = MediaPlayer()
    private val songManager = SongManager(context)

    private var currentSongID: UInt = 0U

    // Получение Uri песни по ID
    fun getUriByID(songID: UInt): Uri? {
        val song = songManager.getSongById(songID)
        return song?.URI
    }

    fun play(newSongID: UInt) {
        mp.stop()
        mp.reset()
        currentSongID = newSongID

        val uri = getUriByID(newSongID)

        // Если Uri найден, то начинаем воспроизведение
        if (uri != null) {
            try {
                mp.setDataSource(context, uri)
                mp.prepare()
                mp.start()
            } catch (e: Exception) {
                // Обработка ошибок, например, если файл не найден
                e.printStackTrace()
            }
        } else {
            // Обработка ситуации, когда Uri не найден
            println("Uri for song ID $newSongID not found")
        }
    }
}