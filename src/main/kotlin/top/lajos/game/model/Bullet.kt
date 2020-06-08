package top.lajos.game.model

import org.itheima.kotlin.game.core.Painter
import top.lajos.game.Config
import top.lajos.game.business.Attackable
import top.lajos.game.business.AutoMovable
import top.lajos.game.business.Destroyable
import top.lajos.game.business.Sufferable
import top.lajos.game.enums.Direction
import top.lajos.game.ext.checkCollision

/**
 * 子弹
 *
 * create()函数返回两个值，是x, y
 */
class Bullet(
    override val owner: View,
    override val currentDirection: Direction,
    create:(width: Int, height: Int)->Pair<Int, Int>
) : AutoMovable, Destroyable, Attackable, Sufferable {


    override val attackPower: Int = 1

    // 给子弹一个方向，方向由坦克决定
    override val width: Int
    override val height: Int
    override val blood: Int = 1

    override var x: Int = 0
    override var y: Int = 0

    override val speed: Int = 8

    private var isDestroyed: Boolean = false

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
        if (isDestroyed) return true
        return when {
            x < -width -> true
            x > Config.gameWidth + width -> true
            y < -height -> true
            x > Config.gameHeight + height -> true
            else -> false
        }
    }

    override fun isCollision(sufferable: Sufferable): Boolean {
        return checkCollision(sufferable)

    }

    override fun notifyAttack(sufferable: Sufferable) {
//        println("子弹接收到碰撞了。。。")
        // 子弹打到墙上后，子弹要销毁掉
        isDestroyed = true
    }

    override fun notifySuffer(attackable: Attackable): Array<View>? {
        return arrayOf(Blast(x, y))
    }
}