package com.game.finitymission

import com.lehaine.littlekt.createLittleKtApp
import com.lehaine.littlekt.graphics.Color

fun main() {
    createLittleKtApp {
        title = Settings.GAME_TITLE
        backgroundColor = Color.DARK_GRAY
        canvasId = "canvas"
    }.start {
        Game(it)
    }
}