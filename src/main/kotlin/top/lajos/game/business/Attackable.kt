package top.lajos.game.business

import top.lajos.game.model.View


/*
* 具备攻击的能力
* */
interface Attackable : View {

    // 所有者
    val owner: View

    /**
     * 攻击力
     */
    val attackPower: Int
    //判断是否碰撞
    fun isCollision(sufferable: Sufferable): Boolean

    fun notifyAttack(sufferable: Sufferable)
}