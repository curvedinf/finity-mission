package com.game.finitymission.abilities

import com.game.finitymission.actors.Actor
import com.game.finitymission.events.Event
import com.game.finitymission.GameState

open class Triggered(
    state: GameState,
    owner: Actor,
    duration: Int? = null
) : Ability(state, owner, duration) {
    override fun onEvent(event: Event) {
        trigger(event)
    }
    open fun trigger(event: Event) {
    }
}