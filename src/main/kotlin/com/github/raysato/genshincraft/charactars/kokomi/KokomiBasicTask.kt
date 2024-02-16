package com.github.raysato.genshincraft.charactars.kokomi

import com.github.raysato.genshincraft.GenshinCraft
import org.bukkit.Particle
import org.bukkit.SoundCategory
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.scheduler.BukkitRunnable

class KokomiBasicTask(private val projectile: Projectile, private val shooter: Player): BukkitRunnable() {
    override fun run() {
        val world = projectile.world
        world.spawnParticle(Particle.WATER_SPLASH, projectile.location, 5, 0.2, 0.2, 0.2)
        world.spawnParticle(Particle.WATER_BUBBLE, projectile.location, 7, 0.2, 0.2, 0.2)
        val entities = projectile.getNearbyEntities(1.0, 1.0, 1.0).filterNotNull().filter { entity: Entity -> entity is LivingEntity && shooter.entityId != entity.entityId }
        if (entities.isNotEmpty()) {
            val target = entities.first() as LivingEntity
            target.damage(1.0, shooter)
            projectile.remove()
            shooter.playSound(shooter.location, org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1f, 1f)
            cancel()
            return
        }
        if (projectile.isDead) {
            cancel()
            return
        }
    }
}