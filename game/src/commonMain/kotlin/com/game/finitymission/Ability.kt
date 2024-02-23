package com.game.finitymission

open class Ability(
    state: GameState,
    val duration: Int,
    val from: Actor,
) : Mote(state) {
    val effects: LinkedHashMap<Int, Effect> = LinkedHashMap()
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }

    open fun tick() {
        if(state.now() >= creationTime + duration) {

        }
    }
}
