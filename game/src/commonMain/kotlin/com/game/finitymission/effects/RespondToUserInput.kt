package com.game.finitymission.effects

import com.game.finitymission.GameState
import com.game.finitymission.abilities.Ability
import com.game.finitymission.actors.Character
import com.game.finitymission.events.EventType
import com.lehaine.littlekt.input.Key
import com.lehaine.littlekt.input.Pointer

class RespondToUserInput(
    state: GameState,
    target: Character,
    from: Ability
) : Effect(state, target, from, null) {
    private val previousInput = mutableMapOf(
        Pointer.MOUSE_LEFT to false,
        Pointer.MOUSE_RIGHT to false,
        Pointer.MOUSE_MIDDLE to false,
        Key.W to false,
        Key.A to false,
        Key.S to false,
        Key.D to false
    )

    override fun tick() {
        // Mouse Input
        handleMouseInput(Pointer.MOUSE_LEFT, EventType.SHOOTING_START, EventType.SHOOTING_END)
        handleMouseInput(Pointer.MOUSE_RIGHT, EventType.ABILITY_2_START, EventType.ABILITY_2_END)
        handleMouseInput(Pointer.MOUSE_MIDDLE, EventType.ABILITY_1_START, EventType.ABILITY_1_END)

        // Keyboard Input
        handleKeyInput(Key.W, EventType.THRUST_UP_START, EventType.THRUST_UP_END)
        handleKeyInput(Key.S, EventType.THRUST_DOWN_START, EventType.THRUST_DOWN_END)
        handleKeyInput(Key.A, EventType.THRUST_LEFT_START, EventType.THRUST_LEFT_END)
        handleKeyInput(Key.D, EventType.THRUST_RIGHT_START, EventType.THRUST_RIGHT_END)

        super.tick()
    }

    private fun handleMouseInput(pointer: Pointer, pressedEventType: EventType, releasedEventType: EventType) {
        val pressed = state.context.input.isTouching(pointer)
        if (pressed && !previousInput[pointer]!!) {
            state.triggerEvent(pressedEventType)
        } else if (!pressed && previousInput[pointer]!!) {
            state.triggerEvent(releasedEventType)
        }
        previousInput[pointer] = pressed
    }

    private fun handleKeyInput(key: Key, pressedEventType: EventType, releasedEventType: EventType) {
        val pressed = state.context.input.isKeyPressed(key)
        if (pressed && !previousInput[key]!!) {
            state.triggerEvent(pressedEventType)
        } else if (!pressed && previousInput[key]!!) {
            state.triggerEvent(releasedEventType)
        }
        previousInput[key] = pressed
    }
}
