package club.chachy.mytestgame.engine.loader

import club.chachy.mytestgame.engine.models.RawModel
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import org.newdawn.slick.opengl.TextureLoader
import java.nio.FloatBuffer
import java.nio.IntBuffer

class Loader {

    private val vaos = mutableListOf<Int>()
    private val vbos = mutableListOf<Int>()
    private val textures = mutableListOf<Int>()

    fun loadToVAO(positions: FloatArray, textureCoords: FloatArray, indices: IntArray): RawModel {
        val vaoID = createVAO()
        bindIndicesBuffer(indices)
        storeDataInAttributesList(0, 3, positions)
        storeDataInAttributesList(1, 2, textureCoords)
        unbindVAO()
        return RawModel(vaoID, indices.size)
    }

    fun loadTexture(fileName: String) : Int {
        val texture = TextureLoader.getTexture("PNG", javaClass.getResourceAsStream("/$fileName"))
        val textureID = texture.textureID
        textures.add(textureID)
        return textureID
    }

    fun cleanup() {
        vaos.forEach {
            GL30.glDeleteVertexArrays(it)
        }

        vbos.forEach {
            GL15.glDeleteBuffers(it)
        }

        textures.forEach {
            GL11.glDeleteTextures(it)
        }
    }

    private fun createVAO(): Int {
        val vaoID = GL30.glGenVertexArrays()
        vaos.add(vaoID)
        GL30.glBindVertexArray(vaoID)
        return vaoID
    }

    private fun storeDataInAttributesList(attributeNumber: Int, coordinateSize: Int, data: FloatArray) {
        val vboID = GL15.glGenBuffers()
        vbos.add(vboID)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID)
        val buffer = storeDataInFloatBuffer(data)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    private fun unbindVAO() {
        GL30.glBindVertexArray(0)
    }

    private fun bindIndicesBuffer(indices: IntArray) {
        val vboID = GL15.glGenBuffers()
        vbos.add(vboID)

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID)
        val buffer = storeDataInIntBuffer(indices)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
    }

    private fun storeDataInIntBuffer(data: IntArray): IntBuffer {
        val buffer = BufferUtils.createIntBuffer(data.size)
        buffer.put(data)
        buffer.flip()
        return buffer
    }

    private fun storeDataInFloatBuffer(data: FloatArray): FloatBuffer {
        val buffer = BufferUtils.createFloatBuffer(data.size)
        buffer.put(data)
        buffer.flip()
        return buffer
    }
}