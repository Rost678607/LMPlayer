package com.softwareforpeople.lmplayer.model.play

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.softwareforpeople.lmplayer.data.Song
import com.softwareforpeople.lmplayer.data.SongManager
import com.softwareforpeople.lmplayer.model.play.SongRatingChanger

class Player(val context: Context, playList: List<Song>) {
    private val mp = MediaPlayer()
    private val queue = SongQueue(playList)
    private val rating = SongRatingChanger(context)
    private val songManager = SongManager(context)
    private var useRating = true

    init {
        mp.setOnCompletionListener {
            next()
        }
    }

    // воспроизведение трека
    fun play(newSongID: Long) {
        val uri = songManager.getSongUriById(newSongID)

        stop()
        mp.setDataSource(context, uri)
        mp.prepare()
        mp.start()
    }

    // музыкальная пауза
    fun pause() {
        if(mp.isPlaying) {
            mp.pause()
        } else {
            mp.start()
        }
    }

    // остановка воспроизведения
    fun stop() {
        if(isPlaying()) {
            mp.stop()
            mp.reset()
            mp.release()
        }
    }

    // воспроизведение следующего трека
    fun next() {
        if(useRating) {
            rating.skipSong(queue.getCurrentSongID(), getDuration(), getCurrentPosition())
        }

        queue.goNextSong()
        play(queue.getCurrentSongID())
    }

    // воспроизведение предыдущего трека
    fun prev() {
        queue.goPrevSong()
        play(queue.getCurrentSongID())
    }

    // получить текщее время воспроизведения
    fun getCurrentPosition(): Int {
        return mp.currentPosition
    }

    // получить длительность трека
    fun getDuration(): Int {
        return mp.duration
    }

    // перемотка трека
    fun seekTo(position: Int) {
        if(useRating) {
            rating.rewindSong(queue.getCurrentSongID(), getDuration(), getCurrentPosition(), position)
        }

        mp.seekTo(position)
    }

    //
    fun setUseRating(use: Boolean) {
        useRating = use
    }

    // проверка на воспроизведение
    fun isPlaying(): Boolean {
        return mp.isPlaying
    }
}