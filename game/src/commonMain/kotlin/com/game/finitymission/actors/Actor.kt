package com.game.finitymission.actors

import com.game.finitymission.*
import com.game.finitymission.abilities.Ability
import com.game.finitymission.effects.Effect
import com.game.finitymission.effects.Impulse
import com.game.finitymission.motes.Mote
import com.game.finitymission.statistics.Statistic
import com.game.finitymission.statistics.StatisticType
import com.lehaine.littlekt.math.MutableVec2f

open class Actor(
    state: GameState,
    val mass: Float = 1.0f
) : Mote(state) {
    override val type: Type = Type.ACTOR

    // Physics
    var position = MutableVec2f(0f, 0f)
    var velocity = MutableVec2f(0f, 0f)
    var facing = MutableVec2f(1f, 0f) // location of the mouse cursor

    // Game mechanics
    val statistics: LinkedHashMap<StatisticType, Statistic> = LinkedHashMap()
    val abilities: LinkedHashMap<Int, Ability> = LinkedHashMap()
    val effects: LinkedHashMap<MoteId, Effect> = LinkedHashMap()

    init {
        state.addActor(this)
    }

    override fun tick() {
        if(facing.subtract(position).length() < 0.001f) {
            facing.y -= 1f
        }
        position += velocity
    }

    fun addImpulse(duration: Int, force: MutableVec2f, tapered: Boolean = false): Impulse {
        return Impulse(state, this, force, tapered, duration)
    }

    fun addStatistic(statistic: Statistic) {
        statistics[statistic.statisticType] = statistic
    }

    fun removeStatistic(statistic: Statistic) {
        statistics.remove(statistic.statisticType)
    }

    fun getStatistic(statisticType: StatisticType): Statistic? {
        return statistics[statisticType]
    }

    fun stat(statisticType: StatisticType): Double? {
        return statistics[statisticType]?.calculate()
    }

    fun activateAbility(abilityNumber: Int, target: Actor? = null) {
        //abilities[key]?.use(target)
    }

    fun addEffect(effect: Effect) {
        effects[effect.id] = effect
    }

    fun removeEffect(effect: Effect) {
        effects.remove(effect.id)
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }
    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}