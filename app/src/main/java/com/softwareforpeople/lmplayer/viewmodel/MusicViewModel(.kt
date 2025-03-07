package com.softwareforpeople.lmplayer.viewmodel

class MusicViewModel(private val musicRepository: MusicRepository) : ViewModel() {

    // LiveData для отслеживания всех треков
    val allTracks: LiveData<List<Track>> = MutableLiveData(musicRepository.getTracks())

    // LiveData для избранных треков
    val favoriteTracks: LiveData<List<Track>> = MutableLiveData(musicRepository.getFavoriteTracks())

    fun toggleFavorite(trackId: Int) {
        musicRepository.toggleFavorite(trackId)
        (favoriteTracks as MutableLiveData).postValue(musicRepository.getFavoriteTracks()) // обновляем список избранного
        (allTracks as MutableLiveData).postValue(musicRepository.getTracks()) // обновляем все треки
    }

    fun addTrack(track: Track) {
        musicRepository.addTrack(track)
        (allTracks as MutableLiveData).postValue(musicRepository.getTracks()) // обновляем все треки
    }
}