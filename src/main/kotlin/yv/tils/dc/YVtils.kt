package yv.tils.dc

import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import yv.tils.dc.manager.startup.Configs
import yv.tils.dc.mods.discord.BotManager
import yv.tils.dc.utils.configs.language.LangStrings
import yv.tils.dc.utils.configs.language.Language
import yv.tils.dc.utils.internalAPI.Placeholder
import yv.tils.dc.utils.internalAPI.Vars
import yv.tils.dc.utils.logger.Debugger

class YVtils : JavaPlugin() {
    companion object {
        lateinit var instance: YVtils
        lateinit var key: NamespacedKey
    }

    val yvtilsVersion = pluginMeta.version

    override fun onLoad() {
        instance = this
        key = NamespacedKey(this, "yvtils")

        val configs = Configs()
        configs.register()
        configs.load()

        Debugger().log("Starting up", "Configs loaded", "yv.tils.dc.manager.startup.Summarizer")
    }

    override fun onEnable() {
        Debugger().log(
            "YVtils SMP Start",
            Language().directFormat(
                "This is the first real action of the plugin!",
                "Dies ist die erste richtige Aktion des Plugins!"
            ),
            "yv.tils.dc.YVtils"
        )

        server.consoleSender.sendMessage(
            Placeholder().replacer(
                Language().getMessage(LangStrings.START_MESSAGE),
                listOf("prefix"),
                listOf(Vars().prefix)
            )
        )

        try {
            yv.tils.dc.manager.startup.Summarizer().startup()

            server.consoleSender.sendMessage(
                Placeholder().replacer(
                    Language().getMessage(LangStrings.START_COMPLETED_MESSAGE),
                    listOf("prefix"),
                    listOf(Vars().prefix)
                )
            )
        } catch (e: Exception) {
            if (BotManager.started) {
                server.consoleSender.sendMessage(
                    e.message ?: "An error occurred while starting the plugin"
                )
            }
        }
    }

    override fun onDisable() {
        server.consoleSender.sendMessage(
            Placeholder().replacer(
                Language().getMessage(LangStrings.STOP_MESSAGE),
                listOf("prefix"),
                listOf(Vars().prefix)
            )
        )

        yv.tils.dc.manager.shutdown.Summarizer().shutdown()

        server.consoleSender.sendMessage(
            Placeholder().replacer(
                Language().getMessage(LangStrings.STOP_COMPLETED_MESSAGE),
                listOf("prefix"),
                listOf(Vars().prefix)
            )
        )
    }
}
