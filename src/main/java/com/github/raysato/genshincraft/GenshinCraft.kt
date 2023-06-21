package com.github.raysato.genshincraft

import com.github.raysato.genshincraft.guis.CharacterGUI
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class GenshinCraft : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        server.pluginManager.registerEvents(EventListener, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if(command.name.equals("genshin", true)){
            val player = sender as Player
            player.openInventory(CharacterGUI().getGUI(player))
            return true
        }
        return false
    }


}
