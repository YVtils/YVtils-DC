package yv.tils.dc.utils.language;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.dc.DiscordPlugin;

import java.io.File;
import java.io.IOException;

public class LanguageList {
    File file = new File(DiscordPlugin.getInstance().getDataFolder(), "language.yml");
    YamlConfiguration ymlfile = YamlConfiguration.loadConfiguration(file);

    public void StringInput() {
        ymlfile.addDefault("PLUGIN_UP_TO_DATE","PREFIXNOUPDATE §fPlugin is up to date.");
        ymlfile.addDefault("PLUGIN_UPDATE_AVAILABLE","PREFIXUPDATE §eThe Plugin Version NEWVERSION is now available. The Server uses OLDVERSION! Download the new Version here LINK");
        ymlfile.addDefault("WHITELIST_EMPTY", "The whitelist is empty!");
        ymlfile.addDefault("MODULE_DISCORD_NO_BOT_TOKEN_GIVEN","No Bot Token was given! Deactivate the Discord Bot Module or insert a valid Token!");
        ymlfile.addDefault("MODULE_DISCORD_STARTUP_FAILED","Bot Startup Failed! Please check your Config of the Bot");
        ymlfile.addDefault("MODULE_DISCORD_STARTUP_FINISHED","Bot Startup Finished! Bot should be Online now!");
        ymlfile.addDefault("MODULE_DISCORD_REGISTERED_NAME_CHANGE","Discord User 'DISCORDUSER' changed their whitelisted Minecraft Account from 'OLDNAME' to 'NEWNAME'");
        ymlfile.addDefault("MODULE_DISCORD_REGISTERED_NAME_ADD","Discord User 'DISCORDUSER' has whitelisted their Minecraft Account 'NAME'");
        ymlfile.addDefault("MODULE_DISCORD_REGISTERED_NAME_WRONG","Discord User 'DISCORDUSER' has tried to whitelisted the Minecraft Account 'NAME', but it failed! -> Account doesn't exist");
        ymlfile.addDefault("MODULE_DISCORD_REGISTERED_NAME_SERVERERROR_CHECK_INPUT","Discord User 'DISCORDUSER' has tried to whitelisted the Minecraft Account 'NAME', but it failed! -> Server for checking Names isn't available");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_NAME_CHANGE","You changed your whitelisted Minecraft Account successfully!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_NAME_CHANGE","OLDNAME -> NEWNAME");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_NAME_ADD","You whitelisted your Minecraft Account successfully!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_NAME_ADD","Account Name: ACCOUNTNAME");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_NAME_NOTEXISTING","This Minecraft Account doesn't exist!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_NAME_NOTEXISTING","Account Name: ACCOUNTNAME • Check your Name and try it again! When you think this is an Bug report it directly to the Developer or contact your Server Administration, that thy can contact the Developer!");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_SERVER_ERROR","Name Check Servers aren't available!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_SERVER_ERROR","Account Name: ACCOUNTNAME • Try it again in a few Minutes/Hours! When this Error don't fix contact the Developer!");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_NAME_UNALLOWED_CHARACTERS","This Minecraft Account doesn't exist!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_NAME_UNALLOWED_CHARACTERS","Account Name: ACCOUNTNAME • You used unallowed characters! Allowed are: a-z; A-Z; 0-9; _");
        ymlfile.addDefault("EMBED_BUILDER_TITLE_ACCOUNT_ALREADY_WHITELISTED","Account already whitelisted!");
        ymlfile.addDefault("EMBED_BUILDER_DESCRIPTION_ACCOUNT_ALREADY_WHITELISTED","Account Name: ACCOUNTNAME • This Account is already whitelisted!");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_CHECK_TITLE","Whitelist Check");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_CHECK_WHITELISTED_DESC","Account Name: MCNAME • This Account is whitelisted with 'DCNAME'!");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_CHECK_NOT_WHITELISTED_DESC","Account Name: MCNAME • This Account is not whitelisted!");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_ADD_TITLE","You whitelisted the Minecraft Account successfully!");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_ADD_DESC","Minecraft Account: MCNAME • Discord Account: DCNAME");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REPLACE_TITLE","You changed the whitelisted Minecraft Account from DCNAME successfully!");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REPLACE_DESC","OLDNAME -> NEWNAME");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REMOVE_TITLE","Which account do you want to remove from the whitelist?");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REMOVE_DESC","￬ Select it down there ￬");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REMOVED_TITLE","You have successfully removed the Account MCNAME (DCNAME) from the whitelist!");
        ymlfile.addDefault("EMBED_CMD_WHITELIST_REMOVED_DESC","You want to remove one more account? \n ￬ Select it down there ￬");
        ymlfile.addDefault("MODULE_DISCORD_CMD_REGISTERED_ADD","Discord User 'DISCORDUSER' has whitelisted the Minecraft Account 'MCNAME' (linked with 'DCNAME')");
        ymlfile.addDefault("MODULE_DISCORD_CMD_REGISTERED_CHANGE","Discord User 'DISCORDUSER' has changed the whitelisted Minecraft Account from 'DCNAME' from 'OLDNAME' to 'NEWNAME'");
        ymlfile.addDefault("MODULE_DISCORD_CMD_REGISTERED_REMOVE","Discord User 'DISCORDUSER' has removed the Minecraft Account 'MCNAME' (linked with 'DCNAME') from the whitelist");

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
