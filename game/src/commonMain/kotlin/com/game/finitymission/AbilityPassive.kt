package com.game.finitymission

open class AbilityPassive(
    state: GameState,
    duration: Int,
    from: Actor,
    abilityType: AbilityType,
) : Ability(state, duration, from, abilityType) {

    override fun tick() {
        super.tick()
    }
}