package yv.tils.dc.discord.Whitelist;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import yv.tils.dc.discord.EmbedManager.whitelist.AccountCantExist;
import yv.tils.dc.discord.EmbedManager.whitelist.discord.Check;

public class AccountCheck {

    public EmbedBuilder WhitelistCheck(String mc) {

        OfflinePlayer player = Bukkit.getOfflinePlayer(mc);

        if (!mc.matches("[a-zA-Z0-9_]+")) {
            //Account can't exist
            return new AccountCantExist().Embed(mc);
        }

        String dc = new ImportWhitelist().reader("null", mc, player.getUniqueId().toString()).get(0);

        boolean b = player.isWhitelisted();
        return new Check().Embed(mc,
                dc,
                b);
    }
}