package top.lajos.game.business

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
    fun willCollision(block: Blockable):Direction?

    /**
     * 通知碰撞
     */
    fun notifyCollision(direction: Direction?, block: Blockable?)
}