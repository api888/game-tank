package top.lajos.game.model

/*
* 显示的视图，定义显示规范
* */
interface View {
    // 可以定义属性让实现类去实现
    // 位置
    val x: Int
    val y: Int
    // 宽高
    val width: Int
    val height: Int
    // 显示
    fun draw()
}