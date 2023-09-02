package com.github.raysato.genshincraft.guis

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class changeLocaleGUIItem(_player: Player): GUIItem(ItemStack(Material.OAK_SIGN)) {
    init {
        val meta = item.itemMeta
    }
}