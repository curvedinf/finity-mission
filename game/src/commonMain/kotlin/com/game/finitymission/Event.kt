package com.game.finitymission

open class Event(
    game: Game,
    name: String,
    val eventType: EventType,
    val from: Mote? = null,
    val target: Mote? = null,
) : Mote(game, name) {
    override val type: MoteType = MoteType.EVENT
    enum class EventType{
        TICK,
        INPUT_PRESSED,
        INPUT_RELEASED,
        ACTOR_CREATED,
        ACTOR_DESTROYED,
        ACTOR_COLLIDING,
        ABILITY_CREATED,
        ABILITY_DESTROYED,
        TARGETED_ABILITY_CREATED,
        TARGETED_ABILITY_ACTIVATED,
        TARGETED_ABILITY_DEACTIVATED,
        TARGETED_ABILITY_DESTROYED,
        TRIGGERED_ABILITY_CREATED,
        TRIGGERED_ABILITY_ACTIVATED,
        TRIGGERED_ABILITY_DEACTIVATED,
        TRIGGERED_ABILITY_DESTROYED,
        PASSIVE_ABILITY_CREATED,
        PASSIVE_ABILITY_ACTIVATED,
        PASSIVE_ABILITY_DEACTIVATED,
        PASSIVE_ABILITY_DESTROYED,
        EFFECT_CREATED,
        EFFECT_DESTROYED,
        MODIFIER_CREATED,
        MODIFIER_DESTROYED,
        AREA_ENTERED,
        AREA_EXITED,
    }
    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }
    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}