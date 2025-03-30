package com.softwareforpeople.lmplayer.model.play

import android.content.Context
import com.softwareforpeople.lmplayer.data.SongManager

class SongRatingChanger(context: Context) {
    val songManager = SongManager(context)

    // увеличение рейтинга на
    private fun addRating(songID: Long, rating: Int) {
        val currentRating: Float = songManager.getSongRatingById(songID)
        var newRating: Float = currentRating + rating
        if (newRating > 100.0F) {
            newRating = 100.0F
        }
        songManager.setSongRating(songID, newRating)
    }

    // уменьшение рейтинга на
    private fun removeRating(songID: Long, rating: Int) {
        val currentRating: Float = songManager.getSongRatingById(songID)
        var newRating: Float = currentRating - rating
        if (newRating < 5.0F) {
            newRating = 5.0F
        }
        songManager.setSongRating(songID, newRating)
    }

    // изменение рейтинга при пропуске трека
    fun skipSong(songID: Long, duration: Int, currentTime: Int) {
        val listenedFraction = currentTime.toFloat() / duration
        if (listenedFraction < 0.1) {
            removeRating(songID, 10)
        } else if (listenedFraction < 0.3) {
            removeRating(songID, 5)
        } else if (listenedFraction < 0.7) {
            removeRating(songID, 2)
        } else if (listenedFraction > 0.8) {
            addRating(songID, 4)
        }
    }

    // изменение рейтинга при перемотке
    fun rewindSong(songID: Long, duration: Int, prevTime: Int, currentTime: Int) {
        val delta = currentTime - prevTime
        if (delta > 5000) {
            removeRating(songID, 1)
        } else if (delta < -5000) {
            addRating(songID, 1)
        }
    }
}