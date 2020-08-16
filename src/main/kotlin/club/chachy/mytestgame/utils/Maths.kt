package club.chachy.mytestgame.utils

import org.lwjgl.util.vector.Matrix4f
import org.lwjgl.util.vector.Vector3f

object Maths {
    fun createTransformationMatrix(translation: Vector3f, rx: Float, ry: Float, rz: Float, scale: Float): Matrix4f {
        val matrix = Matrix4f()
        matrix.setIdentity()
        Matrix4f.translate(translation, matrix, matrix)
        Matrix4f.rotate(Math.toRadians(rx.toDouble()).toFloat(), Vector3f(1f, 0f, 0f), matrix, matrix)
        Matrix4f.rotate(Math.toRadians(ry.toDouble()).toFloat(), Vector3f(0f, 1f, 0f), matrix, matrix)
        Matrix4f.rotate(Math.toRadians(rz.toDouble()).toFloat(), Vector3f(0f, 0f, 1f), matrix, matrix)
        Matrix4f.scale(Vector3f(scale, scale, scale), matrix, matrix)
        return matrix
    }
}