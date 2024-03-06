package com.game.finitymission

class AbilityTriggered(
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