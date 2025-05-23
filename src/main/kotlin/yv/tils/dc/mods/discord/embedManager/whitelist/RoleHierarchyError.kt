package yv.tils.dc.mods.discord.embedManager.whitelist

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Role
import yv.tils.dc.mods.discord.embedManager.EmbedVars
import yv.tils.dc.utils.color.ColorUtils
import yv.tils.dc.utils.configs.language.LangStrings
import yv.tils.dc.utils.configs.language.Language
import yv.tils.dc.utils.internalAPI.Placeholder

class RoleHierarchyError {
    val builder = EmbedBuilder()

    fun embed(role: String, guild: Guild?): EmbedBuilder {
        val roleList = role.replace(" ", "")
        val roles = roleList.split(",")
        var role1: Role

        val list = mutableListOf<String>()
        for (r in roles) {
            role1 = guild?.getRoleById(r)!!
            list.add(role1.asMention)
        }

        return builder
            .setTitle(ColorUtils().convert(Language().getMessage(LangStrings.EMBED_CMD_ROLE_ADD_ERROR_TITLE)))
            .setDescription(
                ColorUtils().convert(
                    Placeholder().replacer(
                        Language().getMessage(LangStrings.EMBED_CMD_ROLE_ADD_ERROR_DESC),
                        listOf("role"),
                        list
                    )
                )
            )
            .setColor(EmbedVars.errorColor)
            .setFooter(EmbedVars.footerText, EmbedVars.footerIcon)
            .setAuthor(EmbedVars.authorName, EmbedVars.authorLink, EmbedVars.authorIcon)
    }
}