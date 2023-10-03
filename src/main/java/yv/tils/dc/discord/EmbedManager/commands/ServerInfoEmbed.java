package yv.tils.dc.discord.EmbedManager.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.dc.config.DiscordConfigManager;

import java.awt.*;

public class ServerInfoEmbed {
    EmbedBuilder builder = new EmbedBuilder();

    String url = "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png";

    public EmbedBuilder Embed(User user) {
        String userID = user.getId();
        YamlConfiguration yml = new DiscordConfigManager().LinkedRequest();

        String IP = new DiscordConfigManager().ConfigRequest().getString("ServerIP");

        if (IP == null) {
            IP = "";
        }

        String MC_Name = "-";

        if (yml.get(userID) != null) {
            String[] liststring = yml.get(userID).toString().split(" ");
            OfflinePlayer playerwhitelistget = Bukkit.getOfflinePlayer(liststring[0]);

            MC_Name = playerwhitelistget.getName();
        }

        boolean viaVersion = Bukkit.getServer().getPluginManager().getPlugin("ViaVersion")!=null;

        String ServerVersionLong = Bukkit.getServer().getVersion();
        String[] Version = ServerVersionLong.split(": ");
        String[] Ver = Version[1].split("[)]");

        String version = Ver[0];

        if (viaVersion) {
            version = Ver[0] + " +";
        }

        return builder
                .setTitle("Minecraft Server Info")
                .setThumbnail("attachment://server-icon.png")
                .addField("Version", version, true)
                .addField("Difficulty", Bukkit.getServer().getWorlds().get(0).getDifficulty().name(), true)
                .addField("Linked Account", MC_Name, false)
                .setColor(new Color(0xD4E589))
                .setFooter("YVtils • https://yvnetwork.de/yvtils/", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor(new DiscordConfigManager().ConfigRequest().getString("ServerName") + " • " + IP, null, url);
    }
}
