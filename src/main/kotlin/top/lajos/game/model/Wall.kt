package top.lajos.game.model

import org.itheima.kotlin.game.core.Painter
import top.lajos.game.Config
import top.lajos.game.business.Blockable

/**
 * 砖墙
 *
 * 具备阻塞能力
 */
// x,y 通过构造传入
class Wall(override val x: Int, override val y: Int) :Blockable {
//    // 位置
//    var x: Int = 100
//    var y: Int = 100
//    // 宽高
//    var width: Int = Config.block
//    var hegith: Int = Config.block
//    // 显示行为
//    fun draw() {
//        Painter.drawImage("img/wall.gif", x, y)
//    }
    // 位置
//    override val x: Int = 100
//    override val y: Int = 100
    // 宽高
    override val width: Int = Config.block
    override val height: Int = Config.block
    // 显示行为
    override fun draw() {
        Painter.drawImage("img/wall.gif", x, y)
    }


}