package com.softwareforpeople.lmplayer.model.play

import kotlin.random.Random
import android.content.Context
import com.softwareforpeople.lmplayer.data.Song
import com.softwareforpeople.lmplayer.model.play.Player

class SongQueueManager(val context: Context, val songs: MutableList<Song>) {
    private val player = Player(context)

    private var queue: MutableList<UInt> = mutableListOf() // список ID песен в очереди
    private var currentSongIndex: Int = 0 // индекс текущего трека

    // составление очереди воспроизведения с нуля
    fun makeQueue(useRatings: Boolean) {
        queue.clear()
        currentSongIndex = 0

        while(queue.size < 200) {
            continueQueue(useRatings)
        }
    }

    // продливание очереди
    private fun continueQueue(useRatings: Boolean) {
        var finalContinuation: MutableList<UInt> = mutableListOf()

        if(useRatings) {
            for (song in songs) {
                if (Random.nextInt(0, 100) < song.Rating) {
                    finalContinuation.add(song.ID)
                }
            }
        } else {
            finalContinuation.addAll(songs.map { it.ID })
        }
        finalContinuation.shuffle()
        queue.addAll(finalContinuation)
    }

    // замена текущего элемента
    fun changeCurrentSong(newSongID: UInt) {
        queue.add(currentSongIndex + 1, newSongID)
    }

    // изменение позиции элемента
    fun moveSong(from: Int, to: Int) {
        val song = queue[from]

        queue.removeAt(from)
        queue.add(to, song)

        if (from == currentSongIndex) {
            currentSongIndex = to
        } else if (from < currentSongIndex && to > currentSongIndex) {
            currentSongIndex--
        } else if (from > currentSongIndex && to < currentSongIndex) {
            currentSongIndex++
        }
    }

    // добавление следующего элемента
    fun addNextSong(songID: UInt) {
        queue.add(currentSongIndex + 1, songID)

    }

    // текущий элемент
    fun getCurrentSongID(): UInt {
        return queue[currentSongIndex]
    }
}