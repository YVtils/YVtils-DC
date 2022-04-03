package yv.tils.discordwhitelist.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.discordwhitelist.DiscordWhitelist;
import yv.tils.discordwhitelist.messages.MessagePlaceholder;

import java.io.File;
import java.io.IOException;

public class ConfigModeration {

    File file1 = new File(DiscordWhitelist.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);
    File file2 = new File(DiscordWhitelist.getInstance().getDataFolder(), "DoNotEdit.yml");
    YamlConfiguration modifyFile2 = YamlConfiguration.loadConfiguration(file2);
    File file3 = new File(DiscordWhitelist.getInstance().getDataFolder(), "WhitelistedDiscordPlayers.yml");
    YamlConfiguration modifyFile3 = YamlConfiguration.loadConfiguration(file3);

    public void onEntranceGeneration() {

        //MinecraftDiscordBridge.yml
        modifyFile1.addDefault("Active", true);
        modifyFile1.addDefault("BotToken", MessagePlaceholder.ConfigCreateBotToken());
        modifyFile1.addDefault("0#",  MessagePlaceholder.DCEmbedAuthorIcon());
        modifyFile1.addDefault("EmbedAuthorIcon", "");
        modifyFile1.addDefault("1#", MessagePlaceholder.BotActivity());
        modifyFile1.addDefault("Activity", "none");
        modifyFile1.addDefault("ActivityMessage", "Minecraft");
        modifyFile1.addDefault("2#", MessagePlaceholder.BotActivityStreamingUrl());
        modifyFile1.addDefault("ActivityStreamingUrl", "");
        modifyFile1.addDefault("3#", MessagePlaceholder.BotStatus());
        modifyFile1.addDefault("OnlineStatus", "online");
        modifyFile1.addDefault("4#", MessagePlaceholder.ChannelID());
        modifyFile1.addDefault("WhitelistChannelID", MessagePlaceholder.ConfigCreateChannelID());
        modifyFile1.options().copyDefaults(true);

        //DoNotEdit.yml
        modifyFile2.addDefault("MissingLanguage", false);
        modifyFile2.options().copyDefaults(true);

        //WhitelistedDiscordPlayers.yml
        modifyFile3.addDefault("DiscordName+Tag", "Minecraft Username + UUID");
        modifyFile3.options().copyDefaults(true);

        onSave();
    }

    public void onSave() {
        try {
            modifyFile1.save(file1);
        } catch (IOException e) {
            System.out.println("---2---");
            Bukkit.getConsoleSender().sendMessage("Error 2");
            System.out.println("---2---");
        }
        try {
            modifyFile2.save(file2);
        } catch (IOException e) {
            System.out.println("---3---");
            Bukkit.getConsoleSender().sendMessage("Error 3");
            System.out.println("---3---");
        }
        try {
            modifyFile3.save(file3);
        } catch (IOException e) {
            System.out.println("---4---");
            Bukkit.getConsoleSender().sendMessage("Error 4");
            System.out.println("---4---");
        }
    }

    public void onNameGenerate() {
        try {
            onGenerate("MinecraftDiscordBridge.yml");
            onGenerate("DoNotEdit.yml");
            onGenerate("WhitelistedDiscordPlayers.yml");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGenerate(String name) throws Exception {
        File file = new File(DiscordWhitelist.getInstance().getDataFolder(), name);
        if (!file.exists()) {
                file.createNewFile();
        }}}