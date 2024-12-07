package yv.tils.dc.manager.listener

import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import yv.tils.dc.mods.discord.sync.chatSync.SyncChats

class PlayerChat : Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    fun onEvent(e: AsyncChatEvent) {
        SyncChats().minecraftToDiscord(e)
    }
}