package yv.tils.dc.discord.Whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import yv.tils.dc.DiscordPlugin;
import yv.tils.dc.config.DiscordConfigManager;
import yv.tils.dc.discord.EmbedManager.whitelist.*;
import yv.tils.dc.utils.MessagePlaceholder;
import yv.tils.dc.utils.language.LanguageFile;
import yv.tils.dc.utils.language.LanguageMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ForceAdd {

    public EmbedBuilder onMessageReceived(String mc, Member dc, Member exec, Guild guild) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(mc);
        String UserID = "~"  + mc;
        String UserName = "~" + mc;

        try {
            UserID = dc.getUser().getId();
            UserName = dc.getUser().getGlobalName();
        }catch (NullPointerException ignored) {}

        if (!mc.matches("[a-zA-Z0-9_]+")) {
            //Account can't exist
            return new AccountCantExist().Embed(mc);
        }

        if (player.isWhitelisted()) {
            //Already Whitelisted
            return new AccountAlreadyListed().Embed(mc);
        }

        try {
            //Searching for Minecraft Account
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + mc);
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            int statusCode = http.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                if (new ImportWhitelist().reader(UserID, null, null).contains(UserID)) {

                    List<String> whitelist = new ImportWhitelist().reader(UserID, null, null);

                    OfflinePlayer playerwhitelistremove = Bukkit.getOfflinePlayer(whitelist.get(1));
                    playerwhitelistremove.setWhitelisted(false);
                    whitelistRemove(UserID, playerwhitelistremove.getName(), playerwhitelistremove.getUniqueId().toString());

                    List<String> list1 = new ArrayList();
                    List<String> list2 = new ArrayList();
                    list1.add("DISCORDUSER");
                    list2.add(exec.getUser().getGlobalName());
                    list1.add("DCNAME");
                    list2.add(dc.getUser().getGlobalName());
                    list1.add("OLDNAME");
                    list2.add(whitelist.get(1));
                    list1.add("NEWNAME");
                    list2.add(mc);

                    //Account Changed
                    if (dc == null) {
                        player.setWhitelisted(true);
                        DiscordPlugin.getInstance().WhitelistManager.add(UserID + "," + player.getName() + "," + player.getUniqueId());
                        new DiscordConfigManager().LinkedWriter(UserID, mc+ " " + player.getUniqueId());
                    }else {
                        try {
                            try {
                                String role = new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role");
                                role = role.replace(" ", "");
                                String[] roles = role.split(",");

                                try {
                                    for (int i = 0; i < roles.length; i++) {
                                        guild.addRoleToMember(dc, guild.getRoleById(roles[i])).queue();
                                    }
                                }catch (NumberFormatException ignored) {}

                                player.setWhitelisted(true);
                                DiscordPlugin.getInstance().WhitelistManager.add(UserID + "," + player.getName() + "," + player.getUniqueId());
                                new DiscordConfigManager().LinkedWriter(UserID, mc + " " + player.getUniqueId());
                            } catch (HierarchyException ignored) {
                                return new RoleHierarchyError().Embed(new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role"), guild);
                            }
                        } catch (IllegalArgumentException ignored) {}
                    }
                    Bukkit.getConsoleSender().sendMessage(new LanguageFile().ListReplacer(MessagePlaceholder.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_CHANGE), list1, list2));
                    return new yv.tils.dc.discord.EmbedManager.whitelist.discord.ForceAdd().Replace(UserName, whitelist.get(1), mc);
                }else {

                    List<String> list1 = new ArrayList();
                    List<String> list2 = new ArrayList();
                    list1.add("DISCORDUSER");
                    list2.add(exec.getUser().getGlobalName());
                    list1.add("DCNAME");
                    list2.add(UserName);
                    list1.add("MCNAME");
                    list2.add(mc);

                    //Account Added
                    if (dc == null) {
                        player.setWhitelisted(true);
                        DiscordPlugin.getInstance().WhitelistManager.add(UserID + "," + player.getName() + "," + player.getUniqueId());
                        new DiscordConfigManager().LinkedWriter(UserID, mc+ " " + player.getUniqueId());
                    }else {
                        try {
                            try {
                                String role = new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role");
                                role = role.replace(" ", "");
                                String[] roles = role.split(",");

                                try {
                                    for (int i = 0; i < roles.length; i++) {
                                        guild.addRoleToMember(dc, guild.getRoleById(roles[i])).queue();
                                    }
                                }catch (NumberFormatException ignored) {}

                                player.setWhitelisted(true);
                                DiscordPlugin.getInstance().WhitelistManager.add(UserID + "," + player.getName() + "," + player.getUniqueId());
                                new DiscordConfigManager().LinkedWriter(UserID, mc + " " + player.getUniqueId());
                            } catch (HierarchyException ignored) {
                                return new RoleHierarchyError().Embed(new DiscordConfigManager().ConfigRequest().getString("WhitelistFeature.Role"), guild);
                            }
                        } catch (IllegalArgumentException ignored) {}
                    }
                    Bukkit.getConsoleSender().sendMessage(new LanguageFile().ListReplacer(MessagePlaceholder.PREFIXDC + " §f" + LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_ADD), list1, list2));
                    return new yv.tils.dc.discord.EmbedManager.whitelist.discord.ForceAdd().Embed(mc, UserName);
                }
            }else if (statusCode == HttpURLConnection.HTTP_BAD_REQUEST) {

                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("DISCORDUSER");
                list2.add(UserName);
                list1.add("NAME");
                list2.add(mc);

                //Account not found
                return new AccountNotFound().Embed(mc);
            }else {

                List<String> list1 = new ArrayList();
                List<String> list2 = new ArrayList();
                list1.add("DISCORDUSER");
                list2.add(UserName);
                list1.add("NAME");
                list2.add(mc);

                //Server Error
                return new AccountCheckError().Embed(mc);
            }} catch (IOException ignored) {}
        return null;
    }

    private void whitelistAdd(String dc, String mc, String uuid) {
        DiscordPlugin.getInstance().WhitelistManager.add(dc + "," + mc + "," + uuid);
    }

    private void whitelistRemove(String dc, String mc, String uuid) {
        DiscordPlugin.getInstance().WhitelistManager.remove(dc + "," + mc + "," + uuid);
    }}