package yv.tils.discordwhitelist;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import yv.tils.discordwhitelist.language.LanguagePlaceholder;
import yv.tils.discordwhitelist.utils.UpdateChecker;

public final class DiscordWhitelist extends JavaPlugin {

    public String Prefix() {
        return "§8[§9DC-WHITELIST§8] ";
    }

    public JDA jda;
    private static DiscordWhitelist instance;

    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        new UpdateChecker(this, 45785).getLatestVersion(version -> {
            if(getDescription().getVersion().equalsIgnoreCase(version)) {
                Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.UpToDate());
            } else {
                Bukkit.getConsoleSender().sendMessage(LanguagePlaceholder.UpdateAvailable());
            }
        });

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("");
    }

    public static DiscordWhitelist getInstance() {
        return instance;
    }
}
