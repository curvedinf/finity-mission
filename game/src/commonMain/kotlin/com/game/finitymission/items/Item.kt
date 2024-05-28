package com.game.finitymission.items

import com.game.finitymission.GameState
import com.game.finitymission.motes.Mote
import com.game.finitymission.actors.Actor

class Item(
    state: GameState,
    val name: String,
    val icon: String,
    var owner: Actor? = null,
) : Mote(state) {

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }

    fun use() {

    }
}