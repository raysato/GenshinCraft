package com.github.raysato.genshincraft

import de.tr7zw.nbtapi.NBT
import de.tr7zw.nbtapi.iface.ReadableNBT
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

object EventListener: Listener {
    @EventHandler
    fun on(e: InventoryClickEvent) {
        val item = e.currentItem
        if (NBT.get(item) { nbt: ReadableNBT -> nbt.getBoolean("isGenshinSkill") }) {

        }
    }
}