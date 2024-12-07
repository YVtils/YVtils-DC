package yv.tils.dc.utils.configs.language

import org.bukkit.configuration.file.YamlConfiguration
import yv.tils.dc.YVtils
import java.io.File

class en_yml {
    private var file = File(YVtils.instance.dataFolder.path + "/language", "en.yml")
    private var ymlFile: YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    fun strings() {
        ymlFile.addDefault("documentation", "https://docs.yvtils.net/dc/language/en.yml")
        ymlFile.addDefault(
            "Language",
            "EN"
        )
        ymlFile.addDefault(
            "#1",
            "Please always use given variables! You can recognize them by looking for words in '<' and '>' (i.e. <prefix>)"
        )
        ymlFile.addDefault(
            "START_MESSAGE",
            "<prefix> <green>Plugin is starting!"
        )
        ymlFile.addDefault(
            "START_COMPLETED_MESSAGE",
            "<prefix> <dark_green>Plugin has started successfully!"
        )
        ymlFile.addDefault(
            "STOP_MESSAGE",
            "<prefix> <red>Plugin is getting stopped!"
        )
        ymlFile.addDefault(
            "STOP_COMPLETED_MESSAGE",
            "<prefix> <dark_red>Plugin got stopped!"
        )
        ymlFile.addDefault(
            "PLUGIN_UP_TO_DATE",
            "<prefix> <white>The Plugin has no Updates available!"
        )

        ymlFile.addDefault(
            "PLUGIN_UPDATE_AVAILABLE_PATCH",
            "<prefix> <yellow>A Patch is available! <newline><yellow>Current Version: <gray><oldVersion><newline><yellow>Newest Version: <gray><newVersion><newline><yellow>Download: <gray><link>"
        )
        ymlFile.addDefault(
            "PLUGIN_UPDATE_AVAILABLE_MINOR",
            "<prefix> <#FF8349>A Minor Feature Update is available! <newline><#FF8349>Current Version: <gray><oldVersion><newline><#FF8349>Newest Version: <gray><newVersion><newline><#FF8349>Download: <gray><link>"
        )
        ymlFile.addDefault(
            "PLUGIN_UPDATE_AVAILABLE_MAJOR",
            "<prefix> <red>A Major Update is available! It is recommended to install the update as soon as possible! <newline><red>Current Version: <gray><oldVersion><newline><red>Newest Version: <gray><newVersion><newline><red>Download: <gray><link>"
        )

        ymlFile.addDefault(
            "PLAYER_PLUGIN_UPDATE_AVAILABLE_PATCH",
            "<prefix> <yellow>A Patch is available! <newline><yellow>Current Version: <gray><oldVersion><newline><yellow>Newest Version: <gray><newVersion><newline><yellow>Download: <gray><link>"
        )
        ymlFile.addDefault(
            "PLAYER_PLUGIN_UPDATE_AVAILABLE_MINOR",
            "<prefix> <#FF8349>A Minor Feature Update is available! <newline><#FF8349>Current Version: <gray><oldVersion><newline><#FF8349>Newest Version: <gray><newVersion><newline><#FF8349>Download: <gray><link>"
        )
        ymlFile.addDefault(
            "PLAYER_PLUGIN_UPDATE_AVAILABLE_MAJOR",
            "<prefix> <red>A Major Update is available! It is recommended to install the update as soon as possible! <newline><red>Current Version: <gray><oldVersion><newline><red>Newest Version: <gray><newVersion><newline><red>Download: <gray><link>"
        )

        ymlFile.addDefault(
            "WHITELIST_EMPTY",
            "The whitelist is empty!"
        )

        ymlFile.addDefault(
            "MODULE_DISCORD_NO_BOT_TOKEN_GIVEN",
            "No bot token was found. Please enter one or disable this module!"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_STARTUP_FAILED",
            "Bot startup has failed! Please check your configurations!"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_STARTUP_FINISHED",
            "Bot startup was successful! The bot should be online now!"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_REGISTERED_NAME_CHANGE",
            "Discord user '<discordUser>' has changed his whitelisted account from '<oldName>' to '<newName>'"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_REGISTERED_NAME_ADD",
            "Discord user '<discordUser>' has added his Minecraft account '<name>' to the whitelist"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_REGISTERED_NAME_WRONG",
            "Discord user '<discordUser>' has tried to whitelist the Minecraft account '<name>', but it failed! -> Account does not exist"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_REGISTERED_NAME_SERVERERROR_CHECK_INPUT",
            "Discord user '<discordUser>' has tried to whitelist the Minecraft account '<name>', but it failed! -> Authentication server is not reachable"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_NAME_CHANGE",
            "You successfully changed your Minecraft account!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_NAME_CHANGE",
            "<oldName> -> <newName>"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_NAME_ADD",
            "You successfully whitelisted your account!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_NAME_ADD",
            "Account Name: <accountName>"
        )

        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_NAME_NOTEXISTING",
            "This Minecraft account does not exist!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_NAME_NOTEXISTING",
            "Account Name: <accountName> • Check the name and try again! If you think this could be a bug, contact the developer or server owner!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_SERVER_ERROR",
            "Authentication servers are not available!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_SERVER_ERROR",
            "Account Name: <accountName> • Try again in a few minutes/hours! If this error persist contact the developer!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_NAME_UNALLOWED_CHARACTERS",
            "This Minecraft account does not exist!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_NAME_UNALLOWED_CHARACTERS",
            "Account Name"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_ACCOUNT_ALREADY_WHITELISTED",
            "This account is already whitelisted!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_ACCOUNT_ALREADY_WHITELISTED",
            "Account Name: <accountName> • This account is already whitelisted!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_CHECK_TITLE",
            "Whitelist Check"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_CHECK_WHITELISTED_DESC",
            "Account Name: <mcName> • This account is whitelisted as <dcName>!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_CHECK_NOT_WHITELISTED_DESC",
            "Account Name: <mcName> • This account is not whitelisted!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_ADD_TITLE",
            "You successfully whitelisted this account!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_ADD_DESC",
            "Minecraft Account: <mcName> • Discord Account: <dcName>"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REPLACE_TITLE",
            "You successfully changed the whitelisted account of <dcName>!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REPLACE_DESC",
            "<oldName> -> <newName>"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REMOVE_TITLE",
            "Which account do you want to remove from the whitelist?"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REMOVE_DESC",
            "￬ Choose it down here ￬"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REMOVED_TITLE",
            "You successfully removed the Minecraft account <mcName> (<dcName>) from the whitelist!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REMOVED_DESC",
            "Do you want to remove a second account? <newline> ￬ Choose it down here ￬"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_CMD_REGISTERED_ADD",
            "Discord user '<discordUser>' has whitelisted the Minecraft account '<mcName>' (linked with '<dcName>')"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_CMD_REGISTERED_CHANGE",
            "Discord user '<discordUser>' has changed the linked account from '<dcName>' from '<oldName>' to '<newName>'"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_CMD_REGISTERED_REMOVE",
            "Discord user '<discordUser>' has removed the Minecraft account '<mcName>' (linked with '<dcName>') from the whitelist"
        )
        ymlFile.addDefault(
            "EMBED_CMD_ROLE_ADD_ERROR_TITLE",
            "There occurred an error while adding the role!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_ROLE_ADD_ERROR_DESC",
            "The role that should be given (<role>), could not be given to the user because of the wrong hierarchy! Please set the bot role over the role which should be given to the user."
        )

        ymlFile.options().copyDefaults(true)
        ymlFile.save(file)
    }
}