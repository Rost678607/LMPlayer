package com.softwareforpeople.lmplayer.viewmodel

import com.softwareforpeople.lmplayer.data.SongManager

class ViewModelFactory(private val repository: SongManager) : ViewModelProvider.Factory {
    override fun <T : ViewModelFactory> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicViewModel::class.java)) {
            return MusicViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}