package top.lajos.game.model

import org.itheima.kotlin.game.core.Painter
import top.lajos.game.Config
import top.lajos.game.business.AutoMovable
import top.lajos.game.business.Destroyable
import top.lajos.game.enums.Direction

/**
 * 子弹
 *
 * create()函数返回两个值，是x, y
 */
class Bullet(
    override val currentDirection: Direction,
    create:(width: Int, height: Int)->Pair<Int, Int>) : AutoMovable, Destroyable {

    // 给子弹一个方向，方向由坦克决定
    override val width: Int
    override val height: Int

    override var x: Int = 0
    override var y: Int = 0

    override val speed: Int = 8

    private val imagePath = "img/" + when (currentDirection) {
        Direction.UP -> "shot_top.gif"
        Direction.DOWN -> "shot_bottom.gif"
        Direction.LEFT -> "shot_left.gif"
        Direction.RIGHT -> "shot_right.gif"
    }

    init {
        // 先计算子弹的宽度和高度
        val size: Array<Int> = Painter.size(imagePath)
        width = size[0]
        height = size[1]

        val pair: Pair<Int, Int> = create.invoke(width, height)
        x = pair.first
        y = pair.second
    }

    override fun draw() {
        Painter.drawImage(imagePath,  x, y)
    }

    override fun autoMove() {
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
    }

    override fun isDestroyed() : Boolean {
        return when {
            x < -width -> true
            x > Config.gameWidth + width -> true
            y < -height -> true
            x > Config.gameHeight + height -> true
            else -> false
        }
    }

}