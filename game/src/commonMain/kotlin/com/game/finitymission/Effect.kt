package com.game.finitymission

class Effect(
    state: GameState,
    val target: Actor? = null,
    val from: Ability? = null,
    val duration: Double? = null,
) : Mote(state) {
    override val type: Type = Type.EFFECT
    val modifiers: LinkedHashMap<Int, Modifier> = LinkedHashMap()
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }

    fun tick() {
        TODO("Not yet implemented")
    }
}