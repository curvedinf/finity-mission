package com.game.finitymission

class AbilityActivated(
    state: GameState,
    duration: Int,
    from: Actor,
    var target: Actor? = null,
) : Ability(state, duration, from) {
    open fun activate() {
    }
}