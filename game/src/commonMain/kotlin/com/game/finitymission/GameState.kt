package com.game.finitymission

class GameState() {
    var tickNumber: Int = 0
    val motes: LinkedHashMap<Int, Mote> = LinkedHashMap()
    val actors: LinkedHashMap<Int, Actor> = LinkedHashMap()
    val abilities: LinkedHashMap<Int, Ability> = LinkedHashMap()
    val effects: LinkedHashMap<Int, Effect> = LinkedHashMap()
    val eventListeners: LinkedHashMap<Event.EventType, LinkedHashMap<Int, Mote>> = LinkedHashMap()
    fun now(): Int {
        return tickNumber
    }

    fun tick() {
        tickNumber++
        for(actor in actors.values) {
            actor.tick()
        }
        for(ability in abilities.values) {
            ability.tick()
        }
        for(effect in effects.values) {
            effect.tick()
        }
    }

    fun addMote(mote: Mote) {
        motes[mote.id] = mote
    }
    fun removeMote(mote: Mote) {
        motes.remove(mote.id)
    }
    fun addActor(actor: Actor) {
        actors[actor.id] = actor
        addMote(actor)
    }
    fun removeActor(actor: Actor) {
        actors.remove(actor.id)
        removeMote(actor)
    }
    fun addAbility(ability: Ability) {
        abilities[ability.id] = ability
        addMote(ability)
    }
    fun removeAbility(ability: Ability) {
        abilities.remove(ability.id)
        removeMote(ability)
    }
    fun addEffect(effect: Effect) {
        effects[effect.id] = effect
        addMote(effect)
    }
    fun removeEffect(effect: Effect) {
        effects.remove(effect.id)
        removeMote(effect)
    }

    fun registerEventListener(mote: Mote, eventType: Event.EventType) {
        if(eventType !in eventListeners) {
            eventListeners[eventType] = LinkedHashMap()
        }
        eventListeners[eventType]?.set(mote.id, mote)
    }
    fun unregisterEventListener(mote: Mote, eventType: Event.EventType) {
        eventListeners[eventType]?.remove(mote.id)
    }
    fun triggerEvent(eventType: Event.EventType, target: Mote? = null, from: Mote? = null) {
        val event = Event(this, eventType, target, from)
        eventListeners[eventType]?.values?.forEach {
            it.onEvent(event)
        }
    }
}