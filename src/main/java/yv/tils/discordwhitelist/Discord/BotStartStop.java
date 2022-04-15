package yv.tils.discordwhitelist.Discord;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.discordwhitelist.DiscordWhitelist;
import yv.tils.discordwhitelist.messages.MessagePlaceholder;

import javax.security.auth.login.LoginException;
import java.io.File;

public class BotStartStop {

    File file1 = new File(DiscordWhitelist.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);

    String token = modifyFile1.getString("BotToken");
    String activity = modifyFile1.getString("Activity");
    String activitymessage = modifyFile1.getString("ActivityMessage");
    String activity_streaming_url = modifyFile1.getString("ActivityStreamingUrl");
    String status = modifyFile1.getString("OnlineStatus");


    public void TokenCheck() {
        if (token.equals(MessagePlaceholder.ConfigCreateBotToken())) {
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.NoBotTokenGiven());
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.BotStartupFailed());
        }else {
            BotSettings();
        }
    }

    JDABuilder builder = JDABuilder.createDefault(token);

    public void BotSettings() {
        //Activity -> Streaming; Watching; Playing; Competing; None;

        switch (activity.toLowerCase()) {
            case "streaming" -> builder.setActivity(Activity.streaming(activitymessage, activity_streaming_url));
            case "watching" -> builder.setActivity(Activity.watching(activitymessage));
            case "playing" -> builder.setActivity(Activity.playing(activitymessage));
            case "competing" -> builder.setActivity(Activity.competing(activitymessage));
            case "none" -> builder.setActivity(null);
            default -> builder.setActivity(Activity.playing("Minecraft"));
        }

        //Status -> Online; Idle; DND; Offline; Invisible; Unknown

        switch (status.toLowerCase()) {
            case "online" -> builder.setStatus(OnlineStatus.ONLINE);
            case "idle" -> builder.setStatus(OnlineStatus.IDLE);
            case "dnd" -> builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
            case "offline" -> builder.setStatus(OnlineStatus.OFFLINE);
            case "invisible" -> builder.setStatus(OnlineStatus.INVISIBLE);
            default -> builder.setStatus(OnlineStatus.ONLINE);
        }

        //register

        builder.addEventListeners(new WhitelistMessageGetter());

        try {
            DiscordWhitelist.getInstance().jda = builder.build();
            Bukkit.getConsoleSender().sendMessage(MessagePlaceholder.BotStartupFinished());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public void onStop() {
        if (DiscordWhitelist.getInstance().jda != null) {
            try {
                builder.setStatus(OnlineStatus.OFFLINE);
                DiscordWhitelist.getInstance().jda.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("------------------------");
                e.getCause();
                System.out.println("------------------------");
            }
        }
    }



}
