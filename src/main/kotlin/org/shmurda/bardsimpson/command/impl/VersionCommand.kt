package org.shmurda.bardsimpson.command.impl

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import org.shmurda.bardsimpson.command.BardCommand
import org.shmurda.bardsimpson.command.Cmd

@Cmd("version", "Returns version information about the current instance.")
class VersionCommand : BardCommand() {

    override fun handleCommand(event: SlashCommandEvent) {
        event.hook.sendMessage("Unknown Build").queue()
    }

}