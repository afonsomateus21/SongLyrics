package com.mobile.songlyrics

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SongPreferences(context: Context) {

    private val prefs = context.getSharedPreferences("songs_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getSongs(): List<Song> {
        val json = prefs.getString("songs", null) ?: return emptyList()
        val type = object : TypeToken<List<Song>>() {}.type
        return gson.fromJson(json, type)
    }

    fun saveSong(song: Song) {
        val songs = getSongs().toMutableList()
        songs.add(song)

        prefs.edit()
            .putString("songs", gson.toJson(songs))
            .apply()
    }
}
