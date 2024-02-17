package com.github.raysato.genshincraft.charactars.ganyu

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.charactars.Charactar
import com.github.raysato.genshincraft.charactars.Skill
import com.github.raysato.genshincraft.utils.DataKey
import com.github.raysato.genshincraft.utils.Lang
import com.github.raysato.genshincraft.utils.PersistentDataController
import org.bukkit.*
import org.bukkit.entity.Arrow
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class GanyuSkill: Skill() {
    override val name = Lang.CHARA_KOKOMI_SKILL
    override val desc = Lang.CHARA_KOKOMI_SKILL
    override val coolDown = 240
    override val energyRequired = 0.0

    override fun click(e: PlayerInteractEvent) {
        val item = e.item
        if (item !is ItemStack || item.type !== Material.BOW) {
            return
        }
        when (e.action) {
            Action.LEFT_CLICK_AIR -> basicAttack(e)
            Action.RIGHT_CLICK_BLOCK -> skill(e)
            Action.RIGHT_CLICK_AIR -> skill(e)
            else -> return
        }
    }

    private fun basicAttack(e: PlayerInteractEvent) {
    }

    private fun skill(e: PlayerInteractEvent) {
        val player = e.player
        cancelChargeCheck(player)
        if (!player.inventory.contains(Material.ARROW)) {
            return
        }
        val task = GanyuChargeCheckTask(player).runTaskLater(GenshinCraft.instance, 40)
        PersistentDataController.setData(player, DataKey.BOW_TASK_ID, task.taskId)
    }

    fun chargeShot(e: EntityShootBowEvent) {
        val player = e.entity as Player
        val projectile = e.projectile as Arrow
        val taskID = PersistentDataController.getData(player, DataKey.BOW_TASK_ID)
        cancelChargeCheck(player)
        if (taskID == -1) {
            GanyuChargeShotTask(projectile, player).runTaskTimer(GenshinCraft.instance, 0, 1)
            PersistentDataController.setData(projectile, DataKey.CHARACTER_TYPE, Charactar.GANYU.id)
            projectile.velocity = projectile.velocity.multiply(1.3)
        }
    }

    fun projectileHit(e: ProjectileHitEvent) {
        val projectile = e.entity
        projectile.world.spawnParticle(Particle.SNOWFLAKE, projectile.location.add(0.0, 2.0, 0.0), 100, 0.1, 0.1, 0.1, 1.0, null, true)
        projectile.world.spawnParticle(Particle.BLOCK_CRACK, projectile.location.add(0.0, 2.0, 0.0), 50, 3.0, 3.0, 3.0, 1.0, Material.BLUE_ICE.createBlockData(), true)
        projectile.world.playSound(projectile.location, Sound.BLOCK_GLASS_BREAK, SoundCategory.NEUTRAL, 1.0f, 1.2f)
        projectile.getNearbyEntities(5.0, 5.0, 5.0).filterIsInstance<LivingEntity>().forEach{ entity: Entity? ->
            run {
                if (entity === null || entity !is LivingEntity || PersistentDataController.getData(entity, DataKey.STATIC) !== null) {
                    return@forEach
                }
                entity.damage(5.0, projectile.shooter as Entity)
            }
        }
        projectile.remove()
    }

    companion object {
        fun cancelChargeCheck(player: Player) {
            val taskID = PersistentDataController.getData(player, DataKey.BOW_TASK_ID)
            if (taskID !== null && taskID != -1) {
                Bukkit.getScheduler().cancelTask(taskID)
            }
            PersistentDataController.setData(player, DataKey.BOW_TASK_ID, 0)
        }
    }
}