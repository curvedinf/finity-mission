package com.game.finitymission.effects

import com.game.finitymission.events.Event
import com.game.finitymission.GameState
import com.game.finitymission.motes.Modifier
import com.game.finitymission.motes.Mote
import com.game.finitymission.abilities.Ability
import com.game.finitymission.actors.Actor

open class Effect(
    state: GameState,
    val from: Ability? = null,
    val duration: Int? = null,
) : Mote(state) {
    var target: Actor? = null

    override val type: Type = Type.EFFECT
    val modifiers: LinkedHashMap<MoteId, Modifier> = LinkedHashMap()

    open fun tick() {
        // If the duration is not null, this effect is timed and expires
        if(duration != null && duration + creationTime <= state.now()) {
            remove()
        }
    }

    fun registerTarget(newTarget: Actor) {
        target = newTarget
        newTarget.targetEffect(this)
        modifiers += createModifers(newTarget).associateBy { it.id }
        state.triggerEvent(Event.EventType.EFFECT_CREATED, this, newTarget, from)
    }

    override fun deconstruct() {
        modifiers.values.forEach { it.remove() }
        target?.removeEffect(this)
        state.triggerEvent(Event.EventType.EFFECT_DESTROYED, this, target, from)
    }

    open fun createModifers(target: Actor): List<Modifier> {
        val modifiers = mutableListOf<Modifier>()

        // Add more specific modifiers based on the game mechanics and requirements

        return modifiers
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}