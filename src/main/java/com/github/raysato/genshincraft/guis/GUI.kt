package com.github.raysato.genshincraft.guis

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import kotlin.math.ceil

abstract class GUI() {
    abstract val title: Component
    abstract val items: Map<Int, GUIItem>

    fun getGUI(player: Player): Inventory {
        val size: Int = ceil((items.maxByOrNull { it.key }?.key ?: 0) / 9.0).toInt() * 3
        val gui = Bukkit.createInventory(player, size, title);
        for ((key, value) in items) {
            gui.setItem(key, value.item);
        }
        return gui;
    }

    open fun onClick(e: InventoryClickEvent) {
        items[e.slot]?.fn?.invoke(e)
    }
}