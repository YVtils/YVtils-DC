package yv.tils.dc.discord.Whitelist;

import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import yv.tils.dc.DiscordPlugin;
import yv.tils.dc.config.DiscordConfigManager;
import yv.tils.dc.utils.language.LanguageFile;
import yv.tils.dc.utils.language.LanguageMessage;

import java.util.ArrayList;
import java.util.List;

public class ForceRemove extends ListenerAdapter {

    public StringSelectMenu.Builder createMenu() {

        List<String> list = DiscordPlugin.getInstance().WhitelistManager;

        if (list.size() <= 1) {
            return StringSelectMenu.create("players").setPlaceholder(LanguageFile.getMessage(LanguageMessage.WHITELIST_EMPTY)).setDisabled(true).addOption("test", "test");
        }

        List<SelectOption> options = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            //options.add(SelectOption.of(display, value));
            options.add(SelectOption.of(list.get(i), list.get(i)));
        }

        return StringSelectMenu.create("players").setPlaceholder("Discord Tag,Minecraft Name, UUID")
                .addOptions(options);
    }

    public void onStringSelectInteraction(StringSelectInteractionEvent e) {

        List<String> list = DiscordPlugin.getInstance().WhitelistManager;

        if (e.getInteraction().getValues().isEmpty()) return;
        else {

            String values = e.getValues().toString();

            values = values.replace("[", "");
            values = values.replace("]", "");

            String[] args = values.split(",");

            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);

            list.remove(args[0] + "," + args[1] + "," + args[2]);
            player.setWhitelisted(false);
            new DiscordConfigManager().LinkedWriter(args[0], null);

            List<String> list1 = new ArrayList();
            List<String> list2 = new ArrayList();
            list1.add("DISCORDUSER");
            list2.add(e.getMember().getUser().getAsTag());
            list1.add("MCNAME");
            list2.add(args[1]);
            list1.add("DCNAME");
            list2.add(args[0]);

            Bukkit.getConsoleSender().sendMessage(new LanguageFile().ListReplacer(LanguageFile.getMessage(LanguageMessage.MODULE_DISCORD_CMD_REGISTERED_REMOVE), list1, list2));
            e.editMessageEmbeds(new yv.tils.dc.discord.EmbedManager.whitelist.discord.ForceRemove().EmbedRemoved((list.size()-1), Bukkit.hasWhitelist(), args).build()).setActionRow(createMenu().build()).queue();
        }
    }
}