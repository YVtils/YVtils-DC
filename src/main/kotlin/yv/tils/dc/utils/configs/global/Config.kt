package yv.tils.dc.utils.configs.global

import yv.tils.dc.YVtils
import yv.tils.dc.utils.configs.language.LangStrings
import yv.tils.dc.utils.configs.language.Language
import yv.tils.dc.utils.logger.Debugger
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class Config {
    companion object {
        var config: MutableMap<String, Any> = HashMap()
    }

    fun loadConfig() {
        val configFile = YVtils.instance.config

        for (key in configFile.getKeys(true)) {
            config[key] = configFile.get(key) as Any
        }
    }

    fun parseTimezone(format: String = "dd/MM/yyyy HH:mm:ss"): String {
        val timezone = config["timezone"] as String

        if (timezone == "default") {
            return SimpleDateFormat(format).format(System.currentTimeMillis())
        }

        try {
            val zoneId = ZoneId.of(timezone)
            val zonedDateTime = ZonedDateTime.now(zoneId)
            val dateFormat = DateTimeFormatter.ofPattern(format)
            val formatTime = zonedDateTime.format(dateFormat)

            return formatTime
        } catch (e: Exception) {
            YVtils.instance.logger.warning(Language().getRawMessage(LangStrings.CONFIG_PARSE_ERROR_TIMEZONE))

            Debugger().log(
                "Error parsing timezone",
                "Error: ${e.message}",
                "yv.tils.dc.utils.configs.global.Config.parseTimezone()"
            )

            return "xx/xx/xxxx xx:xx:xx"
        }
    }
}