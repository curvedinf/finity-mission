package com.game.finitymission

open class Actor(
    state: GameState,
) : Mote(state) {
    override val type: Type = Type.ACTOR

    val statistics: LinkedHashMap<Statistic.StatisticType, Statistic> = LinkedHashMap()
    val abilities: LinkedHashMap<VirtualKey, Ability> = LinkedHashMap()
    val targetEffects: LinkedHashMap<Int, Effect> = LinkedHashMap()
    val fromEffects: LinkedHashMap<Int, Effect> = LinkedHashMap()
    fun tick() {
    }

    fun getStat(statType: Statistic.StatisticType): Double {
        return statistics[statType]?.calculate() ?: 0.0
    }
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }
    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}