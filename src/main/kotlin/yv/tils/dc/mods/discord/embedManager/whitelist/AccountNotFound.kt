package yv.tils.dc.mods.discord.embedManager.whitelist

import net.dv8tion.jda.api.EmbedBuilder
import yv.tils.dc.mods.discord.embedManager.EmbedVars
import yv.tils.dc.utils.color.ColorUtils
import yv.tils.dc.utils.configs.language.LangStrings
import yv.tils.dc.utils.configs.language.Language
import yv.tils.dc.utils.internalAPI.Placeholder

class AccountNotFound {
    val builder = EmbedBuilder()

    fun embed(accName: String): EmbedBuilder {
        return builder
            .setTitle(ColorUtils().convert(Language().getMessage(LangStrings.EMBED_BUILDER_TITLE_NAME_NOTEXISTING)))
            .setDescription(
                ColorUtils().convert(
                    Placeholder().replacer(
                        Language().getMessage(LangStrings.EMBED_BUILDER_DESCRIPTION_NAME_NOTEXISTING),
                        listOf("accountName"),
                        listOf(accName)
                    )
                )
            )
            .setColor(EmbedVars.errorColor)
            .setFooter(EmbedVars.footerText, EmbedVars.footerIcon)
            .setAuthor(EmbedVars.authorName, EmbedVars.authorLink, EmbedVars.authorIcon)
    }
}