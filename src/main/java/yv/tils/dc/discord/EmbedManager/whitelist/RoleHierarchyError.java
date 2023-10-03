package yv.tils.dc.discord.EmbedManager.whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import yv.tils.dc.utils.language.LanguageFile;
import yv.tils.dc.utils.language.LanguageMessage;

import java.awt.*;
import java.util.ArrayList;

public class RoleHierarchyError {
    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(String role, Guild guild) {
        role = role.replace(" ", "");
        String[] roles = role.split(",");
        Role role1;

        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("ROLE");

        for (String s : roles) {
            role1 = guild.getRoleById(s);
            list2.add(role1.getAsMention());
        }

        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_WHITELIST_REMOVE_TITLE))
                .setDescription(new LanguageFile().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_ROLE_ADD_ERROR_DESC), list1, list2))
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Administration", null, url);
    }
}