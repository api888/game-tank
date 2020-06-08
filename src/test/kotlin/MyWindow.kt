import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window

// 窗体：继承游戏引擎总的窗体

class MyWindow : Window() {
    override fun onCreate() {
        println("窗体创建。。。")
    }
    // 窗体渲染时的回调, 不停的回调
    override fun onDisplay() {
        // 绘制图片
        Painter.drawImage("img/tank_u.gif", 200, 200)
        // 绘制颜色
        Painter.drawColor(Color.WHITE, 20, 20, 100, 100)
        // 填写文本
        Painter.drawText("你好", 30, 30)
    }

    // 按键响应
    override fun onKeyPressed(event: KeyEvent) {
        when (event.code) {
            KeyCode.ENTER -> println("点击了enter按钮, ${KeyCode.ENTER}")
            KeyCode.K -> Composer.play("snd/blast.wav")
        }
    }

    // 刷新，做业务逻辑，做耗时的操作
    override fun onRefresh() {

    }

}

fun main(args: Array<String>) {
    Application.launch(MyWindow::class.java)
}