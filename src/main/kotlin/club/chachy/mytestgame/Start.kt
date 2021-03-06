package club.chachy.mytestgame

import club.chachy.mytestgame.engine.loader.Loader
import club.chachy.mytestgame.engine.renderer.Renderer
import club.chachy.mytestgame.engine.textures.ModelTexture
import club.chachy.mytestgame.entities.Entity
import club.chachy.mytestgame.manager.DisplayManager
import club.chachy.mytestgame.models.TexturedModel
import club.chachy.mytestgame.shaders.StaticShader
import org.lwjgl.opengl.Display
import org.lwjgl.util.vector.Vector3f

fun main() {
    DisplayManager.createDisplay()

    val loader = Loader()
    val renderer = Renderer()
    val staticShader = StaticShader()

    val vertices = floatArrayOf(
        -0.5f, 0.5f, 0f,//v0
        -0.5f, -0.5f, 0f,//v1
        0.5f, -0.5f, 0f,//v2
        0.5f, 0.5f, 0f//v3
    )

    val indices = intArrayOf(0, 1, 3, 3, 1, 2)

    val textureCoords = floatArrayOf(0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f)

    val model = loader.loadToVAO(vertices, textureCoords, indices)
    val texture = ModelTexture(loader.loadTexture("texture.png"))
    val texturedModel = TexturedModel(model, texture)
    val entity = Entity(texturedModel, Vector3f(-1f, 0f, 0f), 0f, 0f, 0f, 1f)

    while (!Display.isCloseRequested()) {
        renderer.prepare()
        staticShader.start()
        renderer.render(entity, staticShader)
        staticShader.stop()
        DisplayManager.updateDisplay()
    }

    staticShader.cleanup()
    loader.cleanup()
    DisplayManager.closeDisplay()
}