package com.soywiz.korge.animate

import com.soywiz.klock.milliseconds
import com.soywiz.klock.seconds
import com.soywiz.kmem.toIntRound
import com.soywiz.korge.tests.ViewsForTesting
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.solidRect
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.*
import com.soywiz.korma.geom.interpolate
import com.soywiz.korma.interpolation.*
import kotlin.test.Test
import kotlin.test.assertEquals

class AnimatorTest : ViewsForTesting() {
    @Test
    fun test() = viewsTest {
        val view = solidRect(100, 100, Colors.RED)
        val log = arrayListOf<String>()
        animate(completeOnCancel = false) {
            view.moveTo(100, 0)
            view.moveBy(y = +100.0)
            block { log += "${view.pos}" }
            view.moveBy(x = +10.0)
            view.moveTo(x = { view.x + 10 })
        }
        assertEquals("(120, 100)", view.pos.toString())
        assertEquals("[(100, 100)]", log.toString())
    }

    @Test
    fun testInterpolateAngle() = viewsTest {
        //        0 360 -360
        //  -90 /+--+\
        // 270 |     | 90 -270
        //      \+--+/
        //        180
        //        -180

        assertEquals(202.5.degrees, _interpolateAngle(0.25, 180.degrees, (-90).degrees))
        assertEquals(0.degrees, _interpolateAngle(0.5, 350.degrees, (10).degrees))
        assertEquals(0.degrees, _interpolateAngle(0.5, 10.degrees, (350).degrees))
    }

    @Test
    fun testTweenAngle() = viewsTest(frameTime = 100.milliseconds) {
        val view = solidRect(10, 10, Colors.RED)
        val log = arrayListOf<Int>()
        tween(view::rotation[350.0.degrees, 10.0.degrees], time = 1.seconds, easing = Easing.LINEAR) {
            log += view.rotation.degrees.toIntRound()
        }
        assertEquals("350,352,354,356,358,0,2,4,6,8,10", log.joinToString(","))
    }

    @Test
    fun testTweenAngleDenormalized() = viewsTest(frameTime = 100.milliseconds) {
        val view = solidRect(10, 10, Colors.RED)
        val log = arrayListOf<Int>()
        tween(view::rotation[350.0.degrees, 10.0.degrees].denormalized(), time = 1.seconds, easing = Easing.LINEAR) {
            log += view.rotation.degrees.toIntRound()
        }
        assertEquals("350,316,282,248,214,180,146,112,78,44,10", log.joinToString(","))
    }

    @PublishedApi
    internal fun _interpolateAngle(ratio: Double, l: Angle, r: Angle): Angle = _interpolateAngleAny(ratio, l, r, minimizeAngle = true)

    @PublishedApi
    internal fun _interpolateAngleDenormalized(ratio: Double, l: Angle, r: Angle): Angle = _interpolateAngleAny(ratio, l, r, minimizeAngle = false)

    internal fun _interpolateAngleAny(ratio: Double, l: Angle, r: Angle, minimizeAngle: Boolean = true): Angle {
        if (!minimizeAngle) return Angle.fromRatio(ratio.interpolate(l.ratio, r.ratio))
        val ln = l.normalized
        val rn = r.normalized
        return when {
            (rn - ln).absoluteValue <= 180.degrees -> Angle.fromRadians(ratio.interpolate(ln.radians, rn.radians))
            ln < rn -> Angle.fromRadians(ratio.interpolate((ln + 360.degrees).radians, rn.radians)).normalized
            else -> Angle.fromRadians(ratio.interpolate(ln.radians, (rn + 360.degrees).radians)).normalized
        }
    }
}
