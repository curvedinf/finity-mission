package com.game.finitymission.abilities

import com.game.finitymission.actors.Actor
import com.game.finitymission.GameState

open class Activated(
    state: GameState,
    duration: Int,
    target: Actor,
    from: Actor,
) : Ability(state, duration, target,from) {
    open fun activate() {
    }
}