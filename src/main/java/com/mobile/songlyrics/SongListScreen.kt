package com.mobile.songlyrics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListScreen(navController: NavController) {

    val context = LocalContext.current
    val prefs = remember { SongPreferences(context) }
    var songs by remember { mutableStateOf(prefs.getSongs()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Músicas Salvas") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Search.route)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Buscar")
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            itemsIndexed(songs) { index, song ->

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.Detail.createRoute(index)
                            )
                        }
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(song.title, style = MaterialTheme.typography.titleMedium)
                        Text(song.band, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

        }
    }

    LaunchedEffect(Unit) {
        songs = prefs.getSongs()
    }
}