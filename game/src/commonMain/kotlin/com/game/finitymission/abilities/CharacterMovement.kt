package com.game.finitymission.abilities

import com.game.finitymission.GameState
import com.game.finitymission.actors.Character
import com.game.finitymission.effects.Friction
import com.game.finitymission.effects.Impulse
import com.game.finitymission.events.Event
import com.game.finitymission.events.EventType
import com.game.finitymission.statistics.StatisticType
import com.lehaine.littlekt.math.MutableVec2f

class CharacterMovement(
    state: GameState,
    owner: Character,
) : Triggered(state, owner) {
    private var impulseUp: Impulse? = null
    private var impulseDown: Impulse? = null
    private var impulseLeft: Impulse? = null
    private var impulseRight: Impulse? = null

    init {
        listenForEvent(EventType.THRUST_UP_START)
        listenForEvent(EventType.THRUST_DOWN_START)
        listenForEvent(EventType.THRUST_LEFT_START)
        listenForEvent(EventType.THRUST_RIGHT_START)
        listenForEvent(EventType.THRUST_UP_END)
        listenForEvent(EventType.THRUST_DOWN_END)
        listenForEvent(EventType.THRUST_LEFT_END)
        listenForEvent(EventType.THRUST_RIGHT_END)
        Friction(state, owner)
    }

    override fun onEvent(event: Event) {
        val speed = owner.stat(StatisticType.SPEED) ?: 0.0
        when (event.eventType) {
            EventType.THRUST_UP_START -> impulseUp = createThrust(0f, -1f, speed)
            EventType.THRUST_DOWN_START -> impulseDown = createThrust(0f, 1f, speed)
            EventType.THRUST_LEFT_START -> impulseLeft = createThrust(-1f, 0f, speed)
            EventType.THRUST_RIGHT_START -> impulseRight = createThrust(1f, 0f, speed)
            EventType.THRUST_UP_END -> {
                impulseUp?.remove()
                impulseUp = null
            }
            EventType.THRUST_DOWN_END -> {
                impulseDown?.remove()
                impulseDown = null
            }
            EventType.THRUST_LEFT_END -> {
                impulseLeft?.remove()
                impulseLeft = null
            }
            EventType.THRUST_RIGHT_END -> {
                impulseRight?.remove()
                impulseRight = null
            }
            else -> {}
        }
    }

    private fun createThrust(x: Float, y: Float, speed: Double): Impulse {
        val force = MutableVec2f(x, y).scale(speed.toFloat())
        return owner.addImpulse(force)
    }

    override fun deconstruct() {
        impulseUp?.remove()
        impulseDown?.remove()
        impulseLeft?.remove()
        impulseRight?.remove()
        super.deconstruct()
    }
}
