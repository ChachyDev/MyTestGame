package club.chachy.mytestgame.shaders

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import java.io.File
import kotlin.system.exitProcess

abstract class ShaderProgram(vertexFile: File, fragmentFile: File) {

    private val vertexShaderID: Int
    private val fragmentShaderID: Int
    private val programID: Int

    init {
        vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER)
        fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER)
        programID = GL20.glCreateProgram()
        GL20.glAttachShader(programID, vertexShaderID)
        GL20.glAttachShader(programID, fragmentShaderID)
        GL20.glLinkProgram(programID)
        GL20.glValidateProgram(programID)
        bindAttributes()
    }

    fun start() {
        GL20.glUseProgram(programID)
    }

    fun stop() {
        GL20.glUseProgram(0)
    }

    fun cleanup() {
        stop()
        GL20.glDetachShader(programID, vertexShaderID)
        GL20.glDetachShader(programID, fragmentShaderID)
        GL20.glDeleteShader(vertexShaderID)
        GL20.glDeleteShader(fragmentShaderID)
        GL20.glDeleteProgram(programID)
    }

    protected fun bindAttribute(attribute: Int, variableName: String) {
        GL20.glBindAttribLocation(programID, attribute, variableName)
    }

    protected abstract fun bindAttributes()


    private fun loadShader(file: File, type: Int) : Int {
        val source = file.readText()
        val shaderID = GL20.glCreateShader(type)
        GL20.glShaderSource(shaderID, source)
        GL20.glCompileShader(shaderID)
        if (GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            println(GL20.glGetShaderInfoLog(shaderID, 500))
            println("Could not compile shader")
            exitProcess(0)
        }

        return shaderID
    }
}