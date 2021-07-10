package club.chachy.mytestgame.manager

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL


object DisplayManager {
    private const val WIDTH = 1280
    private const val HEIGHT = 720
    private const val fpsCap = 120

    var window: Long = 999

    fun createDisplay() {
        if (!glfwInit()) {
            error("Failed to initialize GLFW")
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)

        window = glfwCreateWindow(WIDTH, HEIGHT, "Osorio", NULL, NULL).takeIf { it != NULL }
            ?: error("Failed to create GLFW window")

        val videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()) ?: error("Failed to get video mode")

        glfwSetWindowPos(window, (videoMode.width() - WIDTH) / 2, (videoMode.height() - HEIGHT) / 2)

        glfwMakeContextCurrent(window)
        GL.createCapabilities()
        glfwShowWindow(window)
    }

    fun updateDisplay() {
        glfwSwapBuffers(window)
        glfwPollEvents()
    }

    fun closeDisplay() {
        glfwTerminate()
    }
}