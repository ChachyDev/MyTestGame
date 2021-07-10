package club.chachy.mytestgame.shaders

import org.joml.Matrix4f
import java.io.File

class StaticShader : ShaderProgram(
    File(StaticShader::class.java.getResource("/shaders/vertexShader.vert")?.toURI() ?: error("Could not find Vertex Shader")),
    File(
        StaticShader::class.java.getResource("/shaders/fragmentShader.frag")?.toURI()
            ?: error("Could not find Fragment shader :(")
    )
) {
    private var locationTransformationMatrix = 0

    override fun bindAttributes() {
        super.bindAttribute(0, "position")
        super.bindAttribute(1, "textureCoords")
    }

    override fun getAllUniformLocations() {
        locationTransformationMatrix = super.getUniformLocation("transformationMatrix")
    }

    fun loadTransformationMatrix(matrix: Matrix4f) {
        super.loadMatrix(locationTransformationMatrix, matrix)
    }
}