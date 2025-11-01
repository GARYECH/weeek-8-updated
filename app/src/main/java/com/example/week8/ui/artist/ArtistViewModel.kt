package com.example.week8.ui.artist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week8.data.repository.MusicRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.week8.data.model.*

class ArtistViewModel(private val repo: MusicRepository) : ViewModel() {

    var state by mutableStateOf(ArtistState())
        private set


    init { loadArtist("Justin Bieber") }

    fun loadArtist(name: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val artist = repo.getArtist(name).artists?.first()
                val albums = repo.getAlbums(name).album ?: emptyList()
                state = state.copy(artist = artist, albums = albums, isLoading = false)
            } catch (e: Exception) {
                Log.e("ArtistViewModel", "Failed to load artist", e)
                state = state.copy(error = "Failed loading data", isLoading = false)
            }
        }
    }
}

data class ArtistState(
    val artist: Artist? = null,
    val albums: List<Album> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)