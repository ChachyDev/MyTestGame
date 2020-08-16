package club.chachy.mytestgame.engine.renderer

import club.chachy.mytestgame.entities.Entity
import club.chachy.mytestgame.shaders.StaticShader
import club.chachy.mytestgame.utils.Maths
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30

class Renderer {
    fun prepare() {
        GL11.glClearColor(1f, 0f, 0f, 1f)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
    }

    fun render(entity: Entity, shader: StaticShader) {
        val texturedModel = entity.model
        val model = texturedModel.rawModel
        GL30.glBindVertexArray(model.vaoID)
        GL20.glEnableVertexAttribArray(0)
        GL20.glEnableVertexAttribArray(1)
        val transformationMatrix = Maths
            .createTransformationMatrix(
                entity.position,
                entity.rotX,
                entity.rotY,
                entity.rotZ,
                entity.scale
            )

        shader.loadTransformationMatrix(transformationMatrix)

        GL13.glActiveTexture(GL13.GL_TEXTURE0)
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.texture.textureID)
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.vertexCount, GL11.GL_UNSIGNED_INT, 0)
        GL20.glDisableVertexAttribArray(0)
        GL20.glDisableVertexAttribArray(1)
        GL30.glBindVertexArray(0)
    }
}