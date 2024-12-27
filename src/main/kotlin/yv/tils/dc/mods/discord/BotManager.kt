package yv.tils.dc.mods.discord

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.Logger
import yv.tils.dc.YVtils
import yv.tils.dc.mods.discord.commandManager.CMDHandler
import yv.tils.dc.mods.discord.commandManager.CMDRegister
import yv.tils.dc.mods.discord.sync.chatSync.SyncChats
import yv.tils.dc.mods.discord.sync.consoleSync.GetConsole
import yv.tils.dc.mods.discord.sync.consoleSync.SendCMD
import yv.tils.dc.mods.discord.sync.stats.CollectStats
import yv.tils.dc.mods.discord.sync.stats.StatsChannel
import yv.tils.dc.mods.discord.sync.stats.StatsDescription
import yv.tils.dc.mods.discord.whitelist.ForceRemove
import yv.tils.dc.mods.discord.whitelist.SelfAdd
import yv.tils.dc.utils.color.ColorUtils
import yv.tils.dc.utils.configs.discord.DiscordConfig
import yv.tils.dc.utils.configs.language.LangStrings
import yv.tils.dc.utils.configs.language.Language
import yv.tils.dc.utils.logger.Debugger
import java.time.Duration

class BotManager {
    companion object {
        lateinit var instance: BotManager
        lateinit var jda: JDA
        lateinit var builder: JDABuilder
        var started = false
    }

    private val token = DiscordConfig.config["botToken"] as String
    private val mainGuild = DiscordConfig().readChannelID("mainGuild")
    private val status = DiscordConfig.config["botSettings.onlineStatus"] as String
    private val activity = DiscordConfig.config["botSettings.activity"] as String
    private val activityMessage = DiscordConfig.config["botSettings.activityMessage"] as String
    private val logChannel = DiscordConfig().readChannelID("logChannel")

    fun startBot() {
        if (checkToken()) {
            instance = this
            appearance()
        }
    }

    private fun checkToken(): Boolean {
        if (!(token.isEmpty() || token.isBlank() || token == ColorUtils().convert(
                Language().directFormat(
                    "YOUR TOKEN HERE",
                    "DEINEN BOT TOKEN"
                )
            ))
        ) {
            builder = JDABuilder.createDefault(token)
            return true
        } else {
            YVtils.instance.logger.severe(Language().getRawMessage(LangStrings.MODULE_DISCORD_NO_BOT_TOKEN_GIVEN))
            YVtils.instance.logger.severe(Language().getRawMessage(LangStrings.MODULE_DISCORD_STARTUP_FAILED))
            started = false
            YVtils.instance.server.pluginManager.disablePlugin(YVtils.instance)
            return false
        }
    }

    private fun appearance() {
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT)
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS)
        builder.setMemberCachePolicy(MemberCachePolicy.ALL)

        when (activity.lowercase()) {
            "playing" -> builder.setActivity(Activity.playing(activityMessage))
            "listening" -> builder.setActivity(Activity.listening(activityMessage))
            "watching" -> builder.setActivity(Activity.watching(activityMessage))
            "competing" -> builder.setActivity(Activity.competing(activityMessage))
            "custom" -> builder.setActivity(Activity.customStatus(activityMessage))
            else -> builder.setActivity(null)
        }

        when (status.lowercase()) {
            "online" -> builder.setStatus(OnlineStatus.ONLINE)
            "idle" -> builder.setStatus(OnlineStatus.IDLE)
            "dnd" -> builder.setStatus(OnlineStatus.DO_NOT_DISTURB)
            "invisible" -> builder.setStatus(OnlineStatus.INVISIBLE)
            else -> builder.setStatus(OnlineStatus.ONLINE)
        }

        builder.addEventListeners(CMDRegister())
        builder.addEventListeners(CMDHandler())
        builder.addEventListeners(ForceRemove())
        builder.addEventListeners(SelfAdd())
        builder.addEventListeners(SyncChats())
        builder.addEventListeners(SendCMD())

        try {
            jda = builder.build()
        } catch (e: Exception) {
            YVtils.instance.server.consoleSender.sendMessage(Language().getMessage(LangStrings.MODULE_DISCORD_STARTUP_FAILED))
            e.printStackTrace()
        }

        try {
            jda.awaitReady()
        } catch (e: Exception) {
            YVtils.instance.server.consoleSender.sendMessage(Language().getMessage(LangStrings.MODULE_DISCORD_STARTUP_FAILED))
            e.printStackTrace()
        }

        val appender = GetConsole()
        runCatching {
            val logger = LogManager.getRootLogger() as Logger
            logger.addAppender(appender)
        }
        appender.syncTask()

        CollectStats().collect()

        YVtils.instance.server.consoleSender.sendMessage(Language().getMessage(LangStrings.MODULE_DISCORD_STARTUP_FINISHED))
    }

    fun stopBot() {
        try {
            StatsChannel().deleteChannels()
            StatsDescription().serverShutdown()

            GetConsole.active = false
            GetConsole().stop()

            try {
                builder.setStatus(OnlineStatus.OFFLINE)
                jda.shutdown()

                if (!jda.awaitShutdown(Duration.ofSeconds(10))) {
                    jda.shutdownNow()
                    jda.awaitShutdown()
                }
            } catch (e: Exception) {
                YVtils.instance.server.consoleSender.sendMessage(
                    Language().directFormat(
                        "There was an error while shutting down the bot, for more details enable debug in the config.yml file!",
                        "Es gab einen Fehler beim Herunterfahren des Bots, um weitere Details zu erhalten, aktiviere das Debuggen in der config.yml-Datei"
                    )
                )

                val message: String = e.message.toString()
                Debugger().log("Bot shutting down failed!", message, "yv.tils.dc.mods.discord.BotManager.stopBot()")
            }
        }catch (e: UninitializedPropertyAccessException) {
            YVtils.instance.server.consoleSender.sendMessage(
                Language().directFormat(
                    "There was an error while shutting down the bot, for more details enable debug in the config.yml file!",
                    "Es gab einen Fehler beim Herunterfahren des Bots, um weitere Details zu erhalten, aktiviere das Debuggen in der config.yml-Datei"
                )
            )

            val message: String = e.message.toString()
            Debugger().log("Bot shutting down failed!", message, "yv.tils.dc.mods.discord.BotManager.stopBot()")
        }
    }
}