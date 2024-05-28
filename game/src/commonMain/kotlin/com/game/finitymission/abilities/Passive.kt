package com.game.finitymission.abilities

import com.game.finitymission.actors.Actor
import com.game.finitymission.GameState

open class Passive(
    state: GameState,
    duration: Int,
    target: Actor,
    from: Actor,
) : Ability(state, duration, target,from) {

    override fun tick() {
        super.tick()
    }
}