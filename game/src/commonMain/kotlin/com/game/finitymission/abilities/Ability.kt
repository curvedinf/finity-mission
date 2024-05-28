package com.game.finitymission.abilities

import com.game.finitymission.actors.Actor
import com.game.finitymission.effects.Effect
import com.game.finitymission.GameState
import com.game.finitymission.motes.Mote
import com.lehaine.littlekt.graphics.shader.generator.type.vec.Vec2

abstract class Ability(
    state: GameState,
    var owner: Actor,
    duration: Int? = null,
) : Mote(state, duration) {
    override val type: Type = Type.ABILITY

    val effects: LinkedHashMap<Int, Effect> = linkedMapOf()

    init {
        state.addAbility(this)
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}
