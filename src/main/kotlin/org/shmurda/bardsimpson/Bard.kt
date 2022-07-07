package org.shmurda.bardsimpson

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import org.shmurda.bardsimpson.command.CommandHandler
import org.shmurda.bardsimpson.util.ColourUtil
import org.shmurda.bardsimpson.util.TimeUtil

class Bard {

    private lateinit var jda: JDA
    private var commandHandler: CommandHandler

    private val reset = ColourUtil.reset
    private val green = ColourUtil.green
    private val red = ColourUtil.red
    private val blue = ColourUtil.blue

    init {
        println("${green}Starting Bard Simpson...$reset")
        bard = this
        val startTime = System.currentTimeMillis()

        println("${green}Loading JDA...$blue (1/2)$reset")
        registerJDA()
        println("Ignore all SLF4J Logs above")

        println("${green}Loading Command Handler...$blue (2/2)$reset")
        commandHandler = CommandHandler(jda)

        println("${green}Finished, took " + TimeUtil.elapsed(startTime) + "s.$reset")
    }

    private fun registerJDA() {
        val builder = JDABuilder.createDefault("OTk0MzAyNTk0NjkwMzMwNjQ0.GIR1RN.xGQhuyzvchKRKp4rMVd6egqwYE_Fec4cS49w3k")

        builder.setActivity(Activity.listening("help commands."))

        jda = builder.enableIntents(listOf(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES)).build()
        jda.upsertCommand("test", "Debug information.").queue()
    }

    companion object {
        lateinit var bard: Bard
    }

}