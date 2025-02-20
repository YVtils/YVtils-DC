package yv.tils.dc.mods.discord.commandManager

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.utils.FileUpload
import yv.tils.dc.YVtils
import yv.tils.dc.mods.discord.embedManager.commands.HelpEmbed
import yv.tils.dc.mods.discord.embedManager.commands.ServerInfoEmbed
import yv.tils.dc.mods.discord.embedManager.whitelist.discord.ForceRemove
import yv.tils.dc.mods.discord.whitelist.Check
import yv.tils.dc.mods.discord.whitelist.ForceAdd
import yv.tils.dc.mods.discord.whitelist.ImportWhitelist
import java.io.File

class CMDHandler : ListenerAdapter() {
    override fun onSlashCommandInteraction(e: SlashCommandInteractionEvent) {
        val command = e.name
        val args = e.subcommandName

        when (command) {
            "mcinfo" -> {
                val serverIcon = File("./server-icon.png")

                if (serverIcon.exists()) {
                    e.reply("").setEmbeds(ServerInfoEmbed().embed(e.user).build()).setEphemeral(true)
                        .addFiles(FileUpload.fromData(serverIcon, "server-icon.png")).queue()
                } else {
                    e.reply("").setEmbeds(ServerInfoEmbed().embed(e.user).build()).setEphemeral(true).queue()
                }
            }

            "whitelist" -> {
                when (args) {
                    "forceadd" -> {
                        try {
                            e.reply("").setEmbeds(
                                ForceAdd().onMessageReceived(
                                    e.getOption("mc_name")!!.asString,
                                    e.getOption("dc_name")?.asMember!!,
                                    e.member!!,
                                    e.guild!!
                                ).build()
                            ).setEphemeral(true).queue()
                        } catch (_: NullPointerException) {
                            e.reply("").setEmbeds(
                                ForceAdd().onMessageReceived(
                                    e.getOption("mc_name")!!.asString,
                                    null,
                                    e.member!!,
                                    e.guild!!
                                ).build()
                            ).setEphemeral(true).queue()
                        }
                    }

                    "forceremove" -> {
                        var site: Int
                        var maxSite: Int

                        try {
                            site = e.getOption("site")!!.asString.toInt()
                            maxSite = (ImportWhitelist.whitelistManager.size - 1) / 25 + 1

                            if (site > maxSite) {
                                site = 1
                            }
                        } catch (_: NullPointerException) {
                            site = 1
                            maxSite = 1
                        }

                        e.reply("").setEmbeds(
                            ForceRemove().embed(
                                ImportWhitelist.whitelistManager.size,
                                YVtils.instance.server.hasWhitelist(),
                                site
                            ).build()
                        )
                            .setComponents(
                                ActionRow.of(ForceRemove().makeButtons(ImportWhitelist.whitelistManager.size, site)),
                                ActionRow.of(ForceRemove().makeDropDown(site).build())
                            )
                            .setEphemeral(true).queue()
                    }

                    "check" -> {
                        e.reply("").setEmbeds(
                            Check().whitelistCheck(e.getOption("name")!!.asString).build()
                        ).setEphemeral(true).queue()
                    }
                }
            }

            "help" -> {
                e.reply("").setEmbeds(HelpEmbed().embed().build()).setEphemeral(true).queue()
            }
        }
    }
}