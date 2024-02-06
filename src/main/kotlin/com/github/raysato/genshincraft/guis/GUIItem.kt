package com.github.raysato.genshincraft.guis

import com.github.raysato.genshincraft.utils.Lang
import com.github.raysato.genshincraft.utils.LangController
import org.bukkit.inventory.ItemStack

class ItemLang(val name: Lang, val lore: Lang)
open class GUIItem(itemStack: ItemStack, var langs: ItemLang) {
    val item = itemStack

    open fun updateLocale(langController: LangController) {
        val itemMeta = item.itemMeta
        itemMeta.displayName(langController.getComponent(langs.name))
        itemMeta.lore(listOf(langController.getComponent(langs.lore)))
        item.setItemMeta(itemMeta)
    }
}