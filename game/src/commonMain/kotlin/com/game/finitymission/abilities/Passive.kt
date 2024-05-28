package com.game.finitymission.abilities

import com.game.finitymission.actors.Actor
import com.game.finitymission.GameState

abstract class Passive(
    state: GameState,
    owner: Actor,
    duration: Int? = null,
) : Ability(state, owner, duration) {
}