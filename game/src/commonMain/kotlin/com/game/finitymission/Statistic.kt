package com.game.finitymission

class Statistic(
    state: GameState,
    var value: Double,
    var actor: Actor,
) : Mote(state) {
    override val type: Type = Type.STATISTIC

    val modifiers: LinkedHashMap<Int, Modifier> = LinkedHashMap()
    fun addModifier(factor: Double, effect: Effect) {
        val modifier = Modifier(state, factor,this, effect)
        modifiers[modifier.id] = modifier
    }
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}