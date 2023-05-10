package yv.tils.dc.discord.EmbedManager;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

/**
 * @since 4.6.8
 * @version 4.6.8
 */
public class LogEmbed {
    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(String desc) {
        return builder
                .setTitle("Log")
                .setDescription(desc)
                .setColor(new Color(0x2C5F4B))
                .setFooter("YVtils • https://yvnetwork.de/yvtils/", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Logger", null, url);
    }
}
