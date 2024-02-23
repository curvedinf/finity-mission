package com.game.finitymission

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
}