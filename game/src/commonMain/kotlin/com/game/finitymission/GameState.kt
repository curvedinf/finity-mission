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
    }

    fun addMote(mote: Mote) {
        motes[mote.id] = mote
    }

    fun removeMote(mote: Mote) {
        motes.remove(mote.id)
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
}