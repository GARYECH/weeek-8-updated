package com.example.week8

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.week8.data.remote.ApiService
import com.example.week8.data.repository.MusicRepository
import com.example.week8.ui.album.AlbumViewModel
import com.example.week8.ui.album.AlbumScreen
import com.example.week8.ui.artist.ArtistViewModel
import com.example.week8.ui.artist.ArtistScreen
import com.example.week8.ui.theme.ArtistTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ArtistApp() {

    val api = Retrofit.Builder()
        .baseUrl("https://www.theaudiodb.com/api/v1/json/123/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    val repo = MusicRepository(api)
    val factory = ViewModelFactory(repo)
    val nav = rememberNavController()

    ArtistTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(nav, startDestination = "artist") {

                composable("artist") {
                    val vm: ArtistViewModel = viewModel(factory = factory)
                    ArtistScreen(vm.state) { id ->
                        nav.navigate("album/$id")
                    }
                }



                composable("album/{id}") { back ->
                    val id = back.arguments?.getString("id")!!
                    val vm: AlbumViewModel = viewModel(factory = factory)
                    LaunchedEffect(Unit) { vm.loadAlbum(id) }


                    AlbumScreen(
                        state = vm.state,
                        onBackClicked = { nav.navigateUp() }
                    )
                }


            }
        }
    }
}