package com.github.raysato.genshincraft.charactars

import com.destroystokyo.paper.profile.ProfileProperty
import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.guis.GUIItem
import com.github.raysato.genshincraft.guis.GUIType
import com.github.raysato.genshincraft.guis.ItemLang
import com.github.raysato.genshincraft.guis.MainGUI.MainGUIAction
import com.github.raysato.genshincraft.utils.Lang
import com.github.raysato.genshincraft.utils.LangController
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.HumanEntity
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*


class CharactarGUIItem(val character: Charactar, val player: HumanEntity) : GUIItem(ItemStack(Material.PLAYER_HEAD), ItemLang(character.displayName, character.lore)) {
    init {
        val skullMeta = item.itemMeta as SkullMeta
        val profile =  Bukkit.createProfile(UUID.randomUUID(), null)
        profile.setProperty(ProfileProperty("textures", character.textureValue, character.textureSignature))
        skullMeta.playerProfile = profile
        skullMeta.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinGUI"), PersistentDataType.INTEGER, GUIType.CHARACTER.id)
        skullMeta.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER, character.id)
        skullMeta.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinGUIAction"), PersistentDataType.INTEGER, MainGUIAction.CHANGE_CHARACTER.id)
        item.setItemMeta(skullMeta)
        updateStatus()
    }

    fun updateStatus() {
        val langController = LangController(player)
        val charID = player.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)
        if (charID == character.id) {
            val meta = item.itemMeta
            meta.displayName(langController.getComponent(Lang.TEXT_SELECTED))
            item.setItemMeta(meta)
            return
        }
    }

    override fun updateLocale(langController: LangController) {
        val itemMeta = item.itemMeta
        val langController = LangController(player)
        val charID = player.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)
        itemMeta.lore(listOf(langController.getComponent(langs.lore)))
        if (charID == character.id) {
            itemMeta.displayName(langController.getComponent(Lang.TEXT_SELECTED).append(langController.getComponent(langs.name)))
            item.setItemMeta(itemMeta)
            return
        }
        itemMeta.displayName(langController.getComponent(langs.name))
        item.setItemMeta(itemMeta)
    }
}