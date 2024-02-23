package com.game.finitymission

open class Actor(
    game: Game,
) : Mote(game) {
    override val type: Type = Type.ACTOR

    val statistics: LinkedHashMap<String, Statistic> = LinkedHashMap()
    val targetEffects: LinkedHashMap<String, Effect> = LinkedHashMap()
    val fromEffects: LinkedHashMap<String, Effect> = LinkedHashMap()
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }
    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }

    fun tick() {
        for(effect in this.fromEffects.values) {
            effect.tick()
        }
    }
}