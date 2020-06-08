package top.lajos.game

// object 单例设计
object Config {
    /*
    * 方格的宽和高
    * */
    val block: Int = 64
    val gameWidth: Int = block * 13
    val gameHeight: Int = block * 13
}