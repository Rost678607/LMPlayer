package com.softwareforpeople.lmplayer.model.play

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.softwareforpeople.lmplayer.data.Song
import com.softwareforpeople.lmplayer.data.SongManager

class Player(val context: Context, playList: List<Song>) {
    private val mp = MediaPlayer()
    private val queue = SongQueue(playList)
    private val songManager = SongManager(context)

    // воспроизведение трека
    fun play(newSongID: UInt) {
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
        queue.goNextSong()
        play(queue.getCurrentSongID())
    }

    // воспроизведение предыдущего трека
    fun prev() {
        queue.goPrevSong()
        play(queue.getCurrentSongID())
    }

    //
    fun getCurrentPosition(): Int {
        return mp.currentPosition
    }

    // получить длительность трека
    fun getDuration(): Int {
        return mp.duration
    }

    // перемотка трека
    fun seekTo(position: Int) {
        mp.seekTo(position)
    }

    // проверка на воспроизведение
    fun isPlaying(): Boolean {
        return mp.isPlaying
    }
}