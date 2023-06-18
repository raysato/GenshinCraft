package com.github.raysato.genshincraft

import org.bukkit.plugin.java.JavaPlugin

class GenshinCraft : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        server.pluginManager.registerEvents(EventListener, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }


}
