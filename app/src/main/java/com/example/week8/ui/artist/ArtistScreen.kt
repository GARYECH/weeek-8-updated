package com.example.week8.ui.artist

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape // <-- Pastikan import ini ada
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ArtistScreen(state: ArtistState, onAlbumClick: (String) -> Unit) {
    when {

        state.isLoading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            CircularProgressIndicator()
        }


        state.error != null -> Box(Modifier.fillMaxSize(), Alignment.Center) {
            Text(state.error!!, color = MaterialTheme.colorScheme.error)
        }


        else -> {
            Column(Modifier.verticalScroll(rememberScrollState())) {


                AsyncImage(
                    model = state.artist?.strArtistThumb,
                    contentDescription = "Artist",
                    modifier = Modifier.fillMaxWidth().height(260.dp)
                )


                Text(
                    state.artist?.strArtist ?: "",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 4.dp)
                )


                Text(
                    state.artist?.strGenre ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(horizontal = 12.dp)
                )


                Text(
                    state.artist?.strBiographyEN ?: "",
                    modifier = Modifier.padding(12.dp),
                    maxLines = 5
                )


                Text(
                    "Albums",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 4.dp)
                )


                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(500.dp)
                        .padding(horizontal = 6.dp)
                ) {
                    items(state.albums) { album ->

                        Surface(
                            modifier = Modifier
                                .padding(6.dp)
                                .clickable { onAlbumClick(album.idAlbum) },
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)) // "Outlined"
                        ) {
                            Column {
                                AsyncImage(
                                    model = album.strAlbumThumb,
                                    contentDescription = album.strAlbum,
                                    modifier = Modifier
                                        .height(140.dp)
                                        .fillMaxWidth()
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    album.strAlbum,
                                    maxLines = 1,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}