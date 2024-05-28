package com.game.finitymission.effects

import com.game.finitymission.GameState
import com.game.finitymission.abilities.Ability

class Friction(
    state: GameState,
    val frictionFactor: Float,  // Friction factor to apply
    from: Ability? = null,
    duration: Int? = null,
) : Effect(state, from, duration) {

    override fun tick() {
        super.tick()
        target?.let {
            it.velocity *= frictionFactor
        }
    }
}