package yv.tils.dc.utils.configs.discord

import org.bukkit.configuration.file.YamlConfiguration
import yv.tils.dc.YVtils
import java.io.File

class save_yml_discord {
    private var file = File(YVtils.instance.dataFolder.path, "discord/" + "save.yml")
    private var ymlFile: YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    fun strings() {
        ymlFile.addDefault("documentation", "https://docs.yvtils.net/dc/discord/save.yml")

        ymlFile.options().copyDefaults(true)
        ymlFile.save(file)
    }
}