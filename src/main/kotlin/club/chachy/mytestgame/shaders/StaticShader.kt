package club.chachy.mytestgame.shaders

import org.lwjgl.util.vector.Matrix4f
import java.io.File

class StaticShader : ShaderProgram(
    File(StaticShader::class.java.getResource("/vertexShader.vert").toURI()),
    File(StaticShader::class.java.getResource("/fragmentShader.frag").toURI())
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