package com.game.finitymission.motes

import com.game.finitymission.GameState
import com.game.finitymission.effects.Effect

class Modifier(
    state: GameState,
    var factor: Double,
    val statistic: Statistic,
    val from: Effect,
) : Mote(state) {
    override val type: Type = Type.MODIFIER
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun deconstruct() {
        super.deconstruct()
        statistic.modifiers.remove(id)
    }
}