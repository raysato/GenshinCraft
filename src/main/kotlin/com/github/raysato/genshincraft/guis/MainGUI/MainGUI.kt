package com.github.raysato.genshincraft.guis.MainGUI

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.charactars.CharactarGUIItem
import com.github.raysato.genshincraft.charactars.Charactar
import com.github.raysato.genshincraft.guis.GUI
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.persistence.PersistentDataType

class MainGUI(player: HumanEntity): GUI(player) {

    override val title = Component.text("Character")
    override val items = mapOf(
        Charactar.KOKOMI.id to CharactarGUIItem(Charactar.KOKOMI, player),
        Charactar.FISCHL.id to CharactarGUIItem(Charactar.FISCHL, player),
        8 to ChangeLocaleGUIItem()
    )

    override fun onClick(e: InventoryClickEvent) {
        val actionID = e.currentItem!!.itemMeta.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinGUIAction"), PersistentDataType.INTEGER)

        GenshinCraft.log.info(actionID.toString())
        when (actionID) {
            MainGUIAction.CHANGE_LOCALE.id -> changeLocale(e)
            MainGUIAction.CHANGE_CHARACTER.id -> changeCharacter(e)
        }
        e.isCancelled = true
    }

    private fun changeLocale(e: InventoryClickEvent) {
        val localeID = langController.getPlayerLang()
        if (e.currentItem === null) {
            return
        }
        langController.togglePlayerLang()
        e.whoClicked.persistentDataContainer.remove(NamespacedKey(GenshinCraft.instance, "genshinChar"))
        reopen()
    }

    private fun changeCharacter(e: InventoryClickEvent) {
        val clickedPlayer = e.whoClicked
        val itemCharID = e.currentItem!!.itemMeta.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)
        val playerCharID = e.whoClicked.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)
        if (e.currentItem === null || itemCharID == null) {
            return
        }
        clickedPlayer.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER, if (playerCharID === itemCharID) -1 else itemCharID)
        (items[itemCharID] as CharactarGUIItem).updateStatus()
        reopen()
    }
}