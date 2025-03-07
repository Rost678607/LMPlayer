package com.softwareforpeople.lmplayer.viewmodel

import com.softwareforpeople.lmplayer.data.Song

class MusicViewModel(private val musicRepository: SongManager) : ViewModelFactory{

    // LiveData для отслеживания всех треков
    val allTracks: LiveData<List<Song>> = MutableLiveData(musicRepository.getTracks())

    // LiveData для избранных треков
    val favoriteTracks: LiveData<List<Song>> = MutableLiveData(musicRepository.getFavoriteTracks())

    fun toggleFavorite(trackId: Int) {
        musicRepository.toggleFavorite(trackId)
        (favoriteTracks as MutableLiveData).postValue(musicRepository.getFavoriteTracks()) // обновляем список избранного
        (allTracks as MutableLiveData).postValue(musicRepository.getTracks()) // обновляем все треки
    }

    fun addTrack(track: Song) {
        SongManager.addTrack(track)
        (allTracks as MutableLiveData).postValue(musicRepository.getTracks()) // обновляем все треки
    }
}