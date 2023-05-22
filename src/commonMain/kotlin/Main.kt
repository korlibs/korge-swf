import korlibs.korge.*
import korlibs.korge.scene.*
import samples.tictactoeswf.*

suspend fun main() = Korge {
    //sceneContainer().changeTo({ MainDemo() })
    //sceneContainer().changeTo({ MainTicTacToeSwf() })
    sceneContainer().changeTo({ MainSWF() })
}
