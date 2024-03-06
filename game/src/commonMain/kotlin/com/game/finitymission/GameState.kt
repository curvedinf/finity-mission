package com.game.finitymission

class GameState() {
    var tickNumber: Int = 0
    val motes: LinkedHashMap<Mote.MoteId, Mote> = LinkedHashMap()
    val actors: LinkedHashMap<Mote.MoteId, Actor> = LinkedHashMap()
    val abilities: LinkedHashMap<Mote.MoteId, Ability> = LinkedHashMap()
    val effects: LinkedHashMap<Mote.MoteId, Effect> = LinkedHashMap()
    val eventListeners: LinkedHashMap<Event.EventType, LinkedHashMap<Mote.MoteId, Mote>> = LinkedHashMap()

    // Removal queue
    val moteRemovalQueue: MutableList<Mote> = mutableListOf()

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
        for(mote in moteRemovalQueue) {
            mote.destroy()
            removeMote(mote)
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
    fun registerEventListener(mote: Mote, eventType: Event.EventType) {
        if(eventType !in eventListeners) {
            eventListeners[eventType] = LinkedHashMap()
        }
        eventListeners[eventType]?.set(mote.id, mote)
    }
    fun unregisterEventListener(mote: Mote, eventType: Event.EventType) {
        eventListeners[eventType]?.remove(mote.id)
    }
    fun triggerEvent(eventType: Event.EventType, subject: Mote? = null, target: Mote? = null, from: Mote? = null) {
        val event = Event(this, eventType, subject, target, from)
        eventListeners[eventType]?.values?.forEach {
            it.onEvent(event)
        }
    }
}