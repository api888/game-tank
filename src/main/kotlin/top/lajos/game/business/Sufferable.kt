package top.lajos.game.business

import top.lajos.game.model.View


/*
* 遭受攻击的能力
* */
interface Sufferable: View {

    /*
    * 生命值
    * */
    val blood: Int

    fun notifySuffer(attackable: Attackable): Array<View>?


}