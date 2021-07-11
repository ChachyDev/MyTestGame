package club.chachy.mytestgame

import club.chachy.mytestgame.engine.loader.Loader
import club.chachy.mytestgame.engine.renderer.EntityRenderer
import club.chachy.mytestgame.entities.Entity
import club.chachy.mytestgame.manager.DisplayManager
import club.chachy.mytestgame.models.TexturedModel
import club.chachy.mytestgame.shaders.StaticShader
import org.joml.Vector3f
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose

fun main() {
    DisplayManager.createDisplay()

    val loader = Loader()
    val renderer = EntityRenderer()
    val staticShader = StaticShader()

    val vertices = floatArrayOf(
        -0.5f, 0.5f, 0f,//v0
        -0.5f, -0.5f, 0f,//v1
        0.5f, -0.5f, 0f,//v2
        0.5f, 0.5f, 0f//v3
    )

    val indices = intArrayOf(0, 1, 3, 3, 1, 2)

    val textureCoords = floatArrayOf(0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f)

    val entity = Entity(
        TexturedModel(loader.loadToVAO(vertices, textureCoords, indices), loader.loadTexture("images/texture.png")),
        Vector3f(30f, 0f, 0f)
    )

    while (!glfwWindowShouldClose(DisplayManager.window)) {
        renderer.prepare()

        staticShader.apply()
        renderer.render(entity, staticShader)
        staticShader.disable()

        DisplayManager.updateDisplay()
    }

    staticShader.cleanup()
    loader.cleanup()
    DisplayManager.closeDisplay()
}