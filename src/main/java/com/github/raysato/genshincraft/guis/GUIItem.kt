package com.github.raysato.genshincraft.guis

import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import net.kyori.adventure.text.Component

class GUIItem(itemStack: ItemStack, function: (InventoryClickEvent) -> Unit, enchant: Boolean = false) {
    val item: ItemStack;
    val fn = function
    var doEnchant = enchant
    constructor(material: Material, name: Component, lore: List<Component>, function: (InventoryClickEvent) -> Unit, enchant: Boolean = false): this(ItemStack(material), function, enchant) {
        val itemMeta = item.itemMeta
        itemMeta.displayName(name)
        itemMeta.lore(lore)
        item.setItemMeta(itemMeta)
    }
    constructor(itemStack: ItemStack, name: Component, lore: List<Component>, function: (InventoryClickEvent) -> Unit, enchant: Boolean = false): this(itemStack, function, enchant) {
        val itemMeta = item.itemMeta
        itemMeta.displayName(name)
        itemMeta.lore(lore)
        item.setItemMeta(itemMeta)
    }
    init {
        item = itemStack
    }
}