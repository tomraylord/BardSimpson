package org.shmurda.bardsimpson.command

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent

abstract class BardCommand {

    abstract fun handleCommand(event: SlashCommandEvent)

}