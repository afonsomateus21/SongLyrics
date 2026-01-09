package com.mobile.songlyrics

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Detail : Screen("detail/{index}") {
        fun createRoute(index: Int) = "detail/$index"
    }
    object Search : Screen("search")
}