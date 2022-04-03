package yv.tils.discordwhitelist.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.discordwhitelist.DiscordWhitelist;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class BuildEmbeds {
    EmbedBuilder builder = new EmbedBuilder();

    File file1 = new File(DiscordWhitelist.getInstance().getDataFolder(), "MinecraftDiscordBridge.yml");
    YamlConfiguration mc_dcbridge = YamlConfiguration.loadConfiguration(file1);

    String st;

    public String urlisempty() {
        if (Objects.equals(mc_dcbridge.getString("EmbedAuthorIcon"), "" ) || Objects.equals(mc_dcbridge.getString("EmbedAuthorIcon"), " ")) {
        }else {
            st = mc_dcbridge.getString("EmbedAuthorIcon");
        }

        return st;
    }

    public EmbedBuilder namechanged(String oldname, String newname, Guild guild) {
        EmbedBuilder s;
        EmbedBuilder en = builder
                .setTitle("You changed your whitelisted Minecraft Account successfully!")
                .setDescription(oldname + " -> " + newname)
                .setColor(new Color(7719960))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
        if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("de")) {
            s = builder
                    .setTitle("Du hast erfolgreich deinen gewhitelisteten Account geändert!")
                    .setDescription(oldname + " -> " + newname)
                    .setColor(new Color(7719960))
                    .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                    .setAuthor("Whitelist Adminstration", null, urlisempty());
        }else {
            s= en;
        }
        return s;
    }
    public EmbedBuilder namewhitelisted(String accname, Guild guild) {
        EmbedBuilder s;
        EmbedBuilder en = builder
                .setTitle("You whitelisted your Minecraft Account successfully!")
                .setDescription("Account Name: " + accname)
                .setColor(new Color(7719960))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
        if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("de")) {
            s = builder
                    .setTitle("Du hast deinen Minecraft Account erfolgreich gewhitelistet!")
                    .setDescription("Account Name: " + accname)
                    .setColor(new Color(7719960))
                    .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                    .setAuthor("Whitelist Adminstration", null, urlisempty());
        }else {
            s= en;
        }
        return s;
    }
    public EmbedBuilder namenotexisting(String accname, Guild guild) {
        EmbedBuilder s;
        EmbedBuilder en = builder
                .setTitle("This Minecraft Account doesn't exist!")
                .setDescription("Account Name: " + accname + " • Check your Name and try it again! When you think this is an Bug report it directly to the Developer or contact your Server Administration, that thy can contact the Developer!")
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
        if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("de")) {
            s = builder
                    .setTitle("Dieser Minecraft Account existiert nicht!")
                    .setDescription("Account Name: " + accname + "• Überprüfe den Namen und versuche es erneut! Wenn du denkst das es ein Fehler ist, dann kontaktiere denn Developer oder kontaktiere die Server Administration, dass sie es dem Developer mitteilen können!")
                    .setColor(new Color(13375512))
                    .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                    .setAuthor("Whitelist Adminstration", null, urlisempty());
        }else {
            s= en;
        }
        return s;
    }
    public EmbedBuilder namecheckservererror(String accname, Guild guild) {
        EmbedBuilder s;
        EmbedBuilder en = builder
                .setTitle("Name Check Servers are not available!")
                .setDescription("Account Name: " + accname + " • Try it again in a few Minutes/Hours! When this Error don't fix contact the Developer!")
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
        if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("de")) {
            s = builder
                    .setTitle("Namen Überprüfungs Server sind nicht erreichbar!")
                    .setDescription("Account Name: " + accname + " • Versuche es in ein paar Minuten/Stunden erneut! Wenn dieser Fehler bestehen bleibt kontaktiere den Developer!")
                    .setColor(new Color(13375512))
                    .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                    .setAuthor("Whitelist Adminstration", null, urlisempty());
        }else {
            s= en;
        }
        return s;
    }
    public EmbedBuilder namehasunallowedcharacters(String accname, Guild guild) {
        EmbedBuilder s;
        EmbedBuilder en = builder
                .setTitle("This Minecraft Account doesn't exist!")
                .setDescription("Account Name: " + accname + " • You used unallowed characters! Allowed are: **a-z; A-Z; 0-9; _**")
                .setColor(new Color(13375512))
                .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                .setAuthor("Whitelist Adminstration", null, urlisempty());
        if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("en")) {
            s = en;
        }else if (DiscordWhitelist.getInstance().getConfig().getString("Language").equals("de")) {
            s = builder
                    .setTitle("Dieser Minecraft Account existiert nicht!")
                    .setDescription("Account Name: " + accname + " • Du hast unerlaubte Zeichen verwendet! Erlaubt sind: **a-z; A-Z; 0-9; _**")
                    .setColor(new Color(13375512))
                    .setFooter("YVtils-SMP • https://yvnetwork.de/yvtils-smp/spigot", "https://yvnetwork.de/wp-content/uploads/2022/03/YVtils-SMP.png")
                    .setAuthor("Whitelist Adminstration", null, urlisempty());
        }else {
            s= en;
        }
        return s;
    }
}
