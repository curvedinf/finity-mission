package com.game.finitymission.motes

import com.game.finitymission.GameState
import com.game.finitymission.events.Event
import com.game.finitymission.events.EventType


/**
 * Represents a base game object with a lifecycle managed by the [GameState].
 * Motes can represent various game entities, including actors, abilities, effects, etc.
 *
 * @property state The [GameState] this Mote belongs to.
 */
abstract class Mote(
    val state: GameState,
    val duration: Int? = null
) {
    /**
     * Enumerates the possible types of [Mote]s.
     */
    enum class Type {
        MOTE,
        ABILITY,
        STATISTIC,
        MODIFIER,
        EVENT,
        EFFECT,
        ACTOR,
    }

    /**
     * The type of this [Mote]. Defaults to [Type.MOTE].
     */
    open val type: Type = Type.MOTE

    /**
     * The game tick when this [Mote] was created.
     */
    var creationTime: Int = state.now()

    /**
     * A unique identifier for a [Mote].
     *
     * @property value The integer value of this ID.
     */
    data class MoteId(val value: Int)

    companion object {
        private var nextId: Int = 1
    }

    /**
     * The unique [MoteId] of this [Mote].
     */
    val id: MoteId = MoteId(nextId++)

    /**
     * A mutable set of [Event.EventType]s that this [Mote] is listening for.
     */
    open val listenEvents: MutableSet<EventType> = mutableSetOf()

    /**
     * Serializes this [Mote] to a byte array.
     *
     * @return A byte array representation of this [Mote].
     */
    abstract fun serialize(): ByteArray

    /**
     * Deserializes a [Mote] from a byte array.
     *
     * @param data The byte array to deserialize from.
     */
    abstract fun deserialize(data: ByteArray)

    /**
     * Called when an [Event] this [Mote] is listening for is triggered.
     *
     * @param event The [Event] that was triggered.
     */
    open fun onEvent(event: Event) {}

    /**
     * Registers this [Mote] to listen for a specific [Event.EventType].
     *
     * @param eventType The type of event to listen for.
     */
    open fun listenForEvent(eventType: EventType) {
        listenEvents.add(eventType)
        state.registerEventListener(this, eventType)
    }

    /**
     * Unregisters this [Mote] from listening for a specific [Event.EventType].
     *
     * @param eventType The type of event to stop listening for.
     */
    open fun stopListeningForEvent(eventType: EventType) {
        listenEvents.remove(eventType)
        state.unregisterEventListener(this, eventType)
    }

    /**
     * Queues this [Mote] for removal from the [GameState].
     */
    open fun remove() {
        state.queueRemoveMote(this)
    }

    /**
     * Called when this [Mote] is about to be removed from the [GameState].
     * This function should be overridden to perform any necessary cleanup.
     */
    open fun deconstruct() {
        // Any cleanup needed
    }

    /**
     * Called every game tick.
     * This function should be overridden to perform any necessary logic.
     */
    open fun tick() {
        // If the duration is not null, this effect is timed and expires
        if(duration != null && duration + creationTime <= state.now()) {
            remove()
        }
    }
}
