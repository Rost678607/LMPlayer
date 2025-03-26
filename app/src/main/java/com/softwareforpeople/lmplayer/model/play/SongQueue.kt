package com.softwareforpeople.lmplayer.model.play

import kotlin.random.Random
import com.softwareforpeople.lmplayer.data.Song

class SongQueue(val songs: List<Song>) {
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
        val finalContinuation: MutableList<UInt> = mutableListOf()

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

    fun deleteSong(songIndex: Int) {
        if(currentSongIndex != songIndex) {
            queue.removeAt(songIndex)
            if(currentSongIndex > songIndex) {
                currentSongIndex--
            }
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

    fun goNextSong() {
        if(currentSongIndex < queue.size - 1) {
            currentSongIndex++
        } else {
            makeQueue(true)
            currentSongIndex = 0
        }
    }

    fun goPrevSong() {
        if(currentSongIndex > 0) {
            currentSongIndex--
        }
    }
}