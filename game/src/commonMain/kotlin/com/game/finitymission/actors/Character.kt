package com.game.finitymission.actors

import com.game.finitymission.GameState
import com.game.finitymission.abilities.CharacterMovement
import com.game.finitymission.statistics.Statistic
import com.game.finitymission.statistics.StatisticType

class Character(
    state: GameState,
    frontTexturePath: String? = null,
    backTexturePath: String? = null,
    mass: Float = 1.0f,
) : Actor(state, frontTexturePath, mass) {
    init {
        // Add all statistics
        for (statisticType in StatisticType.entries) {
            Statistic(state, this, statisticType)
        }
        // All characters can move
        CharacterMovement(state, this)
    }
}
