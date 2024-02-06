package com.github.raysato.genshincraft.guis

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.utils.LangController
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory


abstract class GUI(val player: HumanEntity) {
    abstract val title: Component
    abstract val items: Map<Int, GUIItem>
    val langController = LangController(player)

    fun getGUI(): Inventory {
        val size = 9
        val gui = Bukkit.createInventory(player, size, title)
        updateAllLocale()
        for ((key, value) in items) {
            gui.setItem(key, value.item)
        }
        return gui
    }

    fun updateAllLocale() {
        for (map in items) {
            GenshinCraft.log.info(map.value.item.type.name)
            map.value.updateLocale(langController)
        }
    }

    open fun onClick(e: InventoryClickEvent) {
        e.isCancelled = true
    }

    fun reopen() {
        player.openInventory(getGUI())
    }
}