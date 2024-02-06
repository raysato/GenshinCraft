package com.github.raysato.genshincraft.charactars.kokomi

import com.github.raysato.genshincraft.GenshinCraft
import org.bukkit.*
import org.bukkit.attribute.Attribute
import org.bukkit.block.data.Levelled
import org.bukkit.entity.*
import org.bukkit.scheduler.BukkitRunnable

val iterateMax = 6
class KokomiSkillTask(val kurageEntity: LivingEntity, val iterateFor: Int = iterateMax): BukkitRunnable() {
    override fun run() {
        val block = kurageEntity.location.block.getRelative(0, 3, 0)
        block.type = Material.WATER
        val levelled = block.blockData as Levelled
        levelled.level = 7
        block.blockData = levelled
        val world = kurageEntity.world
        world.spawnParticle(Particle.WATER_SPLASH, kurageEntity.location.add(0.0, 0.35, 0.0), 400, 3.0, 0.0, 3.0)
        world.playSound(kurageEntity.location, Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, SoundCategory.NEUTRAL, 0.4f, 1.5f)
        kurageEntity.getNearbyEntities(5.0, 5.0, 5.0).forEach{entity: Entity? ->
            run {
                if (entity === null || entity !is LivingEntity || entity.persistentDataContainer.has(NamespacedKey(GenshinCraft.instance, "static"))) {
                    return@forEach
                }
                val healAmount = 5.0
                when (entity.category) {
                    EntityCategory.UNDEAD -> {
                        entity.health = (entity.health - healAmount).coerceAtLeast(0.0)
                    }
                    else -> {
                        entity.health = (entity.health + healAmount).coerceAtMost(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.defaultValue ?: 20.0)
                        world.spawnParticle(Particle.VILLAGER_HAPPY, entity.location.add(0.0, 1.0, 0.0), 10, 0.5, 0.5, 0.5)
                    }
                }

            }
        }
        val squid = kurageEntity.passengers.first() as Squid
        squid.pose = Pose.SWIMMING
        squid.setRotation(0f, 0f)

        if (iterateFor > 0) {
            KokomiSkillTask(kurageEntity, iterateFor - 1).runTaskLater(GenshinCraft.instance, 40)
            return
        }
        block.type = Material.AIR
        kurageEntity.passengers[0].remove()
        kurageEntity.remove()
    }
}