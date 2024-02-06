package com.github.raysato.genshincraft.guis.MainGUI

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.guis.GUIItem
import com.github.raysato.genshincraft.guis.GUIType
import com.github.raysato.genshincraft.guis.ItemLang
import com.github.raysato.genshincraft.utils.Lang
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class ChangeLocaleGUIItem(): GUIItem(ItemStack(Material.OAK_SIGN), ItemLang(Lang.ITEM_TOGGLELANG_NAME, Lang.ITEM_TOGGLELANG_LORE)) {
    init {
        val meta = item.itemMeta
        meta.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinGUIAction"), PersistentDataType.INTEGER, MainGUIAction.CHANGE_LOCALE.id)
        meta.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinGUI"), PersistentDataType.INTEGER, GUIType.CHARACTER.id)
        item.setItemMeta(meta)
    }
}