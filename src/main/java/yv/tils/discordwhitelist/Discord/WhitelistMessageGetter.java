package yv.tils.discordwhitelist.Discord;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import yv.tils.discordwhitelist.DiscordWhitelist;
import yv.tils.discordwhitelist.language.LanguagePlaceholder;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WhitelistMessageGetter extends ListenerAdapter {

    //DiscordName+Tag: Minecraft Username -> Example: WolfiiYV#3204: WolfiiYV

    File file1 = new File(DiscordWhitelist.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration mc_dcbridge = YamlConfiguration.loadConfiguration(file1);
    File file3 = new File(DiscordWhitelist.getInstance().getDataFolder(), "WhitelistedDiscordPlayers.yml");
    YamlConfiguration whitelistlogfile = YamlConfiguration.loadConfiguration(file3);

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        TextChannel channel = e.getTextChannel();

        if (e.getAuthor().isBot()) {
            return;
        }

        if (e.getChannel().getId().equals(mc_dcbridge.getString("WhitelistChannelID"))) {
            Player player = Bukkit.getServer().getPlayer(e.getMessage().getContentRaw());
            OfflinePlayer player1 = Bukkit.getOfflinePlayer(e.getMessage().getContentRaw());
            String name = e.getMessage().getContentRaw();
            String MessageId = e.getMessageId();

            if (!name.matches("[a-zA-Z0-9_]+")) {
                channel.deleteMessageById(MessageId).queue();
                channel.sendMessageEmbeds(new BuildEmbeds().namehasunallowedcharacters(e.getMessage().getContentRaw(),e.getGuild()).build()).complete().delete().queueAfter(5, TimeUnit.SECONDS);
                return;
            }

            try {
                URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                int statusCode = http.getResponseCode();
                if (statusCode == 200) {
                    if (whitelistlogfile.get(e.getMember().getUser().getAsTag()) != null) {
                        String configname = e.getMember().getUser().getAsTag();
                        String configname_remove = (String) whitelistlogfile.get(configname);
                        String[] liststring = configname_remove.split(" ");
                        OfflinePlayer playerwhitelistremove = Bukkit.getOfflinePlayer(liststring[0]);
                        playerwhitelistremove.setWhitelisted(false);
                        Bukkit.getConsoleSender().sendMessage(DiscordWhitelist.getInstance().Prefix() + " §f" + LanguagePlaceholder.DCConsoleLog_NameChangeEvent(liststring[0], e.getMessage().getContentRaw(),e.getMember().getUser().getAsTag()));
                        channel.sendMessageEmbeds(new BuildEmbeds().namechanged(liststring[0], e.getMessage().getContentRaw(),e.getGuild()).build()).complete().delete().queueAfter(5, TimeUnit.SECONDS);
                    }else {
                        Bukkit.getConsoleSender().sendMessage(DiscordWhitelist.getInstance().Prefix() + " §f" + LanguagePlaceholder.DCConsoleLog_NameAddEvent(e.getMessage().getContentRaw(),e.getMember().getUser().getAsTag()));
                        channel.sendMessageEmbeds(new BuildEmbeds().namewhitelisted(e.getMessage().getContentRaw(),e.getGuild()).build()).complete().delete().queueAfter(5, TimeUnit.SECONDS);
                    }
                    channel.deleteMessageById(MessageId).queue();
                    player1.setWhitelisted(true);
                    whitelistlogfile.set(e.getMember().getUser().getAsTag() ,e.getMessage().getContentRaw() + " " + player1.getUniqueId());

                    try {
                        whitelistlogfile.save(file3);
                    } catch (IOException ev) {
                        System.out.println("---4---");
                        Bukkit.getConsoleSender().sendMessage("File Save Error");
                        System.out.println("---4---");
                    }
                }else if (statusCode == 204) {
                    channel.deleteMessageById(MessageId).queue();
                    Bukkit.getConsoleSender().sendMessage(DiscordWhitelist.getInstance().Prefix() + " §f" + LanguagePlaceholder.DCConsoleLog_NameWrongEvent(e.getMessage().getContentRaw(),e.getMember().getUser().getAsTag()));
                    channel.sendMessageEmbeds(new BuildEmbeds().namenotexisting(e.getMessage().getContentRaw(),e.getGuild()).build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
                }else {
                    channel.deleteMessageById(MessageId).queue();
                    Bukkit.getConsoleSender().sendMessage(DiscordWhitelist.getInstance().Prefix() + " §f" + LanguagePlaceholder.DCConsoleLog_NameCheckServerError(e.getMessage().getContentRaw(),e.getMember().getUser().getAsTag()));
                    channel.sendMessageEmbeds(new BuildEmbeds().namecheckservererror(e.getMessage().getContentRaw(),e.getGuild()).build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
                }
            } catch (IOException ignored) {
            }
        }
    }
}