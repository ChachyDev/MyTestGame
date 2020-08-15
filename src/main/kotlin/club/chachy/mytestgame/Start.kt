package club.chachy.mytestgame

import club.chachy.mytestgame.engine.loader.Loader
import club.chachy.mytestgame.engine.renderer.Renderer
import club.chachy.mytestgame.manager.DisplayManager
import org.lwjgl.opengl.Display

fun main() {
    DisplayManager.createDisplay()

    val loader = Loader()
    val renderer = Renderer()

    val vertices = floatArrayOf(
        -0.5f, 0.5f, 0f,//v0
        -0.5f, -0.5f, 0f,//v1
        0.5f, -0.5f, 0f,//v2
        0.5f, 0.5f, 0f//v3
    )

    val indices = intArrayOf(
        0, 1, 3,
        3, 1, 2
    )

    val model = loader.loadToVAO(vertices, indices)

    while (!Display.isCloseRequested()) {
        renderer.prepare()
        renderer.render(model)
        DisplayManager.updateDisplay()
    }

    loader.cleanup()
    DisplayManager.closeDisplay()
}