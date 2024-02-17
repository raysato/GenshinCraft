package com.github.raysato.genshincraft.guis.MainGUI

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.charactars.CharactarGUIItem
import com.github.raysato.genshincraft.charactars.Charactar
import com.github.raysato.genshincraft.guis.GUI
import com.github.raysato.genshincraft.utils.DataKey
import com.github.raysato.genshincraft.utils.PersistentDataController
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
        Charactar.GANYU.id to CharactarGUIItem(Charactar.GANYU, player),
        8 to ChangeLocaleGUIItem()
    )

    override fun onClick(e: InventoryClickEvent) {
        val actionID = PersistentDataController.getData(e.currentItem!!.itemMeta, DataKey.GUI_ACTION_TYPE)

        GenshinCraft.log.info(actionID.toString())
        when (actionID) {
            MainGUIAction.CHANGE_LOCALE.id -> changeLocale(e)
            MainGUIAction.CHANGE_CHARACTER.id -> changeCharacter(e)
        }
        e.isCancelled = true
    }

    private fun changeLocale(e: InventoryClickEvent) {
        if (e.currentItem === null) {
            return
        }
        langController.togglePlayerLang()
        reopen()
    }

    private fun changeCharacter(e: InventoryClickEvent) {
        val clickedPlayer = e.whoClicked
        val itemCharID = PersistentDataController.getData(e.currentItem!!.itemMeta, DataKey.CHARACTER_TYPE)
        val playerCharID = PersistentDataController.getData(e.whoClicked, DataKey.CHARACTER_TYPE)
        if (e.currentItem === null || itemCharID == null) {
            return
        }
        clickedPlayer.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER, if (playerCharID === itemCharID) -1 else itemCharID)
        (items[itemCharID] as CharactarGUIItem).updateStatus()
        reopen()
    }
}