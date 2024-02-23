package com.game.finitymission

class Statistic(
    game: Game,
    name: String,
    var value: Double,
    var actor: Actor? = null,
) : Mote(game, name) {
    override val type: Type = Type.STATISTIC

    val modifiers: LinkedHashMap<String, Modifier> = LinkedHashMap()
    fun addModifier(name: String, multiplier: Double, contributingEffect: Effect? = null) {
        val modifier = Modifier(name, multiplier, this, contributingEffect)
        this.modifiers[modifier.name] = modifier
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}