package yv.tils.dc.discord.EmbedManager.whitelist.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import yv.tils.dc.utils.language.LanguageFile;
import yv.tils.dc.utils.language.LanguageMessage;

import java.awt.*;
import java.util.ArrayList;

public class ForceRemove {
    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(Integer playercount, boolean whitelist) {

        String status;
        if (whitelist) status = "on";
        else status = "off";

        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_WHITELIST_REMOVE_TITLE))
                .setDescription(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_WHITELIST_REMOVE_DESC))
                .addField("Whitelisted Players:", String.valueOf(playercount), true)
                .addField("Whitelist Status", status, true)
                .setColor(new Color(0xBA4C59))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Administration", null, url);
    }

    public EmbedBuilder EmbedRemoved(Integer playercount, boolean whitelist, String[] acc) {

        String status;
        if (whitelist) status = "on";
        else status = "off";

        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("MCNAME");
        list2.add(acc[1]);
        list1.add("DCNAME");
        list2.add(acc[0]);

        return builder
                .setTitle(new LanguageFile().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_WHITELIST_REMOVED_TITLE), list1, list2))
                .setDescription(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_WHITELIST_REMOVED_DESC))
                .addField("Whitelisted Players:", String.valueOf(playercount), true)
                .addField("Whitelist Status", status, true)
                .setColor(new Color(0xBA4C59))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Administration", null, url);
    }

}