package com.game.finitymission.actors

import com.game.finitymission.*
import com.game.finitymission.abilities.Ability
import com.game.finitymission.effects.Effect
import com.game.finitymission.effects.Friction
import com.game.finitymission.effects.Impulse
import com.game.finitymission.motes.Mote
import com.game.finitymission.motes.Statistic
import com.lehaine.littlekt.math.MutableVec2f

open class Actor(
    state: GameState,
    val mass: Float = 1.0f,
    val friction: Float = 0.9f,
) : Mote(state) {
    override val type: Type = Type.ACTOR

    // Physics
    var position = MutableVec2f(0f, 0f)
    var velocity = MutableVec2f(0f, 0f)
    var facing = MutableVec2f(1f, 0f) // location of the mouse cursor

    // Game mechanics
    val statistics: LinkedHashMap<Statistic.StatisticType, Statistic> = LinkedHashMap()
    val abilities: LinkedHashMap<VirtualKey, Ability> = LinkedHashMap()
    val effects: LinkedHashMap<MoteId, Effect> = LinkedHashMap()

    init {
        val frictionEffect = Friction(state, frictionFactor = friction)
        frictionEffect.registerTarget(this)
        state.addEffect(frictionEffect)
    }

    fun tick() {
        if(facing.subtract(position).length() < 0.001f) {
            facing.y -= 1f
        }
        position += velocity
        velocity = velocity.scale(friction)
    }

    fun addImpulse(duration: Int, force: MutableVec2f, tapered: Boolean = false): Impulse {
        val impulse = Impulse(state, duration, force, tapered)
        impulse.registerTarget(this)
        return impulse
    }

    fun getStat(statType: Statistic.StatisticType): Double {
        return statistics[statType]?.calculate() ?: 0.0
    }

    fun useAbility(key: VirtualKey, target: Actor? = null) {
        abilities[key]?.use(target)
    }

    fun targetEffect(effect: Effect) {
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