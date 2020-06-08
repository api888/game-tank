package top.lajos.game

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window
import top.lajos.game.business.AutoMovable
import top.lajos.game.business.Blockable
import top.lajos.game.business.Destroyable
import top.lajos.game.business.Movable
import top.lajos.game.enums.Direction
import top.lajos.game.model.*
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

class GameWindow : Window(
    title = "坦克大战1.0",
    icon = "img/logo.jpg",
    width = Config.gameWidth,
    height = Config.gameWidth) {

//    var wall = Wall()
//    var grass = Grass()
//    var steel = Steel()
//    var water = Water()

    // 管理元素的集合
//    private val views = arrayListOf<View>()
    // 线程安全的集合
    private val views = CopyOnWriteArrayList<View>()


    // 晚点创建
    private lateinit var tank: Tank

    override fun onCreate() {
        //地图
        // 通过都文件的方式创建地图
        val file = File(javaClass.getResource("/map/1.map").path)
        // 读取文件的行
        val lines: List<String> = file.readLines()
        // 循环遍历
        var lineNum = 0
        lines.forEach { line ->
            var columnNum = 0
            // line - [空砖空砖空砖空砖空砖空砖空]
            line.toCharArray().forEach { column ->
                when (column) {
                    '砖' -> views.add(Wall(columnNum * Config.block, lineNum * Config.block))
                    '草' -> views.add(Grass(columnNum * Config.block, lineNum * Config.block))
                    '铁' -> views.add(Steel(columnNum * Config.block, lineNum * Config.block))
                    '水' -> views.add(Water(columnNum * Config.block, lineNum * Config.block))
                }
                columnNum ++
            }
            lineNum ++
        }

        // 添加我方坦克
        tank = Tank(Config.block * 10, Config.block * 12)
        views.add(tank)
    }

    override fun onDisplay() {
        // 绘制图像

        //绘制地图元素
        views.forEach {
            it.draw()
        }
        println("-----------------${views.size}----------------")
//        // 绘制一个墙砖
//        // Painter.drawImage("img/wall.gif", 100, 100)
//        wall.draw()
//
//        // 绘制一个草坪
//        // Painter.drawImage("img/grass.gif", 200, 200)
//        grass.draw()
//
//        // 绘制铁墙
//        steel.draw()
//
//        // 绘制水
//        water.draw()
    }

    override fun onKeyPressed(event: KeyEvent) {
        // 用户操作时
        when (event.code) {
            KeyCode.W -> {
                tank.move(Direction.UP)
            }
            KeyCode.S-> {
                tank.move(Direction.DOWN)
            }
            KeyCode.A -> {
                tank.move(Direction.LEFT)
            }
            KeyCode.D -> {
                tank.move(Direction.RIGHT)
            }
            KeyCode.SPACE -> {
                // 发射子弹
                val bullet: Bullet = tank.shot()
                // 交给views管理
                views.add(bullet)
            }
        }

    }

    override fun onRefresh() {
        // 业务逻辑

        // 判断运动的物体和阻塞物体是否放生碰撞
        //1）找到运动的物体
        views.filter { it is Movable }.forEach { move ->
            move as Movable
            var badDirection: Direction? = null
            var badBlock: Blockable? = null
            //2）找到阻塞的物体
            views.filter { it is Blockable }.forEach blockTag@ { block ->
                //3）遍历集合，找到是否发生碰撞, move和block是否碰撞
                block as Blockable
                // 获得碰撞的方向
                val direction: Direction? = move.willCollision(block)
                direction?.let {
                    // 移动的发现碰撞，跳出当前循环
                    badDirection = direction
                    badBlock = block
                    return@blockTag
                }
            }
            // 找到和move碰撞的block，找到会碰撞的方向
            // 通知可以移动的物体会在哪个方向和哪个物体碰撞
            move.notifyCollision(badDirection, badBlock)
        }

        // 检测自动移动能力的物体，让他们自己动起来
        views.filter { it is AutoMovable }.forEach {
            (it as AutoMovable).autoMove()
        }

        // 检测销毁
        views.filter { it is Destroyable }.forEach {
            // 判断具备销毁能力的物体，是否被销毁
            if ((it as Destroyable).isDestroyed()) views.remove(it)
        }

        // 检测  具备攻击能力的和被攻击能力的物体间是否产生碰撞
    }

}