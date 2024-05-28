package com.game.finitymission

import com.game.finitymission.abilities.Ability
import com.game.finitymission.actors.Actor
import com.game.finitymission.effects.Effect
import com.game.finitymission.events.Event
import com.game.finitymission.events.EventType
import com.game.finitymission.motes.IdMoteMap
import com.game.finitymission.motes.Mote
import com.game.finitymission.motes.NameMoteMap
import com.lehaine.littlekt.Context
import com.lehaine.littlekt.math.MutableVec2f


/**
 * This is a tooltip comment that will show up when hovering over the function.
 */
class GameState(val context: Context) {
    var tickNumber: Int = 0
    val motes: IdMoteMap = LinkedHashMap()
    val actors: IdMoteMap = LinkedHashMap()
    val abilities: IdMoteMap = LinkedHashMap()
    val effects: IdMoteMap = LinkedHashMap()
    val eventListeners: LinkedHashMap<EventType, IdMoteMap> = LinkedHashMap()

    // Removal queue
    val moteRemovalQueue: MutableList<Mote> = mutableListOf()

    // World properties
    val characterFriction: Float = 0.9f

    fun now(): Int {
        return tickNumber
    }

    fun tick() {
        tickNumber++

        actors.values.forEach { it?.tick() }
        abilities.values.forEach { it?.tick() }
        effects.values.forEach { it?.tick() }

        emptyMoteRemovalQueue()
    }

    // Mote management
    fun addMote(mote: Mote) {
        motes[mote.id] = mote
    }
    fun removeMote(mote: Mote) {
        motes.remove(mote.id)
    }
    fun queueRemoveMote(mote: Mote) {
        moteRemovalQueue.add(mote)
    }
    fun emptyMoteRemovalQueue() {
        moteRemovalQueue.forEach {
            it.deconstruct()
            removeMote(it)
        }
        moteRemovalQueue.clear()
    }

    // Actor management
    fun addActor(actor: Actor) {
        actors[actor.id] = actor
        addMote(actor)
    }
    fun removeActor(actor: Actor) {
        actors.remove(actor.id)
        removeMote(actor)
    }

    // Ability management
    fun addAbility(ability: Ability) {
        abilities[ability.id] = ability
        addMote(ability)
    }
    fun removeAbility(ability: Ability) {
        abilities.remove(ability.id)
        removeMote(ability)
    }

    // Effect management
    fun addEffect(effect: Effect) {
        effects[effect.id] = effect
        addMote(effect)
    }
    fun removeEffect(effect: Effect) {
        effects.remove(effect.id)
        removeMote(effect)
    }

    // Event management
    fun registerEventListener(mote: Mote, eventType: EventType) {
        eventListeners.getOrPut(eventType) { LinkedHashMap() }[mote.id] = mote
    }

    fun unregisterEventListener(mote: Mote, eventType: EventType) {
        eventListeners[eventType]?.remove(mote.id)
    }

    fun triggerEvent(eventType: EventType, motes: NameMoteMap? = null) {
        val event = Event(this, eventType, motes)
        eventListeners[eventType]?.values?.forEach { it?.onEvent(event) }
    }

    // Actor querying
    fun getActorsInRadius(position: MutableVec2f, radius: Float): List<Actor> =
        actors.values.filterIsInstance<Actor>().filter {
            position.distance(it.position) <= radius
        }
}