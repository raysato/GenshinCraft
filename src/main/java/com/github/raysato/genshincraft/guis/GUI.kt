package com.github.raysato.genshincraft.guis

import de.tr7zw.nbtapi.NBT
import de.tr7zw.nbtapi.iface.ReadableNBT
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import kotlin.math.ceil

abstract class GUI(items: Map<Int, GUIItem>, player: Player, title: Component) {
    val inventory: Inventory
    init {
        val size: Int = ceil((items.maxByOrNull { it.key }?.key ?: 0) / 9.0).toInt() * 3
        inventory = Bukkit.createInventory(player, size, title)
    }

    fun onClick(e: InventoryClickEvent) {
        val item = e.currentItem
        val gui = NBT.get(item) { nbt: ReadableNBT -> nbt.getByte("guiType") }

    }
}