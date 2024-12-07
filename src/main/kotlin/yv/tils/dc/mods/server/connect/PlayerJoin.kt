package yv.tils.dc.mods.server.connect

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import yv.tils.dc.utils.configs.language.Language
import yv.tils.dc.utils.logger.Debugger
import yv.tils.dc.utils.updater.PluginVersion

class PlayerJoin {

    private var state = 1

    fun eventReceiver(e: PlayerJoinEvent) {
        Debugger().log(
            "PlayerJoin - Event Receiver",
            "Player ${e.player.name} joined the server",
            "yv.tils.dc.mods.server.connect.PlayerJoin.eventReceiver()"
        )
        funcStarter(state, e)
    }

    private fun funcStarter(state: Int, e: PlayerJoinEvent) {
        when (state) {
            1 -> setupPlayer(e, e.player)
            2 -> otherActions(e, e.player)
        }
    }

    private fun setupPlayer(e: PlayerJoinEvent, player: Player) {
        Debugger().log(
            "PlayerJoin - Setup Player",
            "Player ${player.name} joined the server",
            "yv.tils.smp.mods.server.connect.PlayerJoin.setupPlayer()"
        )

        Language.playerLang[player.uniqueId] = player.locale()

        funcStarter(state++, e)
    }

    private fun otherActions(e: PlayerJoinEvent, player: Player) {
        PluginVersion().onPlayerJoin(player)

        funcStarter(state++, e)
    }
}
