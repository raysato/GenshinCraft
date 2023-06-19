package com.github.raysato.genshincraft.guis

import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import net.kyori.adventure.text.Component

abstract class GUIItem(
    material: Material,
    name: Component,
    lore: List<Component>,
    fuction: (InventoryClickEvent) -> Unit,
    enchant: Boolean = false,
) {
    val itemStack = ItemStack(material)
    val itemMeta = itemStack.itemMeta
    init {
        itemMeta.displayName(name)
        itemMeta.lore(lore)
        itemStack.setItemMeta(itemMeta)
    }
}