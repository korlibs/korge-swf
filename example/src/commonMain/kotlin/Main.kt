import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import samples.tictactoeswf.*

suspend fun main() = Korge {
    sceneContainer().changeTo({ MainSWF() })
    //sceneContainer().changeTo({ MainTicTacToeSwf() })
}