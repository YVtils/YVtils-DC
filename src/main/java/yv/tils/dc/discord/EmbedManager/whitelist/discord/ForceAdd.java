package yv.tils.dc.discord.EmbedManager.whitelist.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import yv.tils.dc.utils.language.LanguageFile;
import yv.tils.dc.utils.language.LanguageMessage;

import java.awt.*;
import java.util.ArrayList;

public class ForceAdd {
    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(String mc, String dc) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("MCNAME");
        list2.add(mc);
        list1.add("DCNAME");
        list2.add(dc);
        return builder
                .setTitle(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_WHITELIST_ADD_TITLE))
                .setDescription(new LanguageFile().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_WHITELIST_ADD_DESC), list1, list2))
                .setColor(new Color(0x85BA4C))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Administration", null, url);
    }

    public EmbedBuilder Replace(String dc, String mc_old, String mc_new) {
        java.util.List<String> list1 = new ArrayList();
        java.util.List<String> list2 = new ArrayList();
        list1.add("DCNAME");
        list2.add(dc);
        list1.add("OLDNAME");
        list2.add(mc_old);
        list1.add("NEWNAME");
        list2.add(mc_new);
        return builder
                .setTitle(new LanguageFile().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_WHITELIST_REPLACE_TITLE), list1, list2))
                .setDescription(new LanguageFile().ListReplacer(LanguageFile.getMessage(LanguageMessage.EMBED_CMD_WHITELIST_REPLACE_DESC), list1, list2))
                .setColor(new Color(7719960))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Administration", null, url);
    }
}