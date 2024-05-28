package com.game.finitymission.effects

import com.game.finitymission.GameState
import com.game.finitymission.abilities.Ability
import com.game.finitymission.actors.Actor
import com.game.finitymission.statistics.StatisticType

class Friction(
    state: GameState,
    target: Actor,
    from: Ability? = null,
    duration: Int? = null,
) : Effect(state, target, from, duration) {

    override fun tick() {
        super.tick()
        target.let {
            val friction = target.stat(StatisticType.PHYSICS_FRICTION)!!.toFloat()
            it.velocity = it.velocity.scale(friction)
        }
    }
}