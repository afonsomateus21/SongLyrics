package com.mobile.songlyrics

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSongScreen(navController: NavController) {

    val context = LocalContext.current
    val prefs = remember { SongPreferences(context) }

    var band by remember { mutableStateOf("") }
    var song by remember { mutableStateOf("") }
    var lyrics by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Buscar Música") })
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            OutlinedTextField(
                value = band,
                onValueChange = { band = it },
                label = { Text("Banda") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = song,
                onValueChange = { song = it },
                label = { Text("Música") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    loading = true
                    lyrics = ""

                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val encodedBand = Uri.encode(band.trim())
                            val encodedSong = Uri.encode(song.trim())

                            val url = "https://api.lyrics.ovh/v1/$encodedBand/$encodedSong"

                            Log.d("LyricsURL", url)

                            val response = RetrofitInstance.api.getLyrics(url)

                            withContext(Dispatchers.Main) {
                                lyrics = response.lyrics
                                loading = false
                            }

                        } catch (e: Exception) {
                            Log.e("LyricsError", "Erro real:", e)

                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Erro ao buscar música",
                                    Toast.LENGTH_SHORT
                                ).show()
                                loading = false
                            }
                        }
                    }
                }
            ) {
                Text("Pesquisar")
            }

            Spacer(Modifier.height(16.dp))

            if (loading) {
                CircularProgressIndicator()
            }

            if (lyrics.isNotBlank()) {

                Text(lyrics)

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        prefs.saveSong(
                            Song(band, song, lyrics)
                        )
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}
