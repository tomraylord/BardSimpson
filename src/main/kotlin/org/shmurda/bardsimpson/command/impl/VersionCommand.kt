package org.shmurda.bardsimpson.command.impl

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import org.shmurda.bardsimpson.Bard
import org.shmurda.bardsimpson.command.BardCommand
import org.shmurda.bardsimpson.command.Cmd
import java.time.Instant

@Cmd("version", "Returns version information about the current instance.")
class VersionCommand : BardCommand() {

    override fun handleCommand(event: SlashCommandEvent) {
        val embed: EmbedBuilder = EmbedBuilder().setColor(Bard.bard.rgb())
            .setDescription("Branch: **${Bard.version.branch}** \n" +
                    "ID: **${Bard.version.commit}** \n" +
                    if (Bard.version.commit != "Unable to find commit.")
                        "<**${Bard.version.commitUser}** @ **${Bard.version.commitTime}**> \n" +
                                "**${Bard.version.commitMessage}**"
                    else "")
            .setTimestamp(Instant.now())

        event.hook.sendMessageEmbeds(embed.build()).queue()
    }

}