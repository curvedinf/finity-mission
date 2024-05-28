package com.game.finitymission.effects

import com.game.finitymission.events.Event
import com.game.finitymission.GameState
import com.game.finitymission.statistics.Modifier
import com.game.finitymission.motes.Mote
import com.game.finitymission.abilities.Ability
import com.game.finitymission.actors.Actor
import com.game.finitymission.events.EventType
import com.game.finitymission.motes.MoteMapName
import com.game.finitymission.motes.NameMoteMap

open class Effect(
    state: GameState,
    var target: Actor,
    val from: Ability? = null,
    duration: Int? = null,
) : Mote(state, duration) {
    override val type: Type = Type.EFFECT
    val modifiers: LinkedHashMap<MoteId, Modifier> = LinkedHashMap()

    init {
        target.addEffect(this)
        modifiers += createModifers(target).associateBy { it.id }
        linkedMapOf(
            MoteMapName.OBJECT to this,
            MoteMapName.TARGET to target,
            MoteMapName.FROM to from
        ).let {
            state.triggerEvent(
                EventType.EFFECT_CREATED,
                it
            )
        }
    }

    override fun deconstruct() {
        modifiers.values.forEach { it.remove() }
        target.removeEffect(this)
        linkedMapOf(
            MoteMapName.OBJECT to this,
            MoteMapName.TARGET to target,
            MoteMapName.FROM to from
        ).let {
            state.triggerEvent(
                EventType.EFFECT_DESTROYED,
                it
            )
        }
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