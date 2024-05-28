package com.game.finitymission

import com.game.finitymission.abilities.UserInput
import com.game.finitymission.actors.Actor
import com.game.finitymission.actors.Character
import com.lehaine.littlekt.Context
import com.lehaine.littlekt.ContextListener
import com.lehaine.littlekt.file.vfs.readTexture
import com.lehaine.littlekt.graph.node.resource.HAlign
import com.lehaine.littlekt.graphics.*
import com.lehaine.littlekt.graphics.g2d.SpriteBatch
import com.lehaine.littlekt.graphics.g2d.use
import com.lehaine.littlekt.graphics.gl.ClearBufferMask
import com.lehaine.littlekt.math.geom.degrees
import com.lehaine.littlekt.math.geom.radians
import com.lehaine.littlekt.util.viewport.ExtendViewport
import kotlin.time.Duration.Companion.milliseconds


class Game(context: Context) : ContextListener(context) {

    override suspend fun Context.start() {
        val batch = SpriteBatch(this)
        val viewport = ExtendViewport(960, 540)
        val camera = viewport.camera
        var rotation = 0.radians
        var rotationTimer = 0.milliseconds
        val state = GameState(context)

        val player = Character(state)
        UserInput(state, player)

        val texture: Texture = resourcesVfs["assets/terrain.png"].readTexture()
        val terrain = texture.slice()

        onResize { width, height ->
            viewport.update(width, height, context)
        }

        onRender { dt ->
            gl.clearColor(Color.DARK_GRAY)
            gl.clear(ClearBufferMask.COLOR_BUFFER_BIT)

            state.tick()

            batch.use(camera.viewProjection) {
                batch.draw(terrain, x = -500f, y = -500f, width = 1000f, height = 1000f)
                Fonts.default.draw(it, "Hello LittleKt!", 0f, 0f, align = HAlign.CENTER)
            }
            rotationTimer += dt
            if (rotationTimer > 10.milliseconds) {
                rotationTimer = 0.milliseconds
                rotation += 1.degrees
            }
        }
    }
}