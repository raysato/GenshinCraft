package com.github.raysato.genshincraft.guis.MainGUI

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.guis.GUIItem
import com.github.raysato.genshincraft.guis.GUIType
import com.github.raysato.genshincraft.guis.ItemLang
import com.github.raysato.genshincraft.utils.DataKey
import com.github.raysato.genshincraft.utils.Lang
import com.github.raysato.genshincraft.utils.PersistentDataController
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class ChangeLocaleGUIItem(): GUIItem(ItemStack(Material.OAK_SIGN), ItemLang(Lang.ITEM_TOGGLELANG_NAME, Lang.ITEM_TOGGLELANG_LORE)) {
    init {
        val meta = item.itemMeta
        PersistentDataController.setData(meta, DataKey.GUI_TYPE, GUIType.CHARACTER.id)
        PersistentDataController.setData(meta, DataKey.GUI_ACTION_TYPE, MainGUIAction.CHANGE_LOCALE.id)
        item.setItemMeta(meta)
    }
}