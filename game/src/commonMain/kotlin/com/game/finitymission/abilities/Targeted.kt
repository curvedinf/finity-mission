package com.game.finitymission.abilities

import com.game.finitymission.actors.Actor
import com.game.finitymission.GameState
import com.lehaine.littlekt.math.Vec2f

abstract class Targeted(
    state: GameState,
    owner: Actor,
    duration: Int? = null,
) : Ability(state, owner, duration) {
    var selfTargeting = false

    open fun use(target: Actor): Boolean {
        return false
    }
    open fun use(target: Vec2f): Boolean {
        return false
    }
}