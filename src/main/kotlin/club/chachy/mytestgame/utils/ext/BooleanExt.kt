package club.chachy.mytestgame.utils.ext

fun Boolean.toFloat() = takeIf { it }?.let { 1f } ?: 0f