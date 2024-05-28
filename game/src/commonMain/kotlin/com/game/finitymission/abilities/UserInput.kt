package com.game.finitymission.abilities

import com.game.finitymission.actors.Character
import com.game.finitymission.effects.RespondToUserInput
import com.game.finitymission.GameState

class UserInput(
    state: GameState,
    owner: Character,
) : Passive(state, owner, null) {
    private var respondToUserInput: RespondToUserInput? = null

    init {
        val newRespondToUserInput = RespondToUserInput(state, owner, this)
        respondToUserInput = newRespondToUserInput
        state.addEffect(newRespondToUserInput)
    }

    override fun deconstruct() {
        respondToUserInput?.remove()
        super.deconstruct()
    }
}
