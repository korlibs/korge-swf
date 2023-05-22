package org.korge.samples.swf
import korlibs.korio.android.withAndroidContext
import korlibs.korgw.*
import main
class MainActivity : KorgwActivity(config = GameWindowCreationConfig(msaa = 1)) {
	override suspend fun activityMain() {
		main()
	}
}
