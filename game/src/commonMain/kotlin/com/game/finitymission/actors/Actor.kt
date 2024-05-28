package com.game.finitymission.actors

import com.game.finitymission.*
import com.game.finitymission.abilities.Ability
import com.game.finitymission.effects.Effect
import com.game.finitymission.effects.Impulse
import com.game.finitymission.motes.Mote
import com.game.finitymission.statistics.Statistic
import com.game.finitymission.statistics.StatisticType
import com.lehaine.littlekt.file.vfs.readTexture
import com.lehaine.littlekt.graphics.Texture
import com.lehaine.littlekt.graphics.g2d.SpriteBatch
import com.lehaine.littlekt.graphics.g2d.TextureSlice
import com.lehaine.littlekt.graphics.slice
import com.lehaine.littlekt.graphics.textureMesh
import com.lehaine.littlekt.math.MutableVec2f
import kotlinx.coroutines.runBlocking

open class Actor(
    state: GameState,
    val texturePath: String? = null,
    val textureScale: Float = 1.0f,
    val mass: Float = 1.0f,
) : Mote(state) {
    override val type: Type = Type.ACTOR

    // Physics
    var position = MutableVec2f(0f, 0f)
    var velocity = MutableVec2f(0f, 0f)
    var facing = MutableVec2f(1f, 0f) // location of the mouse cursor

    // Game mechanics
    val statistics: LinkedHashMap<StatisticType, Statistic> = LinkedHashMap()
    val abilities: LinkedHashMap<Int, Ability> = LinkedHashMap()
    val effects: LinkedHashMap<MoteId, Effect> = LinkedHashMap()

    // Rendering
    val texture: TextureSlice? = runBlocking {
        if (texturePath == null) null
        else state.context.resourcesVfs[texturePath].readTexture()?.slice()
    }

    init {
        state.addActor(this)
    }

    override fun tick() {
        if(facing.subtract(position).length() < 0.001f) {
            facing.y -= 1f
        }
        position += velocity
    }

    fun render(batch: SpriteBatch) {
        if (texture == null) return
        batch.draw(
            texture,
            x = position.x - texture.width * textureScale / 2,
            y = position.y - texture.height * textureScale / 2,
            width = texture.width * textureScale,
            height = texture.height * textureScale
        )
    }

    fun addImpulse(force: MutableVec2f, duration: Int? = null, tapered: Boolean = false): Impulse {
        return Impulse(state, this, force, duration, tapered)
    }

    fun addStatistic(statistic: Statistic) {
        statistics[statistic.statisticType] = statistic
    }

    fun removeStatistic(statistic: Statistic) {
        statistics.remove(statistic.statisticType)
    }

    fun getStatistic(statisticType: StatisticType): Statistic? {
        return statistics[statisticType]
    }

    fun stat(statisticType: StatisticType): Double? {
        return statistics[statisticType]?.calculate()
    }

    fun activateAbility(abilityNumber: Int, target: Actor? = null) {
        //abilities[key]?.use(target)
    }

    fun addEffect(effect: Effect) {
        effects[effect.id] = effect
    }

    fun removeEffect(effect: Effect) {
        effects.remove(effect.id)
    }

    override fun serialize(): ByteArray {
        TODO("Not yet implemented")
    }
    override fun deserialize(data: ByteArray) {
        TODO("Not yet implemented")
    }
}