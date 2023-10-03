package yv.tils.dc.discord.sync.stats;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import yv.tils.dc.DiscordPlugin;
import yv.tils.dc.config.DiscordConfigManager;
import yv.tils.dc.discord.BotManager.BotStartStop;
import yv.tils.dc.utils.ConsoleLog;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


public class StatsDescription {
    public void editDesc() {
        String ChannelID = new DiscordConfigManager().ConfigRequest().getString("ChatSync.Channel");
        List<Guild> guilds = BotStartStop.getInstance().jda.getGuilds();

        new BukkitRunnable() {
            public void run() {
                String ServerVersionLong = Bukkit.getServer().getVersion();
                String[] Version = ServerVersionLong.split(": ");
                String[] Ver = Version[1].split("[)]");

                String version = Ver[0];

                String serverip = new DiscordConfigManager().ConfigRequest().getString("ServerIP");
                String onlineplayers = String.valueOf(Bukkit.getOnlinePlayers().size());

                SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                new ConsoleLog("Task running!");

                TextChannel channel = null;

                for (int i = 0; i < guilds.size(); i++) {
                    try {
                        channel = guilds.get(i).getTextChannelById(ChannelID);
                    } catch (NumberFormatException ignored) {
                        if (!(guilds.size() > i + 1)) {
                            Bukkit.getLogger().severe("[YVtils-DC -> StatsDescription] " +
                                    ("Invalid channel ID: '" + ChannelID + "'! Make sure to put a valid channel ID in the config file or disable this feature! (plugins/YVtils-DC/config.yml/ChatSync)"));
                            this.cancel();
                            return;
                        }
                    }
                }

                if (channel == null) {
                    this.cancel();
                    return;
                }

                try {
                    if (serverip.equals("null")) {
                        channel.getManager().setTopic("Server IP: " + serverip + " Version: " + version + " Online Players: " + onlineplayers + " \nLast Updated: " + dateformat.format(timestamp)).queue();
                    }else {
                        channel.getManager().setTopic("Version: " + version + " Online Players: " + onlineplayers + " \nLast Updated: " + dateformat.format(timestamp)).queue();
                    }
                }catch (NullPointerException ignored) {
                    channel.getManager().setTopic("Version: " + version + " Online Players: " + onlineplayers + " \nLast Updated: " + dateformat.format(timestamp)).queue();
                }
            }
        }.runTaskTimer(DiscordPlugin.getInstance(), 0L, 12000L);
    }
}