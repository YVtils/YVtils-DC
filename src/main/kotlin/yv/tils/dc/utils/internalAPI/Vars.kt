package yv.tils.dc.utils.internalAPI

import yv.tils.dc.utils.configs.global.Config

class Vars {
    var prefix = "<dark_gray>[<blue>YVtils-DC<dark_gray>]<white>"
    var prefixCustom = " ${Config.config["prefix"]}"
}