package top.lajos.game.model

import org.itheima.kotlin.game.core.Painter
import top.lajos.game.Config
import top.lajos.game.business.Destroyable


class Blast(override val x: Int, override val y: Int) : Destroyable {
    override val width: Int = Config.block
    override val height: Int =  Config.block
    private val imagePaths = arrayListOf<String>()
    private var index: Int = 0

    init {
        (1..32).forEach {
            imagePaths.add("img/blast_${it}.png")
        }
    }

    override fun draw() {
        val i = index % imagePaths.size
        Painter.drawImage(imagePaths[i], x, y)
        index ++
    }

    override fun isDestroyed(): Boolean {
        return index >= imagePaths.size
    }

}