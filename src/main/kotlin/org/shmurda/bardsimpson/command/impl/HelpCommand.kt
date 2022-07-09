package org.shmurda.bardsimpson.command.impl

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import org.shmurda.bardsimpson.Bard
import org.shmurda.bardsimpson.command.BardCommand
import org.shmurda.bardsimpson.command.Cmd
import java.time.Instant

@Cmd("help", "Shows all possible commands.")
class HelpCommand : BardCommand() {

    override fun handleCommand(event: SlashCommandEvent) {
        val embed: EmbedBuilder = EmbedBuilder().setTitle("Commands").setColor(Bard.bard.rgb())
            .addField("/help", "Displays all commands.", true)
            .addField("/version", "Displays build number and version.", true)
            .setTimestamp(Instant.now())
            .setFooter("Page #1/1")

        event.hook.sendMessageEmbeds(embed.build()).queue()
    }
}