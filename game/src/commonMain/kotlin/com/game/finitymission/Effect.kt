package com.game.finitymission

open class Effect(
    state: GameState,
    val from: Ability? = null,
    val duration: Int? = null,
) : Mote(state) {
    var target: Actor? = null

    override val type: Type = Type.EFFECT
    val modifiers: LinkedHashMap<MoteId, Modifier> = LinkedHashMap()

    open fun tick() {
        // If the duration is not null, this effect is timed and expires
        if(duration != null) {
            if(duration + creationTime <= state.now()) {
                remove()
            }
        }
    }

    fun registerTarget(target: Actor) {
        this.target = target
        target.targetEffect(this)
        val modifiers = createModifers(target)
        for(modifier in modifiers) {
            this.modifiers[modifier.id] = modifier
        }
        state.triggerEvent(Event.EventType.EFFECT_CREATED, this, target, from)
    }

    override fun destroy() {
        for(modifier in modifiers.values) {
            modifier.remove()
        }
        state.triggerEvent(Event.EventType.EFFECT_DESTROYED, this, target, from)
    }

    fun createModifers(target: Actor): List<Modifier> {
        return listOf<Modifier>()
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}