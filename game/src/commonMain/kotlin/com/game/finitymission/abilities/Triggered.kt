package com.game.finitymission.abilities

import com.game.finitymission.actors.Actor
import com.game.finitymission.events.Event
import com.game.finitymission.GameState

class Triggered(
    state: GameState,
    duration: Int,
    target: Actor,
    from: Actor,
) : Ability(state, duration, target, from) {
    override fun onEvent(event: Event) {
        trigger(event)
    }
    open fun trigger(event: Event) {
    }
}