package top.lajos.game.business

import top.lajos.game.model.View

/*
* 销毁的能力
* */
interface Destroyable : View{
    /*判断是否销毁了*/
    fun isDestroyed(): Boolean
}