package com.game.finitymission

open class AbilityPassive(
    state: GameState,
    duration: Int,
    target: Actor,
    from: Actor,
) : Ability(state, duration, target,from) {

    override fun tick() {
        super.tick()
    }
}