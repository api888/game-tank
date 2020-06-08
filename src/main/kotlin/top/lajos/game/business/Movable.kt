package top.lajos.game.business

import top.lajos.game.Config
import top.lajos.game.enums.Direction
import top.lajos.game.model.View

/*
* 移动运动的能力
* */
interface Movable : View {

    /**
     * 可移动的物体存在方向
     */
    val currentDirection: Direction

    /**
     * 可移动的物体需要有移动的速度
     */
    val speed: Int

    /**
    * 判断移动的物体是否和阻塞物体发生碰撞
    * @return 要碰撞的方向，如果为null说明没有碰撞的
    */
    fun willCollision(block: Blockable):Direction? {
        // 未来的坐标
        var x: Int = this.x
        var y: Int = this.y
        // 将要碰撞时做判断
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

        // 和边界进行检测
        // 越界判断
        if (x < 0) return Direction.LEFT
        if (x > Config.gameWidth - width) return Direction.RIGHT
        if (y < 0) return Direction.UP
        if (y > Config.gameHeight - height) return Direction.DOWN

        val collision = checkCollision(block.x, block.y, block.width, block.height, x, y, width, height)
        return if (collision) currentDirection else null
    }

    /**
     * 通知碰撞
     */
    fun notifyCollision(direction: Direction?, block: Blockable?)
}