package yv.tils.dc.manager.shutdown

import yv.tils.dc.mods.discord.BotManager

class Modules {
    fun shutdown() {
        BotManager().stopBot()
    }
}