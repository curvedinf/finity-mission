package com.game.finitymission.effects

import com.game.finitymission.GameState
import com.game.finitymission.abilities.Ability
import com.game.finitymission.actors.Actor

class Friction(
    state: GameState,
    val frictionFactor: Float,  // Friction factor to apply
    target: Actor,
    from: Ability? = null,
    duration: Int? = null,
) : Effect(state, target, from, duration) {

    override fun tick() {
        super.tick()
        target?.let {
            it.velocity *= frictionFactor
        }
    }
}