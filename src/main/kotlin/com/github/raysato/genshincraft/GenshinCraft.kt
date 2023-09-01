package com.github.raysato.genshincraft

import com.github.raysato.genshincraft.enchants.EnchantRegister
import com.github.raysato.genshincraft.guis.MainGUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class GenshinCraft : JavaPlugin(), CommandExecutor {
    override fun onEnable() {
        // Plugin startup logic
        log = logger
        instance = this
        server.pluginManager.registerEvents(EventListener, this)
        EnchantRegister.register()
        log.info(instance.config.load("lang.yml").)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    override fun onCommand(sender: CommandSender, command: Command, p2: String, p3: Array<out String>): Boolean {

        logger.info("aa")
        if(command.name.equals("genshin", true)){
            val player = sender as Player
            logger.info(player.name)
            player.openInventory(MainGUI(player).getGUI(player))
            return true
        }
        return false
    }

    companion object {
        lateinit var instance: org.bukkit.plugin.Plugin
        lateinit var log: java.util.logging.Logger
    }
}
