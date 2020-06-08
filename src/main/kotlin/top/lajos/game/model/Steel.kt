package top.lajos.game.model

import org.itheima.kotlin.game.core.Painter
import top.lajos.game.Config
import top.lajos.game.business.Attackable
import top.lajos.game.business.Blockable
import top.lajos.game.business.Sufferable

/**
 *  铁墙
 *
 *  具有阻塞能力
 */
class Steel(override val x: Int, override val y: Int) : Blockable, Sufferable {
    override val blood: Int = 5


    // 位置
//    override val x: Int = 200
//    override val y: Int = 200
    // 宽高
    override val width: Int = Config.block
    override val height: Int = Config.block
    // 显示行为
    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }

    override fun notifySuffer(attackable: Attackable): Array<View>?  = null
}