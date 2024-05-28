package com.game.finitymission.abilities

import com.game.finitymission.actors.Actor
import com.game.finitymission.effects.RespondToUserInput
import com.game.finitymission.GameState

class UserInput(
    state: GameState,
    duration: Int,
    target: Actor,  // typically the user's Actor
    from: Actor,
) : Ability(state, duration, target, from) {
    private var respondToUserInput: RespondToUserInput? = null

    init {
        val newEffect = RespondToUserInput(state, this, duration)
        newEffect.registerTarget(target)
        respondToUserInput = newEffect
        state.addEffect(newEffect)
    }

    override fun deconstruct() {
        respondToUserInput?.remove()
        super.deconstruct()
    }
}
