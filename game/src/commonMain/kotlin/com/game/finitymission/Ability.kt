package com.game.finitymission

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
