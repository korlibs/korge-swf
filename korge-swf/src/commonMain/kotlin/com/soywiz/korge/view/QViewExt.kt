package com.soywiz.korge.view

import com.soywiz.korge.animate.*

/** Sets the state (if available) of all the views in this query */
@Deprecated("")
fun QView.state(name: String) {
    fastForEach { it.play(name) }
}
