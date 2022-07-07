package org.shmurda.bardsimpson.command

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.shmurda.bardsimpson.command.impl.HelpCommand
import org.shmurda.bardsimpson.command.impl.VersionCommand

class CommandHandler(private val jda: JDA) : ListenerAdapter() {

    private val slashCommands: List<BardCommand> = listOf(
        HelpCommand(),
        VersionCommand()
    )

    private val mappedCommands: HashMap<String, BardCommand> = HashMap()

    init {
        jda.addEventListener(this)
        jda.updateCommands()
        registerCommands()
    }

    private fun registerCommands() {
        slashCommands.forEach {
            if (it.javaClass.isAnnotationPresent(Cmd::class.java)) {
                val cmd: Cmd = it.javaClass.getAnnotation(Cmd::class.java)
                mappedCommands[cmd.name] = it

                jda.upsertCommand(cmd.name, cmd.description).queue()
                println("Registered Command: /${cmd.name}")
            }
        }
    }

    override fun onSlashCommand(event: SlashCommandEvent) {
        for (label: String in mappedCommands.keys) {
            if (event.name == label) {
                event.deferReply().queue()

                mappedCommands[label]?.handleCommand(event)
            }
        }
    }

}