package yv.tils.dc.discord.BotManager;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.dc.DiscordPlugin;
import yv.tils.dc.config.DiscordConfigManager;
import yv.tils.dc.discord.CommandManager.CommandHandler;
import yv.tils.dc.discord.CommandManager.CommandRegister;
import yv.tils.dc.discord.ConsoleSync.GetConsole;
import yv.tils.dc.discord.ConsoleSync.SendCMD;
import yv.tils.dc.discord.Whitelist.ForceRemove;
import yv.tils.dc.discord.Whitelist.WhitelistMessageGetter;
import yv.tils.dc.discord.sync.ChatSync;
import yv.tils.dc.discord.sync.stats.StatsDescription;
import yv.tils.dc.utils.language.LanguageFile;
import yv.tils.dc.utils.language.LanguageMessage;

import java.io.File;


public class BotStartStop {

    File file1 = new File(DiscordPlugin.getInstance().getDataFolder(), "config.yml");
    YamlConfiguration modifyFile1 = YamlConfiguration.loadConfiguration(file1);

    String token = modifyFile1.getString("BotToken");
    String mainGuild = modifyFile1.getString("MainGuild");
    String status = modifyFile1.getString("BotSettings.OnlineStatus");
    String activity = modifyFile1.getString("BotSettings.Activity");
    String activitymessage = modifyFile1.getString("BotSettings.ActivityMessage");
    String logChannel = modifyFile1.getString("LogChannel");

    public JDA jda;
    private static BotStartStop instance;

    private GetConsole appender;
    private Logger logger;

   //USE VERSION
    public void TokenCheck() {
        if (token.equals("YOUR TOKEN HERE")) {
            Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_NO_BOT_TOKEN_GIVEN));
            Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_STARTUP_FAILED));
        }else {
            instance = this;
            BotSettings();
        }
    }

    JDABuilder builder = JDABuilder.createDefault(token);

    public static BotStartStop getInstance() {
        return instance;
    }

    public void BotSettings() {

        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        //Activity -> Streaming; Watching; Playing; Competing; None;

        switch (activity.toLowerCase()) {
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

        builder.addEventListeners(new CommandRegister());
        builder.addEventListeners(new CommandHandler());
        builder.addEventListeners(new WhitelistMessageGetter());
        if (new DiscordConfigManager().ConfigRequest().getBoolean("ChatSync.Enabled")) {
            builder.addEventListeners(new ChatSync());
        }
        builder.addEventListeners(new ForceRemove());
        builder.addEventListeners(new SendCMD());

        jda = builder.build();

        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Bukkit.getConsoleSender().sendMessage(LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_STARTUP_FINISHED));

        new StatsDescription().editDesc();

        if (new DiscordConfigManager().ConfigRequest().getBoolean("ConsoleSync.Enabled")) {
            this.appender = new GetConsole(DiscordPlugin.getInstance(), this.jda);
            try {
                this.logger = (Logger) LogManager.getRootLogger();
                this.logger.addAppender(this.appender);
            } catch (Exception ignored) {
            }
            this.appender.sendMessages();
        }
    }

    public void onStop() {
        if (jda != null) {
            try {
                builder.setStatus(OnlineStatus.OFFLINE);
                jda.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("------------------------");
                e.getCause();
                System.out.println("------------------------");
            }}}}