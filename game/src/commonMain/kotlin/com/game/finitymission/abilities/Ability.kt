package com.game.finitymission.abilities

import com.game.finitymission.actors.Actor
import com.game.finitymission.effects.Effect
import com.game.finitymission.GameState
import com.game.finitymission.motes.Mote

open class Ability(
    state: GameState,
    val duration: Int,
    var target: Actor,
    val from: Actor,
) : Mote(state) {
    override val type: Type = Type.ABILITY

    val effects: LinkedHashMap<Int, Effect> = LinkedHashMap()

    open fun tick() {
        if(state.now() >= creationTime + duration) {

        }
    }

    open fun use(target: Actor? = null) {
        for(effect in effects.values) {
            //effect.activate()
        }
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}
