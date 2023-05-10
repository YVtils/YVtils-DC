package yv.tils.dc.discord.ConsoleSync;

import net.dv8tion.jda.api.JDA;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import yv.tils.dc.DiscordPlugin;
import yv.tils.dc.config.DiscordConfigManager;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class GetConsole extends AbstractAppender {
    private final DiscordPlugin plugin;
    private String messages = "";
    private final JDA jda;

    public GetConsole(DiscordPlugin plugin, JDA jda) {
        super("MyLogAppender" + System.currentTimeMillis(), null, null);
        this.plugin = plugin;
        this.jda = jda;
        this.start();
    }

    public void append(LogEvent e) {
        String message = e.getMessage().getFormattedMessage().toString();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        message = "[" + sdf1.format(timestamp) + " " + e.getLevel().toString() + "]: " + message + "\n";
        this.messages = this.messages + message;
    }

    public void sendMessages() {
        (new BukkitRunnable() {
            public void run() {
                try {
                    if (GetConsole.this.messages.length() != 0) {
                        GetConsole.this.messages = GetConsole.this.messages.replaceAll("\u001b\\[[;\\d]*m", "");
                        GetConsole.this.messages = GetConsole.this.messages.replaceAll("\u007F", "&");
                        GetConsole.this.messages = ChatColor.translateAlternateColorCodes('&', GetConsole.this.messages);
                        GetConsole.this.messages = ChatColor.stripColor(GetConsole.this.messages);
                        if (GetConsole.this.messages.length() > 2000) {
                            String messageTooLong = "\n\nThis message has exceeded the discord message limit (2000 characters) so the rest has been cut out. To see it completely please check the console itself!";
                            GetConsole.this.messages = GetConsole.this.messages.substring(0, 1999 - messageTooLong.length() - 6);
                            GetConsole console = GetConsole.this;
                            console.messages = console.messages + messageTooLong;
                        }

                        String channel = new DiscordConfigManager().ConfigRequest().getString("ConsoleSync.Channel");

                        try {
                            GetConsole.this.jda.getTextChannelById(channel).sendMessage("```" + GetConsole.this.messages + "```").queue();
                        } catch (NumberFormatException exce) {
                            Bukkit.getLogger().severe("[YVtils-DC -> ConsoleSync] Invalid channel ID '" + channel + "'! Make sure to put a valid channel ID in the config file!");
                            this.cancel();
                        }
                    }
                } catch (NullPointerException ignored) {}

                GetConsole.this.messages = "";
            }
        }).runTaskTimer(this.plugin, 0L, 200L);
    }
}
