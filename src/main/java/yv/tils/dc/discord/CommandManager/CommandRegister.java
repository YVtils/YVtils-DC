package yv.tils.dc.discord.CommandManager;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import yv.tils.dc.config.DiscordConfigManager;

import java.util.ArrayList;
import java.util.List;

public class CommandRegister extends ListenerAdapter {

    public void onReady(ReadyEvent e) {
        List<CommandData> commandData = new ArrayList<>();
        ServerInfoCommand(commandData);
        WhitelistCommand(commandData);
        e.getJDA().updateCommands().addCommands(commandData).queue();
    }


    private void ServerInfoCommand(List<CommandData> commandData) {
        try {
            commandData.add(Commands.slash("mcinfo", "Sends a helpful overview about the Minecraft Server")
                    .setGuildOnly(true)
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.valueOf(new DiscordConfigManager().ConfigRequest().getString("ServerInfoCommand.Permission")))));
        } catch (IllegalArgumentException ignored) {
            commandData.add(Commands.slash("mcinfo", "Sends a helpful overview about the Minecraft Server")
                    .setGuildOnly(true)
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MESSAGE_SEND)));
        }
    }

    private void WhitelistCommand(List<CommandData> commandData) {
        SubcommandData subCommandData1 = new SubcommandData("forceadd",
                "Let you add Minecraft Accounts to the Whitelist. You can link an Discord Account too, if you want to");
        SubcommandData subCommandData2 = new SubcommandData("forceremove",
                "Gives you an menu, where you can select one account, which will be removed from the whitelist");
        SubcommandData subCommandData3 = new SubcommandData("check",
                "Let you check if a Minecraft Account is whitelisted, when yes with wich discord account it is linked");

        List<OptionData> options1 = new ArrayList<>();
        List<OptionData> options2 = new ArrayList<>();
        List<OptionData> options3 = new ArrayList<>();

        //ForceAdd
        options1.add(new OptionData(OptionType.STRING, "mc_name",
                "Enter a Minecraft Ingame Name to whitelist this Name.", true));
        options1.add(new OptionData(OptionType.USER, "dc_name",
                "When you want to link an Discord Account to an MC Account, select here the Account of the User", false));

        subCommandData1.addOptions(options1);

        //ForceRemove
        subCommandData2.addOptions(options2);

        //Check
        options3.add(new OptionData(OptionType.STRING, "name", "Input a Name from Minecraft to check it", true));

        subCommandData3.addOptions(options3);


        try {
            commandData.add(Commands.slash("whitelist", "Sends a Whitelist Request to the Minecraft Server")
                    .addSubcommands(subCommandData1)
                    .addSubcommands(subCommandData2)
                    .addSubcommands(subCommandData3)
                    .setGuildOnly(true)
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.valueOf(new DiscordConfigManager().ConfigRequest().getString("WhitelistCommand.Permission")))));
        } catch (IllegalArgumentException ignored) {
            commandData.add(Commands.slash("whitelist", "Sends a Whitelist Request to the Minecraft Server")
                    .addSubcommands(subCommandData1)
                    .addSubcommands(subCommandData2)
                    .addSubcommands(subCommandData3)
                    .setGuildOnly(true)
                    .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_CHANNEL)));
        }
    }
}
