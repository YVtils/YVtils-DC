package yv.tils.dc.discord.sync;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.entity.Player;

import java.awt.*;

public class ChatSyncEmbed {
    EmbedBuilder builder = new EmbedBuilder();
    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(Player sender, String message) {
        return builder
                .setAuthor(sender.getName(), null, "https://cravatar.eu/head/" + sender.getName() + "/600")
                .setDescription(message)
                .setColor(new Color(0xABFF99));
    }
}