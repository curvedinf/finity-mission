package com.game.finitymission.events

import com.game.finitymission.GameState
import com.game.finitymission.motes.Mote
import com.game.finitymission.motes.NameMoteMap

open class Event(
    state: GameState,
    val eventType: EventType,
    val motes: NameMoteMap? = null,
) : Mote(state) {
    override val type: Type = Type.EVENT

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }
    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}