package com.github.raysato.genshincraft.utils

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class LocaleItem(_player: Player, material: Material): ItemStack(material) {
    val player = _player

    var nameLang: Lang? = null
        set(value) {
            field = value
            if (value !== null) {
                itemMeta.displayName(LangController(player).getComponent(value))
            }
        }
    var loreLang: List<Lang>? = null
        set(value) {
            field = value
            if (value !== null) {
                val langController = LangController(player)
                itemMeta.lore(value.map { langController.getComponent(it) })
            }
        }
}