package com.example.week8

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.week8.data.repository.MusicRepository
import com.example.week8.ui.album.AlbumViewModel
import com.example.week8.ui.artist.ArtistViewModel

class ViewModelFactory(private val repository: MusicRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtistViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlbumViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
