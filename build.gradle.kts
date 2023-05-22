import korlibs.korge.gradle.*

plugins {
    //alias(libs.plugins.korge)
    id("com.soywiz.korge") version "4.0.0"
}

korge {
    id = "org.korge.samples.swf"

    targetJvm()
    targetJs()
    targetDesktop()
    //targetDesktopCross()
    targetIos()
    targetAndroid()

    serializationJson()
}

dependencies {
    add("commonMainApi", project(":deps"))
}

