package top.lajos.game.business

import top.lajos.game.enums.Direction
import top.lajos.game.model.View


/**
 * 自动移动的能力
 */
interface AutoMovable : View {

    val currentDirection: Direction

    val speed: Int

    fun autoMove()
}