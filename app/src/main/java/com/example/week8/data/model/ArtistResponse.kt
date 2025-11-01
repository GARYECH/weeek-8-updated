package com.example.week8.data.model

data class ArtistResponse(val artists: List<Artist>?)
data class Artist(
    val strArtist: String,
    val strGenre: String?,
    val strBiographyEN: String?,
    val strArtistThumb: String?
)

data class AlbumResponse(val album: List<Album>?)
data class Album(
    val idAlbum: String,
    val strAlbum: String,
    val intYearReleased: String?,
    val strGenre: String?,
    val strAlbumThumb: String?
)

data class AlbumDetailResponse(val album: List<AlbumDetail>?)
data class AlbumDetail(
    val strAlbum: String,
    val intYearReleased: String?,
    val strGenre: String?,
    val strAlbumThumb: String?,
    val strDescriptionEN: String?
)

data class TrackResponse(val track: List<Track>?)
data class Track(
    val idTrack: String,
    val strTrack: String,
    val intDuration: String?
)
