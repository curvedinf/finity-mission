package com.game.finitymission.effects

import com.game.finitymission.GameState
import com.game.finitymission.abilities.Ability
import com.lehaine.littlekt.input.Key
import com.lehaine.littlekt.input.Pointer

class RespondToUserInput(
    state: GameState,
    from: Ability? = null,
    duration: Int? = null,
) : Effect(state, from, duration) {
    override fun tick() {
        super.tick()
        if (duration != null && state.now() >= creationTime + duration) {
            remove()
            return
        }
        val input = state.context.input
        // Add mouse input stubs here
        if (input.isTouching(Pointer.MOUSE_LEFT)) {
            // Handle left mouse button press
        }
        if (input.isTouching(Pointer.MOUSE_RIGHT)) {
            // Handle right mouse button press
        }
        if (input.isTouching(Pointer.MOUSE_MIDDLE)) {
            // Handle middle mouse button press
        }
        // Add keyboard input stubs here
        if (input.isKeyPressed(Key.A)) {
            // Handle A key press
        }
        if (input.isKeyPressed(Key.D)) {
            // Handle D key press
        }
        if (input.isKeyPressed(Key.W)) {
            // Handle W key press
        }
        if (input.isKeyPressed(Key.S)) {
            // Handle S key press
        }
    }
}
