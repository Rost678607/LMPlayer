package com.softwareforpeople.lmplayer.data

class PlaykistManager{
    val myPlaylist = Playlist("Мой плейлист", listOf())
    val newTrack = Song(
        ID = TODO(),
        Name = TODO(),
        Author = TODO(),
        URI = TODO(),
        Duration = TODO(),
        Rating = TODO(),
        Speed = TODO(),
        isFavorite = TODO()
    )
    

    // Добавление трека в плейлист
    val updatedPlaylist = addPlaylist(myPlaylist, newTrack)

    // Переключение избранного
    val favoriteTrack = toggleFavorite(newTrack)
    
    fun addPlaylist(playlist: Playlist, song: Song): Playlist {
        return playlist.copy(tracks = (playlist.tracks + song))
    }

    fun removePlaylist(playlist: Playlist, song: Song): Playlist {
        return playlist.copy(tracks = (playlist.tracks - song))
    }
    
    fun toggleFavorite(song: Song): Song {
        song.isFavorite = !song.isFavorite
        return song
    }
    fun getFavoriteTracks(playlist: Playlist): List<Song> {
        return playlist.tracks.filter { it.isFavorite }
    }
    
}