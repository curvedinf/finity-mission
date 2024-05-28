package com.game.finitymission.effects

import com.game.finitymission.GameState
import com.lehaine.littlekt.math.MutableVec2f

class Impulse(
    state: GameState,
    duration: Int? = null,
    val force: MutableVec2f,
    val tapered: Boolean = true,
) : Effect(state, null, duration) {
    override fun tick() {
        target?.let {
            val mass = it.mass
            val appliedForce = if (tapered) {
                force.scale(1.0f - (state.now() - creationTime) / duration!!).scale(1 / mass)
            } else {
                force.scale(1 / mass)
            }
            it.velocity += appliedForce
        }
        super.tick()
    }
}
