package com.github.raysato.genshincraft.guis

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.charactars.CharactarIDs
import com.github.raysato.genshincraft.charactars.kokomi.KokomiSkull
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.persistence.PersistentDataType

class MainGUI(): GUI() {
    override val title = Component.text("Character")
    override val items = mapOf(
        Pair(CharactarIDs.KOKOMI.id, KokomiSkull()),
        Pair(8, KokomiSkull())
    )
    constructor(player: Player): this() {
        if (player.persistentDataContainer.has(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)) {
            items[player.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)]?.addGlitter()
        }
    }

    override fun onClick(e: InventoryClickEvent) {
        val item = GUIItem(e.currentItem!!)
        val itemCharID = e.currentItem!!.itemMeta.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)
        if (e.currentItem === null || itemCharID == null) {
            return
        }
        val playerCharID = e.whoClicked.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)
        if (playerCharID === itemCharID) {
            e.whoClicked.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER, -1)
            item.removeGlitter()
        } else {
            e.whoClicked.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER, itemCharID)
            item.addGlitter()
        }
        e.isCancelled = true
    }
}