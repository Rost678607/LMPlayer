package com.softwareforpeople.lmplayer.data

import android.content.Context
import android.net.Uri
import com.softwareforpeople.lmplayer.data.Song
import com.softwareforpeople.lmplayer.data.SongManager

class SongFinder(val context: Context) {
    val songManager = SongManager(context)

    fun findLostSong(song: Song): Uri? {
        val allFiles = songManager.getAllAudioFiles()

        val candidates = allFiles.filter { // первичная фильтрация
            it.fileSize == song.FileSize &&
                    it.fileType == song.FileType &&
                    it.duration == song.Duration
        }

        when {
            candidates.isEmpty() -> {
                onSongNotFound(song)
                return null
            }
            candidates.size == 1 -> {
                val found = candidates[0]
                updateSongManager(song, found)
                return found.URI
            }
            else -> {
                // Пытаемся сузить по имени файла
                val nameMatches = candidates.filter { it.fileName == song.FileName }
                when {
                    nameMatches.size == 1 -> {
                        val found = nameMatches[0]
                        updateSongManager(song, found)
                        return found.URI
                    }
                    nameMatches.size > 1 -> {
                        // Если несколько совпадений по имени, пробуем по пути
                        val pathMatches = nameMatches.filter { it.filePath == song.FilePath }
                        when {
                            pathMatches.size == 1 -> {
                                val found = pathMatches[0]
                                updateSongManager(song, found)
                                return found.URI
                            }
                            pathMatches.size > 1 -> {
                                onMultipleSongsFound(song, pathMatches)
                                return null
                            }
                            else -> {
                                onMultipleSongsFound(song, nameMatches)
                                return null
                            }
                        }
                    }
                    else -> {
                        // Если по имени ничего не найдено, пробуем по пути
                        val pathMatches = candidates.filter { it.filePath == song.FilePath }
                        when {
                            pathMatches.size == 1 -> {
                                val found = pathMatches[0]
                                updateSongManager(song, found)
                                return found.URI
                            }
                            pathMatches.size > 1 -> {
                                onMultipleSongsFound(song, pathMatches)
                                return null
                            }
                            else -> {
                                onMultipleSongsFound(song, candidates)
                                return null
                            }
                        }
                    }
                }
            }
        }
    }

    // ничего не найдено
    private fun onSongNotFound(song: Song) {
        // Пустой обработчик, как указано в задании
    }

    // найдено много чего
    private fun onMultipleSongsFound(song: Song, candidates: List<AudioFile>) {
        // Пустой обработчик, как указано в задании
    }

    // обновление записи о треке
    private fun updateSongManager(song: Song, found: AudioFile) {
        songManager.updateSongFileInfo(song.ID, found.URI, found.fileName, found.filePath)
    }
}