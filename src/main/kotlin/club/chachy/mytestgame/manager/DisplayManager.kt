package club.chachy.mytestgame.manager

import org.lwjgl.opengl.*

object DisplayManager {
    private const val width = 1280
    private const val height = 720
    private const val fpsCap = 120

    fun createDisplay() {
        val attribs = ContextAttribs(3, 2)
        attribs.withForwardCompatible(true)
        attribs.withProfileCore(true)

        Display.setDisplayMode(
            DisplayMode(
                width,
                height
            )
        )
        Display.create(PixelFormat(), attribs)
        Display.setTitle("Osorio")

        GL11.glViewport(
            0, 0,
            width,
            height
        )
    }

    fun updateDisplay() {
        Display.sync(fpsCap)
        Display.update()
    }

    fun closeDisplay() {
        Display.destroy()
    }
}