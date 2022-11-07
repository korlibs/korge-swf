package com.soywiz.korge.ktree

import com.soywiz.korge.ui.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.ktree.*

@Deprecated("KTree is going to be removed in a future version")
class UIButtonSerializer : KTreeSerializerExt<AnimationViewRef>("AnimationViewRef", AnimationViewRef::class, { AnimationViewRef() }, {
})
