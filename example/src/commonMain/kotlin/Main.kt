import korlibs.korge.*
import korlibs.korge.scene.*
import samples.tictactoeswf.*

suspend fun main() = Korge {
    sceneContainer().changeTo({ MainSWF() })
    //sceneContainer().changeTo({ MainTicTacToeSwf() })
}