package com.github.raysato.genshincraft.charactars.fischl

import com.github.raysato.genshincraft.GenshinCraft
import org.bukkit.entity.Entity
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.LivingEntity
import org.bukkit.scheduler.BukkitRunnable

class FischlSkillTask(val skillEntity: LivingEntity, val iterateFor: Int = 6, val player: HumanEntity): BukkitRunnable() {
    override fun run() {
        val world = skillEntity.world
        skillEntity.getNearbyEntities(5.0, 5.0, 5.0).filter { entity: Entity? -> entity is LivingEntity }.forEach{ entity: Entity? ->
            run {
            }
        }
        if (iterateFor > 0) {
            FischlSkillTask(skillEntity, iterateFor - 1, player).runTaskLater(GenshinCraft.instance, 20)
            return
        }
        skillEntity.remove()
    }
}