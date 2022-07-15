package org.shmurda.bardsimpson

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.shmurda.bardsimpson.command.CommandHandler
import org.shmurda.bardsimpson.meep.MeepHandler
import org.shmurda.bardsimpson.task.ActivityTask
import org.shmurda.bardsimpson.util.ColourUtil
import org.shmurda.bardsimpson.util.TimeUtil
import org.shmurda.bardsimpson.util.version.VersionInfo
import org.shmurda.bardsimpson.util.version.VersionUtil
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

        println("${green}Loading Version Info...$blue (1/5)$reset")
        version = try {
            VersionUtil.generateVersionInfo()
        } catch (e: NullPointerException) {
            e.printStackTrace()

            println("${red}There was an error whilst obtaining version info, no git.properties?$reset")
            VersionInfo()
        }

        println("${green}Loading JDA...$blue (2/5)$reset")
        registerJDA()
        println("Ignore all SLF4J Logs above")

        println("${green}Loading Command Handler...$blue (3/5)$reset")
        commandHandler = CommandHandler(jda)

        println("${green}Connecting to MongoDB...$blue (4/5)$reset")
        connectMongo()

        println("${green}Loading Meep Handler...$blue (5/5)$reset")
        meepHandler = MeepHandler(jda)

        println("${green}Finished, took " + TimeUtil.elapsed(startTime) + "s.$reset")
    }

    private fun registerJDA() {
        val builder = JDABuilder.createDefault("OTk0MzAyNTk0NjkwMzMwNjQ0.GIR1RN.xGQhuyzvchKRKp4rMVd6egqwYE_Fec4cS49w3k")

        jda = builder.enableIntents(listOf(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES)).build()

        val timer = Timer()
        timer.schedule(ActivityTask(jda), 0, 10000)
    }

    private fun connectMongo() {
        mongo = MongoClient("127.0.0.1", 27017).getDatabase("bard-simpson")
    }

    fun rgb(): Color {
        return ColourUtil.nextColour()
    }

    companion object {
        @JvmStatic
        lateinit var bard: Bard
        @JvmStatic
        lateinit var version: VersionInfo
    }

}