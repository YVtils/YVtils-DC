package yv.tils.dc.manager.startup

import yv.tils.dc.utils.logger.Debugger

class Summarizer {
    /**
     * Start up the plugin
     */
    fun startup() {
        Debugger().log("Starting up", "Listeners loading", "yv.tils.dc.manager.startup.Summarizer")

        val listeners = Listeners()
        listeners.register()

        Debugger().log("Starting up", "Modules loading", "yv.tils.dc.manager.startup.Summarizer")

        val modules = Modules()
        modules.registerModules()

        Debugger().log("Starting up", "Other loading", "yv.tils.dc.manager.startup.Summarizer")

        val other = Other()
        other.register()
    }
}