package yv.tils.dc.utils.configs.global

import org.bukkit.configuration.file.YamlConfiguration
import yv.tils.dc.YVtils
import java.io.File

class config_yml {
    private val file = File(YVtils.instance.dataFolder.path, "config.yml")
    private val ymlFile: YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    fun strings() {
        ymlFile.addDefault("documentation", "https://docs.yvtils.net/dc/config.yml")
        ymlFile.addDefault("language", "en")
        ymlFile.addDefault("prefix", "<dark_gray>[<blue>YVtils-DC<dark_gray>]<white>")
        ymlFile.addDefault("serverIP", "smp.xyz.net")

        ymlFile.addDefault("timezone", "default")

        ymlFile.addDefault("playerUpdateMessage", true)

        ymlFile.addDefault("debug", false)

        ymlFile.options().copyDefaults(true)
        ymlFile.save(file)
    }
}