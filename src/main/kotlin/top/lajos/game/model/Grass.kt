package top.lajos.game.model

import org.itheima.kotlin.game.core.Painter
import top.lajos.game.Config

/*
* 草坪
* */
class Grass(override val x: Int, override val y: Int) : View {
    // 位置
//    override val x: Int = 200
//    override val y: Int = 200
    // 宽高
    override val width: Int = Config.block
    override val height: Int = Config.block
    // 显示行为
    override fun draw() {
        Painter.drawImage("img/grass.gif", x, y)
    }
}