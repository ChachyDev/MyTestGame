package club.chachy.mytestgame.shaders

import java.io.File

class StaticShader : ShaderProgram(
        File(StaticShader::class.java.getResource("/vertexShader.vert").toURI()),
        File(StaticShader::class.java.getResource("/fragmentShader.frag").toURI())
) {
    override fun bindAttributes() {
        super.bindAttribute(0, "position")
        super.bindAttribute(1, "textureCoords")
    }
}