package com.github.raysato.genshincraft

import com.github.raysato.genshincraft.charactars.Charactar
import com.github.raysato.genshincraft.charactars.fischl.FischlSkill
import com.github.raysato.genshincraft.charactars.kokomi.KokomiSkill
import com.github.raysato.genshincraft.guis.MainGUI.MainGUI
import com.github.raysato.genshincraft.guis.GUIType
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.FluidLevelChangeEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType

object EventListener: Listener {
    @EventHandler
    fun onInvClick(e: InventoryClickEvent) {
        val item = e.currentItem
        if (item === null || item.itemMeta === null) {
            return
        }
        GenshinCraft.log.info(e.whoClicked.inventory.type.toString())
        if (item.itemMeta.persistentDataContainer.has(NamespacedKey(GenshinCraft.instance, "genshinGUI"), PersistentDataType.INTEGER)) {
            when(item.itemMeta.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinGUI"), PersistentDataType.INTEGER)) {
                GUIType.CHARACTER.id -> MainGUI(e.whoClicked).onClick(e)
            }
        }
    }
    @EventHandler
    fun onRightClick(e: PlayerInteractEvent) {
        val player = e.player
        if (!player.persistentDataContainer.has(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)) {
            return
        }
        when (player.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER)) {
            Charactar.KOKOMI.id -> KokomiSkill().click(e)
            Charactar.FISCHL.id -> FischlSkill().click(e)
        }
    }

    @EventHandler
    fun onBlockFromTo(e: FluidLevelChangeEvent) {
        val block = e.block
        if (block.type !== Material.WATER) {
            return
        }
        val entities = block.world.getNearbyEntities(block.location.add(0.0, -1.0, 0.0), 0.5, 0.5, 0.5)
        if (entities.isEmpty() || entities.first() == null || !entities.first().persistentDataContainer.has(NamespacedKey(GenshinCraft.instance, "static"))) {
            return

        }
        e.isCancelled = true
    }
}