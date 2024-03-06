package com.game.finitymission

class Statistic(
    state: GameState,
    var maximumValue: Double,
    var currentValue: Double,
    var owner: Actor,
) : Mote(state) {
    override val type: Type = Type.STATISTIC
    enum class StatisticType(val label: String) {
        // Skills
        PILOTING("Piloting"),
        GUNNERY("Gunnery"),
        ENGINEERING("Engineering"),
        TECHNOLOGY("Technology"),
        INFLUENCE("Influence"),
        LOGISTICS("Logistics"),
        // Counters
        LEVEL("Level"),
        EXPERIENCE("Experience"),
        MONEY("Money"),
        SKILL_POINTS("Skill Points"),
        // Derivative Stats
        ARMOR("Armor"),
        SHIELDS("Shields"),
        ENERGY("Energy"),
        LUCK("Luck"),
        SPEED("Speed"),
        DAMAGE("Damage"),
        FIRE_RATE("Fire Rate"),
        ABILITY_COOLDOWN("Ability Cooldown"),
        EQUIPMENT_SLOTS("Equipment Slots"),
        REPAIR_DELAY("Repair Delay"),
        REPAIR_RATE("Repair Rate"),
        SHIELD_RECHARGE_DELAY("Shield Recharge Delay"),
        SHIELD_RECHARGE_RATE("Shield Recharge Rate"),
        CRITICAL_CHANCE("Critical Chance"),
        CRITICAL_DODGE_CHANCE("Critical Dodge Chance"),
        AMMO_CHANCE("Ammo Chance"),
        // Resistances
        ARMOR_RESISTANCE("Armor Resistance"),
        ARMOR_FIRE_RESISTANCE("Armor Fire Resistance"),
        ARMOR_COLD_RESISTANCE("Armor Cold Resistance"),
        ARMOR_EXPLOSIVE_RESISTANCE("Armor Explosive Resistance"),
        ARMOR_CORROSION_RESISTANCE("Armor Corrosion Resistance"),
        ARMOR_RADIATION_RESISTANCE("Armor Radiation Resistance"),
        ARMOR_ELECTRICAL_RESISTANCE("Armor Electrical Resistance"),
        SHIELD_RESISTANCE("Shield Resistance"),
        SHIELD_FIRE_RESISTANCE("Shield Fire Resistance"),
        SHIELD_COLD_RESISTANCE("Shield Cold Resistance"),
        SHIELD_EXPLOSIVE_RESISTANCE("Shield Explosive Resistance"),
        SHIELD_CORROSION_RESISTANCE("Shield Corrosion Resistance"),
        SHIELD_RADIATION_RESISTANCE("Shield Radiation Resistance"),
        SHIELD_ELECTRICAL_RESISTANCE("Shield Electrical Resistance"),
    }

    var cachedCalculatedValue: Double = 0.0
    var cachedTick: Int = -1
    val modifiers: LinkedHashMap<MoteId, Modifier> = LinkedHashMap()
    fun addModifier(factor: Double, effect: Effect) {
        val modifier = Modifier(state, factor,this, effect)
        modifiers[modifier.id] = modifier
    }

    fun calculate() : Double {
        if (state.now() == cachedTick) {
            return cachedCalculatedValue
        }
        var totalFactor = 1.0
        for (modifier in modifiers.values) {
            totalFactor += modifier.factor
        }
        cachedCalculatedValue = currentValue * totalFactor
        cachedTick = state.now()
        return cachedCalculatedValue
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}