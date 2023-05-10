package yv.tils.dc.utils;

import org.bukkit.Bukkit;
import yv.tils.dc.config.DiscordConfigManager;

public class ConsoleLog {
    public ConsoleLog(String WhatLog) {
        if (new DiscordConfigManager().ConfigRequest().getBoolean("Debug")) {
            Bukkit.getConsoleSender().sendMessage("§c----- DEBUG START -----");
            Bukkit.getConsoleSender().sendMessage("§dYVtils-DC Plugin");
            Bukkit.getConsoleSender().sendMessage(WhatLog);
            Bukkit.getConsoleSender().sendMessage("§c----- DEBUG END -----");
        }
    }
}
