package club.chachy.mytestgame.utils

import club.chachy.mytestgame.entities.Entity
import org.joml.Matrix4f
import org.joml.Vector3f

fun createTransformationMatrix(entity: Entity): Matrix4f {
    return createTransformationMatrix(entity.position, entity.rotX, entity.rotY, entity.rotZ, entity.scale)
}

fun createTransformationMatrix(translation: Vector3f, rx: Float, ry: Float, rz: Float, scale: Float): Matrix4f {
    val matrix = Matrix4f()
    matrix.identity()
    matrix.translate(translation)
    matrix.rotate(Math.toRadians(rx.toDouble()).toFloat(), Vector3f(1f, 0f, 0f), matrix)
    matrix.rotate(Math.toRadians(ry.toDouble()).toFloat(), Vector3f(0f, 1f, 0f), matrix)
    matrix.rotate(Math.toRadians(rz.toDouble()).toFloat(), Vector3f(0f, 0f, 1f), matrix)
    matrix.scale(Vector3f(scale, scale, scale))
    return matrix
}