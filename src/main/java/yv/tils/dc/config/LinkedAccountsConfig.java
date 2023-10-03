package yv.tils.dc.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.dc.DiscordPlugin;

import java.io.File;
import java.io.IOException;


public class LinkedAccountsConfig {
    File file = new File(DiscordPlugin.getInstance().getDataFolder(), "linkedAccounts.yml");
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlfile.addDefault("DiscordID", "Minecraft Username + UUID");
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
