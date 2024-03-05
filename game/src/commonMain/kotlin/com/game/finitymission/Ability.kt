package com.game.finitymission

open class Ability(
    state: GameState,
    val duration: Int,
    val from: Actor? = null,
    val abilityType: AbilityType,
) : Mote(state) {
    override val type: Type = Type.ABILITY

    enum class AbilityType {
        MOTE,
        ABILITY,
        STATISTIC,
        MODIFIER,
        EVENT,
        EFFECT,
        ACTOR,
    }

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

    open fun use(target: Actor? = null) {
        for(effect in effects.values) {
            //effect.activate()
        }
    }
}
