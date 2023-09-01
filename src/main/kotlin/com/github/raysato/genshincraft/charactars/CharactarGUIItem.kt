package com.github.raysato.genshincraft.charactars

import com.destroystokyo.paper.profile.ProfileProperty
import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.guis.GUIItem
import com.github.raysato.genshincraft.guis.GUITypes
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType
import java.util.*


abstract class CharactarGUIItem(textureValue: String, textureSignature: String, name: Component, lore: List<Component?>, charactarID: Int): GUIItem(ItemStack(Material.PLAYER_HEAD)) {
    init {
        GenshinCraft.log.info("init skull")
        GenshinCraft.log.info(textureValue)
        val skullMeta = item.itemMeta as SkullMeta
        val profile =  Bukkit.createProfile(UUID.randomUUID(), null);
        profile.setProperty(ProfileProperty("textures", textureValue, textureSignature))
        skullMeta.playerProfile = profile
        skullMeta.displayName(name)
        skullMeta.lore(lore)
        skullMeta.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinGUI"), PersistentDataType.INTEGER, GUITypes.CHARACTER.id)
        skullMeta.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER, charactarID)
        item.setItemMeta(skullMeta)
    }
}