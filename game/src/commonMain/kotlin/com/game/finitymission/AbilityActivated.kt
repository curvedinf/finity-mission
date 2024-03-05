package com.game.finitymission

open class AbilityActivated(
    state: GameState,
    duration: Int,
    from: Actor,
    var target: Actor? = null,
    abilityType: AbilityType,
) : Ability(state, duration, from, abilityType) {
    open fun activate() {
    }
}