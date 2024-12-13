package yv.tils.dc.mods.discord.embedManager.whitelist.discord

import net.dv8tion.jda.api.EmbedBuilder
import yv.tils.dc.mods.discord.embedManager.EmbedVars
import yv.tils.dc.utils.color.ColorUtils
import yv.tils.dc.utils.configs.language.LangStrings
import yv.tils.dc.utils.configs.language.Language
import yv.tils.dc.utils.internalAPI.Placeholder

class ForceAdd {
    val builder = EmbedBuilder()

    fun embed(mc: String, dc: String): EmbedBuilder {
        return builder
            .setTitle(ColorUtils().convert(Language().getMessage(LangStrings.EMBED_CMD_WHITELIST_ADD_TITLE)))
            .setDescription(
                ColorUtils().convert(
                    Placeholder().replacer(
                        Language().getMessage(LangStrings.EMBED_CMD_WHITELIST_ADD_DESC),
                        listOf("mcName", "dcName"),
                        listOf(mc, dc)
                    )
                )
            )
            .setColor(EmbedVars.successColor)
            .setFooter(EmbedVars.footerText, EmbedVars.footerIcon)
            .setAuthor(EmbedVars.authorName, EmbedVars.authorLink, EmbedVars.authorIcon)
    }

    fun embedReplace(dc: String, mcOld: String, mcNew: String): EmbedBuilder {
        return builder
            .setTitle(
                ColorUtils().convert(
                    Placeholder().replacer(
                        Language().getMessage(LangStrings.EMBED_CMD_WHITELIST_REPLACE_TITLE),
                        listOf("dcName", "oldName", "newName"),
                        listOf(dc, mcOld, mcNew)
                    )
                )
            )
            .setDescription(
                ColorUtils().convert(
                    Placeholder().replacer(
                        Language().getMessage(LangStrings.EMBED_CMD_WHITELIST_REPLACE_DESC),
                        listOf("dcName", "oldName", "newName"),
                        listOf(dc, mcOld, mcNew)
                    )
                )
            )
            .setColor(EmbedVars.infoColor)
            .setFooter(EmbedVars.footerText, EmbedVars.footerIcon)
            .setAuthor(EmbedVars.authorName, EmbedVars.authorLink, EmbedVars.authorIcon)
    }
}