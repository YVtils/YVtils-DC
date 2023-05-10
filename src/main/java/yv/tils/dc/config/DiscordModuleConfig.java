package yv.tils.dc.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.dc.DiscordPlugin;

import java.io.File;
import java.io.IOException;


public class DiscordModuleConfig {
    File file = new File(DiscordPlugin.getInstance().getDataFolder(), "config.yml");
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlfile.addDefault("BotToken", "YOUR TOKEN HERE");
        ymlfile.addDefault("MainGuild", "Guild ID");
        ymlfile.addDefault("ServerName", "A Minecraft Server");
        ymlfile.addDefault("ServerIP", "null");

        ymlfile.addDefault("1#", "You can use Online; Idle; DND; Offline; Invisible");
        ymlfile.addDefault("2#", "You can use Watching; Playing; Competing; None");
        ymlfile.addDefault("BotSettings.OnlineStatus", "online");
        ymlfile.addDefault("BotSettings.Activity", "none");
        ymlfile.addDefault("BotSettings.ActivityMessage", "Minecraft");

        ymlfile.addDefault("EmbedSettings.Author", "YVtils SMP");
        ymlfile.addDefault("EmbedSettings.AuthorIconURL", "URL");

        ymlfile.addDefault("WhitelistFeature.Channel", "CHANNEL ID");
        ymlfile.addDefault("WhitelistFeature.Role", "ROLE ID");

        ymlfile.addDefault("#3", "See here for Permission Names: https://ci.dv8tion.net/job/JDA5/javadoc/net/dv8tion/jda/api/Permission.html");
        ymlfile.addDefault("ServerInfoCommand.Permission", "PERMISSION");

        ymlfile.addDefault("WhitelistCommand.Permission", "PERMISSION");

        ymlfile.addDefault("ChatSync.Enabled", true);
        ymlfile.addDefault("ChatSync.Permission", "PERMISSION");
        ymlfile.addDefault("ChatSync.Channel", "CHANNEL ID");

        ymlfile.addDefault("ConsoleSync.Enabled", true);
        ymlfile.addDefault("ConsoleSync.Channel", "CHANNEL ID");

        ymlfile.addDefault("LogChannel", "CHANNEL ID");
        ymlfile.addDefault("Debug", false);

        ymlfile.options().copyDefaults(true);
        fileSave();
    }

    public void fileSave() {
        try {
            ymlfile.save(file);
        } catch (IOException e) {
            System.out.println("-------");
            Bukkit.getConsoleSender().sendMessage("File creation Error! Please get Support on the YVtils Support Discord");
            System.out.println("-------");
        }
    }
}
