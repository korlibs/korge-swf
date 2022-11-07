package com.soywiz.korge.ext.swf

import com.soywiz.korge.awt.*
import com.soywiz.korge.view.*
import java.util.ArrayList

class SwfComponentFactory {
    open fun getViewFactories(views: Views): List<MyComponentFactory.ViewFactory> = ArrayList<MyComponentFactory.ViewFactory>().also { list ->
        list.add(MyComponentFactory.ViewFactory("AnimationViewRef") { AnimationViewRef() })
    }
}