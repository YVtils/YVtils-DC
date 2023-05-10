package yv.tils.dc.config;

import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.dc.DiscordPlugin;

import java.io.File;
import java.io.IOException;


public class DiscordConfigManager {
    public YamlConfiguration ConfigRequest() {
        File configfile = new File(DiscordPlugin.getInstance().getDataFolder(), "config.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public YamlConfiguration LinkedRequest() {
        File configfile = new File(DiscordPlugin.getInstance().getDataFolder(), "linkedAccounts.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public void LinkedWriter(String path,String value) {
        File configfile = new File(DiscordPlugin.getInstance().getDataFolder(), "linkedAccounts.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configfile);
        try {
            configuration.set(path, value);
            configuration.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
