package yv.tils.dc.utils.configs.language

import org.bukkit.configuration.file.YamlConfiguration
import yv.tils.dc.YVtils
import java.io.File

class de_yml {
    private var file = File(YVtils.instance.dataFolder.path + "/language", "de.yml")
    private var ymlFile: YamlConfiguration = YamlConfiguration.loadConfiguration(file)

    fun strings() {
        ymlFile.addDefault("documentation", "https://docs.yvtils.net/dc/language/de.yml")
        ymlFile.addDefault(
            "Language",
            "DE"
        )
        ymlFile.addDefault(
            "#1",
            "Bitte benutze immer alle bereits angegebenen Variablen, da ansonsten das Sprachen-System nicht korrekt funktioniert! - Variablen sind daran zu erkennen, dass sie mit kleiner/größer als Zeichen umhüllt sind (z.B. <prefix>)."
        )
        ymlFile.addDefault(
            "START_MESSAGE",
            "<prefix> <green>Plugin startet!"
        )
        ymlFile.addDefault(
            "START_COMPLETED_MESSAGE",
            "<prefix> <dark_green>YVtils-SMP Start ist abgeschlossen!"
        )
        ymlFile.addDefault(
            "STOP_MESSAGE",
            "<prefix> <red>Plugin wird gestoppt!"
        )
        ymlFile.addDefault(
            "STOP_COMPLETED_MESSAGE",
            "<prefix> <dark_red>YVtils-SMP wurde gestoppt!"
        )

        ymlFile.addDefault(
            "PLUGIN_UP_TO_DATE",
            "<prefix> <white>Das Plugin hat keine Updates zur Verfügung!"
        )
        ymlFile.addDefault(
            "PLUGIN_UPDATE_AVAILABLE_PATCH",
            "<prefix> <yellow>Es ist ein Patch verfügbar! <newline><yellow>Verwendete Version: <gray><oldVersion><newline><yellow>Neueste Version: <gray><newVersion><newline><yellow>Download: <gray><link>"
        )
        ymlFile.addDefault(
            "PLUGIN_UPDATE_AVAILABLE_MINOR",
            "<prefix> <#FF8349>Es ist ein kleines Feature Update verfügbar! <newline><yellow>Verwendete Version: <gray><oldVersion><newline><yellow>Neueste Version: <gray><newVersion><newline><yellow>Download: <gray><link>"
        )
        ymlFile.addDefault(
            "PLUGIN_UPDATE_AVAILABLE_MAJOR",
            "<prefix> <red>Es ist ein Major Update verfügbar! Es wird empfohlen, das Update so bald wie möglich zu installieren! <newline><yellow>Verwendete Version: <gray><oldVersion><newline><yellow>Neueste Version: <gray><newVersion><newline><yellow>Download: <gray><link>"
        )

        ymlFile.addDefault(
            "PLAYER_PLUGIN_UPDATE_AVAILABLE_PATCH",
            "<prefix> <yellow>Ein Patch ist verfügbar! <newline><yellow>Verwendete Version: <gray><oldVersion><newline><yellow>Neueste Version: <gray><newVersion><newline><yellow>Download: <gray><link>"
        )
        ymlFile.addDefault(
            "PLAYER_PLUGIN_UPDATE_AVAILABLE_MINOR",
            "<prefix> <#FF8349>Ein kleines Feature Update ist verfügbar! <newline><yellow>Verwendete Version: <gray><oldVersion><newline><yellow>Neueste Version: <gray><newVersion><newline><yellow>Download: <gray><link>"
        )
        ymlFile.addDefault(
            "PLAYER_PLUGIN_UPDATE_AVAILABLE_MAJOR",
            "<prefix> <red>Ein Major Update ist verfügbar! Es wird empfohlen, das Update so bald wie möglich zu installieren! <newline><yellow>Verwendete Version: <gray><oldVersion><newline><yellow>Neueste Version: <gray><newVersion><newline><yellow>Download: <gray><link>"
        )

        ymlFile.addDefault(
            "WHITELIST_EMPTY",
            "Die Whitelist ist leer!"
        )

        ymlFile.addDefault(
            "MODULE_DISCORD_NO_BOT_TOKEN_GIVEN",
            "Es wurde kein Bot Token erkannt! Deaktiviere das Bot Modul oder trage einen Token ein."
        )

        ymlFile.addDefault(
            "MODULE_DISCORD_STARTUP_FAILED",
            "Bot Start ist fehlgeschlagen! Bitte überprüfe deine Konfigurationen"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_STARTUP_FINISHED",
            "Bot Start war erfolgreich! Der Bot sollte nun Online sein!"
        )

        ymlFile.addDefault(
            "MODULE_DISCORD_REGISTERED_NAME_CHANGE",
            "Discord User '<discordUser>' hat seinen gewhitelisteten Account von '<oldName>' zu '<newName>' geändert"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_REGISTERED_NAME_ADD",
            "Discord User '<discordUser>' hat seinen Minecraft Account '<name>' zur Whitelist hinzugefügt"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_REGISTERED_NAME_WRONG",
            "Discord User '<discordUser>' hat versucht den Minecraft Account '<name>' zur Whitelist hinzuzufügen, aber es ist fehlgeschlagen! -> Account existiert nicht"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_REGISTERED_NAME_SERVERERROR_CHECK_INPUT",
            "Discord User '<discordUser>' hat versucht den Minecraft Account '<name>' zur Whitelist hinzuzufügen, aber es ist fehlgeschlagen! -> Überprüfungsserver sind nicht erreichbar"
        )

        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_NAME_CHANGE",
            "Du hast erfolgreich deinen gewhitelisteten Account geändert!"
        )

        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_NAME_CHANGE",
            "<oldName> -> <newName>"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_NAME_ADD",
            "Du hast deinen Minecraft Account erfolgreich zur Whitelist hinzugefügt!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_NAME_ADD",
            "Account Name: <accountName>"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_NAME_NOTEXISTING",
            "Dieser Minecraft Account existiert nicht!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_NAME_NOTEXISTING",
            "Account Name: <accountname> • Überprüfe den Namen und versuche es erneut! Wenn du denkst, dass es ein Fehler ist, kontaktiere den Developer oder die Server Administration, damit sie es dem Developer mitteilen können!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_SERVER_ERROR",
            "Überprüfungsserver sind nicht erreichbar!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_SERVER_ERROR",
            "Account Name: <accountname> • Versuche es in ein paar Minuten/Stunden erneut! Wenn dieser Fehler bestehen bleibt, kontaktiere den Developer!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_NAME_UNALLOWED_CHARACTERS",
            "Dieser Minecraft Account existiert nicht!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_NAME_UNALLOWED_CHARACTERS",
            "Account Name: <accountName> • Du hast unerlaubte Zeichen verwendet! Erlaubt sind: a-z; A-Z; 0-9; _"
        )

        ymlFile.addDefault(
            "EMBED_BUILDER_TITLE_ACCOUNT_ALREADY_WHITELISTED",
            "Account ist bereits auf der Whitelist!"
        )
        ymlFile.addDefault(
            "EMBED_BUILDER_DESCRIPTION_ACCOUNT_ALREADY_WHITELISTED",
            "Account Name: <accountName> • Dieser Account ist bereits auf der Whitelist!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_CHECK_TITLE",
            "Whitelist Check"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_CHECK_WHITELISTED_DESC",
            "Account Name: <mcName> • Dieser Account ist als '<dcName>' auf der Whitelist!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_CHECK_NOT_WHITELISTED_DESC",
            "Account Name: <mcName> • Dieser Account ist nicht auf der Whitelist!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_ADD_TITLE",
            "Du hast erfolgreich den Minecraft Account gewhitelistet!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_ADD_DESC",
            "Minecraft Account: <mcName> • Discord Account: <dcName>"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REPLACE_TITLE",
            "Du hast erfolgreich den gewhitelisteten Account von <dcName> geändert!"
        )

        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REPLACE_DESC",
            "<oldName> -> <newName>"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REMOVE_TITLE",
            "Welchen Account möchtest du von der whitelist entfernen?"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REMOVE_DESC",
            "￬ Wähle ihn unten aus ￬"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REMOVED_TITLE",
            "Du hast erfolgreich den Account von <mcName> (<dcName>) von der whitelist entfernt!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_WHITELIST_REMOVED_DESC",
            "Möchtest du noch einen Account entfernen? <newline> ￬ Wähle ihn unten aus ￬"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_CMD_REGISTERED_ADD",
            "Discord User '<discordUser>' hat den Minecraft Account '<mcName>' (verbunden mit '<dcName>') zur whitelist hinzugefügt"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_CMD_REGISTERED_CHANGE",
            "Discord User '<discordUser>' hat den gewhitelisteten Account von '<dcName>' von '<oldName>' zu '<newName>' geändert"
        )
        ymlFile.addDefault(
            "MODULE_DISCORD_CMD_REGISTERED_REMOVE",
            "Discord User '<discordUser>' hat den Minecraft Account '<mcName>' (verbunden mit '<dcName>') von der whitelist entfernt"
        )

        ymlFile.addDefault(
            "EMBED_CMD_ROLE_ADD_ERROR_TITLE",
            "Es ist ein Fehler beim hinzufügen aufgetreten!"
        )
        ymlFile.addDefault(
            "EMBED_CMD_ROLE_ADD_ERROR_DESC",
            "Die Rolle, welche dem User gegeben werden sollte (<role>), konnte vom Bot wegen falscher Hierarchie der Rollen nicht hinzugefügt werden! <newline>Bitte setze die Rolle vom Bot über die Rolle welche vergeben werden soll."
        )

        ymlFile.options().copyDefaults(true)
        ymlFile.save(file)
    }
}