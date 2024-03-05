package com.game.finitymission

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