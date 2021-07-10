package club.chachy.mytestgame.engine.loader.texture

import de.matthiasmann.twl.utils.PNGDecoder
import org.lwjgl.opengl.ARBFramebufferObject.glGenerateMipmap
import org.lwjgl.opengl.GL11.*
import java.nio.ByteBuffer

object TextureLoader {
    data class Texture(val id: Int)

    fun loadPNGTexture(fileName: String): Texture {
        val decoder = PNGDecoder(TextureLoader::class.java.getResourceAsStream("/$fileName"))
        val buffer = ByteBuffer.allocateDirect(4 * decoder.width * decoder.height)

        //decode
        decoder.decode(buffer, decoder.width * 4, PNGDecoder.Format.RGBA)
        buffer.flip()

        return loadTexture(decoder.width, decoder.height, buffer)
    }

    private fun loadTexture(width: Int, height: Int, textureBuffer: ByteBuffer): Texture {
        val id = glGenTextures()

        glBindTexture(GL_TEXTURE_2D, id)

        //tell opengl how to unpack bytes
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)

        //set the texture parameters, can be GL_LINEAR or GL_NEAREST
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)

        //upload texture
        glTexImage2D(
            GL_TEXTURE_2D,
            0,
            GL_RGBA,
            width,
            height,
            0,
            GL_RGBA,
            GL_UNSIGNED_BYTE,
            textureBuffer
        )

        // Generate Mip Map
        glGenerateMipmap(GL_TEXTURE_2D)
        return Texture(id)
    }
}