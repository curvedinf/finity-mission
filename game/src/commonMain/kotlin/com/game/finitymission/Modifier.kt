package com.game.finitymission

class Modifier(
    game: Game,
    name: String,
    var multiplier: Double,
    val statistic: Statistic,
    val contributingEffect: Effect,
) : Mote(game, name) {
    override val type: Type = Type.MODIFIER
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }

}