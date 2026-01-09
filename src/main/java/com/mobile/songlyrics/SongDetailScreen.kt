package com.mobile.songlyrics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongDetailScreen(index: Int) {

    val context = LocalContext.current
    val prefs = remember { SongPreferences(context) }
    val songs = prefs.getSongs()

    if (index !in songs.indices) {
        Text("Música não encontrada")
        return
    }

    val song = songs[index]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(song.title) }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(song.band, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(16.dp))
            Text(song.lyrics)
        }
    }
}