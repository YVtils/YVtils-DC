package yv.tils.dc.discord.sync;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import yv.tils.dc.config.DiscordConfigManager;
import yv.tils.dc.discord.BotManager.BotStartStop;

public class ChatSync extends ListenerAdapter implements Listener {

    YamlConfiguration yml = new DiscordConfigManager().ConfigRequest();

    @EventHandler
    public void onEvent(AsyncPlayerChatEvent e) {
        if (!yml.getBoolean("ChatSync.Enabled")) return;
        if (!e.getPlayer().hasPermission("yvtils.smp.chatsync")) return;

        String message = e.getMessage();
        Player sender = e.getPlayer();

        message = ChatColor.translateAlternateColorCodes('&', message);
        message = ChatColor.stripColor(message);

        sendDCMessage(sender, message);
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        if (!yml.getBoolean("ChatSync.Enabled")) return;
        if (!e.getChannel().getId().equals(yml.getString("ChatSync.Channel"))) return;

        try {
            if (!e.getMember().hasPermission(Permission.valueOf(new DiscordConfigManager().ConfigRequest().getString("ChatSync.Permission")))) return;
        } catch (IllegalArgumentException ignored) {
            if (!e.getMember().hasPermission(Permission.ADMINISTRATOR)) return;
        }

        if (e.getMember().getUser().isBot()) return;

        String message = e.getMessage().getContentRaw();
        String sender = e.getMember().getUser().getAsTag();

        sendMCMessage(sender, message);
    }

    public void sendMCMessage(String sender, String message) {
        Bukkit.broadcastMessage("§8[§9Discord§8] §f" + sender + "§8: §f" + message);
    }

    public void sendDCMessage(Player sender, String message) {
        TextChannel textChannel = BotStartStop.getInstance().jda.getTextChannelById(yml.getString("ChatSync.Channel"));
        textChannel.sendMessageEmbeds(new ChatSyncEmbed().Embed(sender, message).build()).queue();
    }
}