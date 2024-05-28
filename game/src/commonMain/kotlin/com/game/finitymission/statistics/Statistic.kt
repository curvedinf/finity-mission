package com.game.finitymission.statistics

import com.game.finitymission.GameState
import com.game.finitymission.actors.Actor
import com.game.finitymission.motes.Mote

class Statistic(
    state: GameState,
    var owner: Actor,
    val statisticType: StatisticType,
) : Mote(state) {
    override val type: Type = Type.STATISTIC
    var current: Double = statisticType.starting
    var maximum: Double? = statisticType.maximum

    var cachedValue: Double? = null
    val modifiers: LinkedHashMap<MoteId, Modifier> = LinkedHashMap()

    init {
        owner.addStatistic(this)
    }

    override fun deconstruct() {
        owner.removeStatistic(this)
        super.deconstruct()
    }

    fun addModifier(modifier: Modifier) {
        modifiers[modifier.id] = modifier
        clearCache()
    }

    fun removeModifier(modifier: Modifier) {
        modifiers.remove(modifier.id)
        clearCache()
    }

    fun calculate() : Double {
        if(cachedValue != null) {
            return cachedValue!!
        }
        var totalFactor = 1.0
        for (modifier in modifiers.values) {
            totalFactor += modifier.factor
        }
        cachedValue = current * totalFactor
        if(maximum != null && cachedValue!! > maximum!!) {
            cachedValue = maximum
        }
        return cachedValue!!
    }

    fun clearCache() {
        cachedValue = null
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}