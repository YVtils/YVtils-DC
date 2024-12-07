package yv.tils.dc.manager.startup

import org.bukkit.permissions.Permission
import yv.tils.dc.YVtils
import yv.tils.dc.utils.updater.PluginVersion

class Other {
    /**
     * Register all other startup tasks
     */
    fun register() {
        pluginVersion()
        permissions()
    }

    private fun pluginVersion() {
        PluginVersion().asyncUpdateChecker()
        PluginVersion().updateChecker(YVtils.instance.yvtilsVersion)
    }

    private fun permissions() {
        val pm = YVtils.instance.server.pluginManager
        pm.addPermission(Permission.loadPermission("yvtils.smp.chatSync", mapOf()))
    }
}