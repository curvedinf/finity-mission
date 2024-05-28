package com.game.finitymission.actors

import com.game.finitymission.GameState
import com.game.finitymission.statistics.Statistic
import com.game.finitymission.statistics.StatisticType

class Character(
    state: GameState,
    mass: Float = 1.0f
) : Actor(state, mass) {
    init {
        // Add all statistics
        for (statisticType in StatisticType.values()) {
            Statistic(state, this, statisticType)
        }
    }
}
