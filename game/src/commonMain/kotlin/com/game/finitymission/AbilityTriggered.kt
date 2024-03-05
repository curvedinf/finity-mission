package com.game.finitymission

class AbilityTriggered(
    state: GameState,
    duration: Int,
    from: Actor,
    abilityType: AbilityType,
) : Ability(state, duration, from, abilityType) {
    override fun onEvent(event: Event) {
        trigger(event)
    }
    open fun trigger(event: Event) {
    }
}