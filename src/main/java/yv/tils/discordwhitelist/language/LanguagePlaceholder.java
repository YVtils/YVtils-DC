package yv.tils.discordwhitelist.language;

import org.bukkit.ChatColor;
import yv.tils.discordwhitelist.DiscordWhitelist;

public class LanguagePlaceholder {

    public static String UpToDate() {
        return DiscordWhitelist.getInstance().Prefix() + ChatColor.WHITE + " Plugin is up to date.";
    }

    public static String UpdateAvailable() {
        return DiscordWhitelist.getInstance().Prefix() + ChatColor.YELLOW + " The Plugin has a new Version available. Load it here: " + ChatColor.GRAY + "https://www.spigotmc.org/resources/yvtils-ba.97642/";
    }

    public static String ConfigCreateBotToken() {
        return "YOUR TOKEN HERE";
    }

    public static String NoBotTokenGiven() {
        return "No Bot Token was given! Deactivate the Discord Bot or insert a valid Token!";
    }

    public static String BotStartupFailed() {
        return "Bot Startup Failed! Please check your Config of the Bot";
    }

    public static String BotStartupFinished() {
        return "Bot Startup Finished! Bot should be Online now!";
    }

    public static String BotActivity() {
        //Activity -> Streaming; Watching; Playing; Competing; None;
        return "You can use Streaming; Watching; Playing; Competing; None";
    }

    public static String BotActivityStreamingUrl() {
        return "You only need a Url, when you use the Activity Streaming! Otherwise you don't need one";
    }

    public static String BotStatus() {
        //Status -> Online; Idle; DND; Offline; Invisible; Unknown
        return "You can use Online; Idle; DND; Offline; Invisible";
    }

    public static String ChannelID() {
        //Status -> Online; Idle; DND; Offline; Invisible; Unknown
        return "Here you can set the Channel for the whitelist Feature! Players can write their MC Name in there and they will get whitelisted! Copy the Channel ID and paste it here";
    }

    public static String ConfigCreateChannelID() {
        //Status -> Online; Idle; DND; Offline; Invisible; Unknown
        return "CHANNEL ID HERE";
    }

    public static String DCConsoleLog_NameChangeEvent(String oldname, String newname, String discorduser) {
        return "Discord User '" + discorduser + "' changed their whitelisted Minecraft Account from '" + oldname + "' to '" + newname + "'";
    }

    public static String DCConsoleLog_NameAddEvent(String name, String discorduser) {
        return "Discord User '" + discorduser + "' has whitelisted their Minecraft Account '" + name + "'";
    }

    public static String DCConsoleLog_NameWrongEvent(String name, String discorduser) {
        return "Discord User '" + discorduser + "' has tried to whitelisted their Minecraft Account '" + name + "', but it failed! -> Account doesn't exist";
    }

    public static String DCConsoleLog_NameCheckServerError(String name, String discorduser) {
        return "Discord User '" + discorduser + "' has tried to whitelisted their Minecraft Account '" + name + "', but it failed! -> Server for checking Names isn't available";
    }

    public static String DCEmbedAuthorIcon() {
        return "You can insert here a URL (https://cdn.discordapp.com/attachments/887398222555930664/892066785766019112/buildattack.jpg) for a Icon in the Embeds!";
    }
}