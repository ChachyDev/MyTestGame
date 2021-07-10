package club.chachy.mytestgame.entities

import club.chachy.mytestgame.models.TexturedModel
import org.joml.Vector3f

class Entity(
    val model: TexturedModel,
    val position: Vector3f,
    var rotX: Float = 0f,
    var rotY: Float = 0f,
    var rotZ: Float = 0f,
    val scale: Float = 1f
) {

    fun increasePosition(dx: Float, dy: Float, dz: Float) {
        position.x += dx
        position.y += dy
        position.z += dz
    }

    fun increaseRotation(dx: Float, dy: Float, dz: Float) {
        rotX += dx
        rotY += dy
        rotZ += dz
    }
}