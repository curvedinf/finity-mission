package com.game.finitymission

import com.lehaine.littlekt.createLittleKtApp
import com.lehaine.littlekt.graphics.Color

fun main() {
    createLittleKtApp {
        width = 960
        height = 540
        backgroundColor = Color.DARK_GRAY
        title = Settings.GAME_TITLE
    }.start {
        Game(it)
    }
}