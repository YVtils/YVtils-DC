package yv.tils.dc.manager.startup

import yv.tils.dc.mods.discord.BotManager
import yv.tils.dc.mods.discord.whitelist.ImportWhitelist

class Modules {
    /**
     * Register all modules
     */
    fun registerModules() {
        registerDiscord()
    }

    private fun registerDiscord() {
        BotManager().startBot()
        ImportWhitelist().importer()
    }
}