package com.github.raysato.genshincraft

import com.github.raysato.genshincraft.guis.MainGUI
import com.github.raysato.genshincraft.guis.GUITypes
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.persistence.PersistentDataType

object EventListener: Listener {
    @EventHandler
    fun on(e: InventoryClickEvent) {
        val item = e.currentItem
        if (item === null) {
            return
        }
        GenshinCraft.log.info(e.whoClicked.inventory.type.toString())
        if (item.itemMeta.persistentDataContainer.has(NamespacedKey(GenshinCraft.instance, "genshinGUI"), PersistentDataType.INTEGER)) {
            when(item.itemMeta.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinGUI"), PersistentDataType.INTEGER)) {
                GUITypes.CHARACTER.id -> MainGUI().onClick(e)
            }
        }
    }
}