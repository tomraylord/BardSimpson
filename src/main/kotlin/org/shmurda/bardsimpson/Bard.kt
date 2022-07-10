package org.shmurda.bardsimpson

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import org.shmurda.bardsimpson.command.CommandHandler
import org.shmurda.bardsimpson.meep.MeepHandler
import org.shmurda.bardsimpson.task.ActivityTask
import org.shmurda.bardsimpson.util.ColourUtil
import org.shmurda.bardsimpson.util.TimeUtil
import java.awt.Color
import java.util.*

class Bard {

    lateinit var jda: JDA
    lateinit var mongo: MongoDatabase
    private var commandHandler: CommandHandler
    private var meepHandler: MeepHandler

    private val reset = ColourUtil.reset
    private val green = ColourUtil.green
    private val red = ColourUtil.red
    private val blue = ColourUtil.blue

    init {
        println("${green}Starting Bard Simpson...$reset")

        bard = this
        val startTime = System.currentTimeMillis()

        println("${green}Loading JDA...$blue (1/4)$reset")
        registerJDA()
        println("Ignore all SLF4J Logs above")

        println("${green}Loading Command Handler...$blue (2/4)$reset")
        commandHandler = CommandHandler(jda)

        println("${green}Loading MongoDB...$blue (3/4)$reset")
        registerMongo()

        println("${green}Loading Meep Handler...$blue (4/4)$reset")
        meepHandler = MeepHandler(jda)

        println("${green}Finished, took " + TimeUtil.elapsed(startTime) + "s.$reset")
    }

    private fun registerJDA() {
        val builder = JDABuilder.createDefault("OTk0MzAyNTk0NjkwMzMwNjQ0.GIR1RN.xGQhuyzvchKRKp4rMVd6egqwYE_Fec4cS49w3k")

        jda = builder.enableIntents(listOf(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES)).build()

        val timer = Timer()
        timer.schedule(ActivityTask(jda), 0, 10000)
    }

    private fun registerMongo() {
        mongo = MongoClient("127.0.0.1", 27017).getDatabase("bard-simpson")
    }

    fun rgb(): Color {
        return ColourUtil.nextColour()
    }

    companion object {
        @JvmStatic
        lateinit var bard: Bard

        @JvmStatic
        fun getVersion(): String {
            return "1.0-SNAPSHOT"
        }
    }

}