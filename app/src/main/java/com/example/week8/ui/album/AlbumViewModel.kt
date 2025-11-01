package com.example.week8.ui.album

import android.util.Log // <-- ADDED THIS IMPORT
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week8.data.repository.MusicRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.week8.data.model.*

class AlbumViewModel(private val repo: MusicRepository) : ViewModel() {

    var state by mutableStateOf(AlbumState())
        private set

    fun loadAlbum(id: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val detail = repo.getAlbumDetail(id).album?.first()
                val tracks = repo.getTracks(id).track ?: emptyList()
                state = state.copy(album = detail, tracks = tracks, isLoading = false)
            } catch (e: Exception) {
                Log.e("AlbumViewModel", "Error loading album", e)
                state = state.copy(error = "Error loading album", isLoading = false)
            }
        }
    }
}

data class AlbumState(
    val album: AlbumDetail? = null,
    val tracks: List<Track> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)