package yv.tils.dc.utils.configs.global

import yv.tils.dc.YVtils

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
}