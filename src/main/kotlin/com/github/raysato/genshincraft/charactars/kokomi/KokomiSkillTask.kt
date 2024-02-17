package com.github.raysato.genshincraft.charactars.kokomi

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.utils.DataKey
import com.github.raysato.genshincraft.utils.PersistentDataController
import org.bukkit.*
import org.bukkit.attribute.Attribute
import org.bukkit.block.data.Levelled
import org.bukkit.entity.*
import org.bukkit.scheduler.BukkitRunnable
class KokomiSkillTask(val kurageEntity: LivingEntity, val iterateFor: Int = 6, val player: HumanEntity): BukkitRunnable() {
    override fun run() {
        val block = kurageEntity.location.block.getRelative(0, 3, 0)
        if (block.type === Material.AIR) {
            block.type = Material.WATER
            val levelled = block.blockData as Levelled
            levelled.level = 7
            block.blockData = levelled
        }
        val world = kurageEntity.world
        world.spawnParticle(Particle.WATER_SPLASH, kurageEntity.location.add(0.0, 0.35, 0.0), 400, 3.0, 0.0, 3.0)
        world.playSound(kurageEntity.location, Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, SoundCategory.NEUTRAL, 0.4f, 1.5f)
        kurageEntity.getNearbyEntities(5.0, 100.0, 5.0).filterIsInstance<LivingEntity>().forEach{ entity: Entity? ->
            run {
                if (entity === null || entity !is LivingEntity || PersistentDataController.getData(entity, DataKey.STATIC) !== null || entity.health <= 0) {
                    return@forEach
                }
                val healAmount = 5.0
                when (entity.category) {
                    EntityCategory.UNDEAD -> {
                        entity.damage(healAmount, player)
                    }
                    else -> {
                        entity.health = (entity.health + healAmount).coerceAtMost(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.value ?: entity.health)
                        world.spawnParticle(Particle.VILLAGER_HAPPY, entity.location.add(0.0, 1.0, 0.0), 10, 0.5, 0.5, 0.5)
                    }
                }

            }
        }
        val squid = kurageEntity.passengers.first() as Squid

        if (iterateFor > 0) {
            KokomiSkillTask(kurageEntity, iterateFor - 1, player).runTaskLater(GenshinCraft.instance, 40)
            return
        }
        if (block.type === Material.WATER) {
            block.type = Material.AIR
        }
        kurageEntity.passengers[0].remove()
        kurageEntity.remove()
    }
}