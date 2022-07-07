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
        val embed: EmbedBuilder = EmbedBuilder().setTitle("General Information").setColor(Bard.bard.rgb())
            .addField("/help", "Reopens this menu.", true)
            .setFooter("Page #1/2")
        val embed2: EmbedBuilder = EmbedBuilder().setTitle("Slash Commands").setColor(Bard.bard.rgb())
            .addField("/help", "Displays all commands.", true)
            .addField("/version", "Displays build number and version.", true)
            .setTimestamp(Instant.now())
            .setFooter("Page #2/2")

        event.hook.sendMessageEmbeds(embed.build(), embed2.build()).queue()
    }
}