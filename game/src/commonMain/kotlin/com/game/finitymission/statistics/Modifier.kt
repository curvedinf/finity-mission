package com.game.finitymission.statistics

import com.game.finitymission.GameState
import com.game.finitymission.effects.Effect
import com.game.finitymission.motes.Mote

class Modifier(
    state: GameState,
    var factor: Double,
    val statistic: Statistic,
    val from: Effect,
) : Mote(state) {
    override val type: Type = Type.MODIFIER

    init {
        statistic.addModifier(this)
    }

    override fun deconstruct() {
        statistic.removeModifier(this)
        super.deconstruct()
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}