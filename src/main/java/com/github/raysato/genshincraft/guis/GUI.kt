package com.github.raysato.genshincraft.guis

import de.tr7zw.nbtapi.NBT
import de.tr7zw.nbtapi.iface.ReadableNBT
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
        return Bukkit.createInventory(player, size, title)
    }

    open fun onClick(e: InventoryClickEvent) {
        val item = e.currentItem
        items[e.slot]?.fn?.invoke(e)
    }
}