package club.chachy.mytestgame.engine.renderer

import club.chachy.mytestgame.entities.Entity
import club.chachy.mytestgame.shaders.StaticShader
import club.chachy.mytestgame.utils.createTransformationMatrix
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30

class EntityRenderer {
    fun prepare() {
        GL11.glClearColor(1f, 0f, 0f, 1f)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
    }

    fun render(entity: Entity, shader: StaticShader) {
        val model = entity.model.rawModel

        GL30.glBindVertexArray(model.vaoID)
        GL20.glEnableVertexAttribArray(0)
        GL20.glEnableVertexAttribArray(1)

        shader.loadTransformationMatrix(createTransformationMatrix(entity))

        GL13.glActiveTexture(GL13.GL_TEXTURE0)
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, entity.model.texture.textureID)
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.vertices, GL11.GL_UNSIGNED_INT, 0)
        GL20.glDisableVertexAttribArray(0)
        GL20.glDisableVertexAttribArray(1)
        GL30.glBindVertexArray(0)
    }
}