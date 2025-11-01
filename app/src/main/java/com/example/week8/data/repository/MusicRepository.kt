package com.example.week8.data.repository

import com.example.week8.data.remote.ApiService

class MusicRepository(private val api: ApiService) {
    suspend fun getArtist(name: String) = api.searchArtist(name)
    suspend fun getAlbums(name: String) = api.getAlbums(name)
    suspend fun getAlbumDetail(id: String) = api.getAlbumDetail(id)
    suspend fun getTracks(id: String) = api.getTracks(id)
}
