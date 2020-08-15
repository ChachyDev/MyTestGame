package club.chachy.mytestgame.engine.loader

import club.chachy.mytestgame.engine.modelling.RawModel
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import java.nio.FloatBuffer
import java.nio.IntBuffer

class Loader {

    private val vaos = mutableListOf<Int>()
    private val vbos = mutableListOf<Int>()

    fun loadToVAO(positions: FloatArray, indices: IntArray): RawModel {
        val vaoID = createVAO()
        bindIndicesBuffer(indices)
        storeDataInAttributesList(0, positions)
        unbindVAO()
        return RawModel(vaoID, indices.size)
    }

    fun cleanup() {
        vaos.forEach {
            GL30.glDeleteVertexArrays(it)
        }

        vbos.forEach {
            GL15.glDeleteBuffers(it)
        }
    }

    private fun createVAO(): Int {
        val vaoID = GL30.glGenVertexArrays()
        vaos.add(vaoID)
        GL30.glBindVertexArray(vaoID)
        return vaoID
    }

    private fun storeDataInAttributesList(attributeNumber: Int, data: FloatArray) {
        val vboID = GL15.glGenBuffers()
        vbos.add(vboID)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID)
        val buffer = storeDataInFloatBuffer(data)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0)
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