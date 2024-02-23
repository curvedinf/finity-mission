package com.game.finitymission

class AbilityTriggered(
    state: GameState,
    duration: Int,
    from: Actor,
) : Ability(state, duration, from) {
    override fun onEvent(event: Event) {
        trigger(event)
    }
    open fun trigger(event: Event) {
    }
}