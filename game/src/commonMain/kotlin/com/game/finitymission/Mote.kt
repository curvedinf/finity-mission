package com.game.finitymission

abstract class Mote (
    val state: GameState
) {

    enum class Type {
        MOTE,
        ABILITY,
        STATISTIC,
        MODIFIER,
        EVENT,
        EFFECT,
        ACTOR,
    }
    open val type: Type = Type.MOTE

    var creationTime: Int = state.now()

    data class MoteId(val value: Int)
    companion object {
        var nextId: Int = 1
    }
    val id: MoteId = MoteId(nextId++)

    open val listenEvents: MutableSet<Event.EventType> = mutableSetOf()

    abstract fun serialize() : ByteArray
    abstract fun deserialize(data: ByteArray)
    open fun onEvent(event: Event) {}

    open fun listenForEvent(eventType: Event.EventType) {
        listenEvents.add(eventType)
        state?.registerEventListener(this, eventType)
    }

    open fun stopListeningForEvent(eventType: Event.EventType) {
        listenEvents.remove(eventType)
        state?.unregisterEventListener(this, eventType)
    }

    open fun destroy() {
        state.removeMote(this)
    }
}