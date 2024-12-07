package yv.tils.dc.manager.startup

import yv.tils.dc.YVtils
import yv.tils.dc.manager.listener.*

class Listeners {
    /**
     * Register all listeners
     */
    fun register() {
        val plugin = YVtils.instance

        // Player-related events
        plugin.server.pluginManager.registerEvents(PlayerChat(), plugin)
        plugin.server.pluginManager.registerEvents(PlayerJoinServer(), plugin)
    }
}