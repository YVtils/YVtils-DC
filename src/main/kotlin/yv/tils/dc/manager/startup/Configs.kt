package yv.tils.dc.manager.startup

import yv.tils.dc.utils.configs.discord.config_yml_discord
import yv.tils.dc.utils.configs.discord.DiscordConfig
import yv.tils.dc.utils.configs.discord.save_yml_discord
import yv.tils.dc.utils.configs.global.Config
import yv.tils.dc.utils.configs.global.config_yml
import yv.tils.dc.utils.configs.language.Language
import yv.tils.dc.utils.configs.language.de_yml
import yv.tils.dc.utils.configs.language.en_yml

class Configs {
    /**
     * Register all configs
     */
    fun register() {
        de_yml().strings()
        en_yml().strings()

        config_yml().strings()
        config_yml_discord().strings()

        save_yml_discord().strings()
    }

    /**
     * Load all configs
     */
    fun load() {
        Language().getLanguageFiles()
        Config().loadConfig()
        DiscordConfig().loadConfig()
    }
}