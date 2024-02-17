package com.github.raysato.genshincraft

import com.github.raysato.genshincraft.charactars.Charactar
import com.github.raysato.genshincraft.charactars.fischl.FischlSkill
import com.github.raysato.genshincraft.charactars.ganyu.GanyuSkill
import com.github.raysato.genshincraft.charactars.kokomi.KokomiSkill
import com.github.raysato.genshincraft.guis.MainGUI.MainGUI
import com.github.raysato.genshincraft.guis.GUIType
import com.github.raysato.genshincraft.utils.DataKey
import com.github.raysato.genshincraft.utils.PersistentDataController
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.FluidLevelChangeEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemHeldEvent

object EventListener: Listener {
    @EventHandler
    fun onInvClick(e: InventoryClickEvent) {
        val item = e.currentItem
        if (item === null || item.itemMeta === null) {
            return
        }
        val guiType = PersistentDataController.getData(item.itemMeta, DataKey.GUI_TYPE)
        if (guiType === null) {
            return
        }
        when(guiType) {
            GUIType.CHARACTER.id -> MainGUI(e.whoClicked).onClick(e)
        }
    }
    @EventHandler
    fun onRightClick(e: PlayerInteractEvent) {
        val player = e.player
        val charType = PersistentDataController.getData(player, DataKey.CHARACTER_TYPE)
        if (charType === null) {
            return
        }
        when (charType) {
            Charactar.KOKOMI.id -> KokomiSkill().click(e)
            Charactar.FISCHL.id -> FischlSkill().click(e)
            Charactar.GANYU.id -> GanyuSkill().click(e)
        }
    }

    @EventHandler
    fun onBlockFromTo(e: FluidLevelChangeEvent) {
        val block = e.block
        if (block.type !== Material.WATER) {
            return
        }
        val entities = block.world.getNearbyEntities(block.location.add(0.0, -1.0, 0.0), 0.5, 0.5, 0.5)
        if (entities.isEmpty() || entities.first() == null || PersistentDataController.getData(entities.first(), DataKey.STATIC) === null) {
            return
        }
        e.isCancelled = true
    }

    @EventHandler
    fun onBowShoot(e: EntityShootBowEvent) {
        if (e.entity.type !== EntityType.PLAYER) {
            return
        }
        val charType = PersistentDataController.getData(e.entity, DataKey.CHARACTER_TYPE)
        if (charType === null) {
            return
        }
        when (charType) {
            Charactar.GANYU.id -> GanyuSkill().chargeShot(e)
        }
    }
    @EventHandler
    fun onSwapItem(e: PlayerItemHeldEvent) {
        GanyuSkill.cancelChargeCheck(e.player)
    }

    @EventHandler
    fun onProjectileLand(e: ProjectileHitEvent) {
        val projectile = e.entity
        when (PersistentDataController.getData(projectile, DataKey.CHARACTER_TYPE)) {
            Charactar.GANYU.id -> GanyuSkill().projectileHit(e)
        }
    }
}