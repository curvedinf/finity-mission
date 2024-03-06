package com.game.finitymission

import com.lehaine.littlekt.math.MutableVec2f

class Impulse(
    state: GameState,
    duration: Int? = null,
    val force: MutableVec2f,
    val tapered: Boolean = true,
) : Effect(state, null, duration) {
    override fun tick() {
        if (tapered) {
            val taperedForce = force.scale(1.0f - (state.now() - creationTime) / duration!!)
            target?.velocity?.plusAssign(taperedForce)
        } else {
            target?.velocity?.plusAssign(force)
        }
        super.tick()
    }
}