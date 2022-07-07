package org.shmurda.bardsimpson.task

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Activity
import java.util.TimerTask

class ActivityTask(private val jda: JDA) : TimerTask() {

    private var stage = 0
    private val stages = listOf(
        "Bard chimes",
        "Bard plays a tune",
        "Bard laughs",
        "A meep chimes",
        "Bard sings"
    )

    override fun run() {
        if (++stage >= stages.size) stage = 0
        jda.presence.activity = Activity.listening("${stages[stage]}. /help")
    }

}