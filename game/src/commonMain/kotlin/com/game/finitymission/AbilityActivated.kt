package com.game.finitymission

open class AbilityActivated(
    state: GameState,
    duration: Int,
    target: Actor,
    from: Actor,
) : Ability(state, duration, target,from) {
    open fun activate() {
    }
}