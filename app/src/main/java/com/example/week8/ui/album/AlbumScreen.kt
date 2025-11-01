package com.example.week8.ui.album

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumScreen(state: AlbumState, onBackClicked: () -> Unit) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(state.album?.strAlbum ?: "") },
                navigationIcon = {

                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->


        when {

            state.isLoading -> Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                Alignment.Center
            ) {
                CircularProgressIndicator()
            }


            state.error != null -> Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                Alignment.Center
            ) {
                Text(state.error!!, color = MaterialTheme.colorScheme.error)
            }


            else -> {

                LazyColumn(modifier = Modifier.fillMaxSize()) {


                    item {
                        AsyncImage(
                            model = state.album?.strAlbumThumb,
                            contentDescription = "Album Cover",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                        )
                        Text(
                            state.album?.strAlbum ?: "",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 4.dp)
                        )

                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                state.album?.intYearReleased ?: "",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                            if (!state.album?.intYearReleased.isNullOrBlank() && !state.album?.strGenre.isNullOrBlank()) {
                                Text(
                                    " • ",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                )
                            }
                            Text(
                                state.album?.strGenre ?: "",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                        }

                        Text(state.album?.strDescriptionEN ?: "", Modifier.padding(12.dp))

                        Text(
                            "Tracks",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 4.dp)
                        )
                    }


                    items(state.tracks) { track ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                track.strTrack,
                                Modifier.weight(1f),
                                style = MaterialTheme.typography.bodyLarge
                            )

                            val durationSeconds = track.intDuration?.toIntOrNull()?.div(1000) ?: 0
                            val minutes = durationSeconds / 60
                            val seconds = durationSeconds % 60
                            val formattedDuration = String.format("%d:%02d", minutes, seconds)
                            Text(
                                formattedDuration,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                        }
                        Divider(modifier = Modifier.padding(horizontal = 12.dp))
                    }
                }
            }
        }
    }
}