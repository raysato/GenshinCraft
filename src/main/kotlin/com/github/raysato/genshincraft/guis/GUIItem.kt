package com.github.raysato.genshincraft.guis

import com.github.raysato.genshincraft.enchants.EnchantRegister
import com.github.raysato.genshincraft.enchants.Glitter
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

open class GUIItem(itemStack: ItemStack, function: (InventoryClickEvent) -> Unit = fun(_) {}) {
    val item = itemStack;
    val fn = function

    fun addGlitter() {
        val itemMeta = item.itemMeta
        itemMeta.addEnchant(EnchantRegister.GLITTER, 0, true)
        item.setItemMeta(itemMeta)
    }

    fun removeGlitter() {
        val itemMeta = item.itemMeta
        itemMeta.removeEnchant(EnchantRegister.GLITTER)
        item.setItemMeta(itemMeta)
    }
}