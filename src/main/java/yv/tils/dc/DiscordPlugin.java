package yv.tils.dc;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import yv.tils.dc.config.DiscordConfigManager;
import yv.tils.dc.config.DiscordModuleConfig;
import yv.tils.dc.config.LinkedAccountsConfig;
import yv.tils.dc.discord.BotManager.BotStartStop;
import yv.tils.dc.discord.Whitelist.ImportWhitelist;
import yv.tils.dc.discord.sync.ChatSync;
import yv.tils.dc.utils.MessagePlaceholder;
import yv.tils.dc.utils.UpdateChecker;
import yv.tils.dc.utils.language.LanguageFile;
import yv.tils.dc.utils.language.LanguageList;
import yv.tils.dc.utils.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

public final class DiscordPlugin extends JavaPlugin {

    public JDA jda;
    private static DiscordPlugin instance;
    public final List<String> WhitelistManager = new ArrayList<>();
    public boolean chatSyncID = true;

    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        LanguageList createFile_en = new LanguageList();
        createFile_en.StringInput();
        LanguageFile.LanguageFileGet();

        //Discord Module
        DiscordModuleConfig discordModuleConfig = new DiscordModuleConfig();
        discordModuleConfig.StringInput();
        LinkedAccountsConfig linkedAccountsConfig = new LinkedAccountsConfig();
        linkedAccountsConfig.StringInput();

        if (new DiscordConfigManager().ConfigRequest().getBoolean("ChatSync.Enabled")) {
            PluginManager manager = Bukkit.getPluginManager();
            manager.registerEvents(new ChatSync(), this);
        }

        new BotStartStop().TokenCheck();

        new ImportWhitelist().Importer();


        new UpdateChecker(this, 101391).getLatestVersion(version -> {
            if(getDescription().getVersion().equalsIgnoreCase(version)) {
                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("PREFIXNOUPDATE");
                list2.add(MessagePlaceholder.PREFIXNOUPDATE);

                Bukkit.getConsoleSender().sendMessage(new LanguageFile().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UP_TO_DATE), list1, list2));
            } else {
                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("PREFIXUPDATE");
                list2.add(MessagePlaceholder.PREFIXUPDATE);
                list1.add("NEWVERSION");
                list2.add(version);
                list1.add("OLDVERSION");
                list2.add(getDescription().getVersion());
                list1.add("LINK");
                list2.add("https://www.spigotmc.org/resources/yvtils-dc.101391/");

                Bukkit.getConsoleSender().sendMessage(new LanguageFile().ListReplacer(LanguageFile.getMessage(LanguageMessage.PLUGIN_UPDATE_AVAILABLE), list1, list2));
            }
        });

    }

    @Override
    public void onDisable() {
        new BotStartStop().onStop();
        Bukkit.getConsoleSender().sendMessage("");
    }

    public static DiscordPlugin getInstance() {
        return instance;
    }
}

