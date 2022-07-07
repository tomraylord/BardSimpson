package org.shmurda.bardsimpson.util

import java.awt.Color

object ColourUtil {
    @JvmStatic
    val reset = "\u001b[0m"
    @JvmStatic
    val red = "\u001b[91m"
    @JvmStatic
    val green = "\u001b[92m"
    @JvmStatic
    val blue = "\u001b[96m"

    var hue = 0F

    @JvmStatic
    fun nextColour(): Color {
        hue += 0.05F
        return Color.getHSBColor(hue, 1F, 1F)
    }
}