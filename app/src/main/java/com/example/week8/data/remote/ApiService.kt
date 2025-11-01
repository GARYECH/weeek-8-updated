package com.example.week8.data.remote

import com.example.week8.data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.php")
    suspend fun searchArtist(@Query("s") artist: String): ArtistResponse

    @GET("searchalbum.php")
    suspend fun getAlbums(@Query("s") artist: String): AlbumResponse

    @GET("album.php")
    suspend fun getAlbumDetail(@Query("m") albumId: String): AlbumDetailResponse

    @GET("track.php")
    suspend fun getTracks(@Query("m") albumId: String): TrackResponse
}
