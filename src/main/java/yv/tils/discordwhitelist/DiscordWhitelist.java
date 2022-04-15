package yv.tils.discordwhitelist;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import yv.tils.discordwhitelist.Discord.BotStartStop;
import yv.tils.discordwhitelist.messages.MessagePlaceholder;
import yv.tils.discordwhitelist.utils.ConfigModeration;
import yv.tils.discordwhitelist.utils.UpdateChecker;

public final class DiscordWhitelist extends JavaPlugin {

    public String Prefix() {
        return "§8[§9DC-WHITELIST§8]";
    }

    public JDA jda;
    private static DiscordWhitelist instance;

    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        ConfigModeration configModeration = new ConfigModeration();
        //configModeration.onNameGenerate();
        configModeration.onEntranceGeneration();

        new BotStartStop().TokenCheck();

        new UpdateChecker(this, 101391).getLatestVersion(version -> {
            if(getDescription().getVersion().equalsIgnoreCase(version)) {
                Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.UpToDate());
            } else {
                Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.UpdateAvailable());
            }
        });

    }

    @Override
    public void onDisable() {
        new BotStartStop().onStop();
        Bukkit.getConsoleSender().sendMessage("");
    }

    public static DiscordWhitelist getInstance() {
        return instance;
    }
}
