package org.shmurda.bardsimpson.task

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Activity
import java.util.TimerTask

class ActivityTask(private val jda: JDA) : TimerTask() {

    private var stage = 0
    private val stages = listOf(
        "over the Meeps",
        "The Simpsons",
        "@gbo_tom's twitter videos"
    )

    override fun run() {
        jda.presence.activity = Activity.watching("${stages[stage]}. /help")
        if (++stage >= stages.size) stage = 0
    }

}