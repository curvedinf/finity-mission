package com.game.finitymission

open class Actor(
    state: GameState,
) : Mote(state) {
    override val type: Type = Type.ACTOR

    val statistics: LinkedHashMap<Int, Statistic> = LinkedHashMap()
    val abilities: LinkedHashMap<Int, Ability> = LinkedHashMap()
    val targetEffects: LinkedHashMap<Int, Effect> = LinkedHashMap()
    val fromEffects: LinkedHashMap<Int, Effect> = LinkedHashMap()
    fun tick() {
    }
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }
    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}