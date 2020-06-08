package top.lajos.game.model

import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import top.lajos.game.Config
import top.lajos.game.business.Attackable
import top.lajos.game.business.Blockable
import top.lajos.game.business.Destroyable
import top.lajos.game.business.Sufferable

/**
 * 砖墙
 *
 * 具备阻塞能力
 * 具备挨打能力
 * 具备销毁能力
 */
// x,y 通过构造传入
class Wall(override val x: Int, override val y: Int) :Blockable, Sufferable, Destroyable {

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

    override var blood: Int = 3

    // 宽高
    override val width: Int = Config.block
    override val height: Int = Config.block
    // 显示行为
    override fun draw() {
        Painter.drawImage("img/wall.gif", x, y)
    }

    override fun notifySuffer(attackable: Attackable): Array<View>? {
//        println("砖墙接收到挨打了。。。")
        // 砖墙要被销毁，砖墙要掉血
        blood -= attackable.attackPower
        // 喊疼
        Composer.play("snd/hit.wav")
        return arrayOf(Blast(x, y))
    }

    override fun isDestroyed(): Boolean = blood <= 0

}