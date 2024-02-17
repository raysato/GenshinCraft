package com.github.raysato.genshincraft.charactars.ganyu

import com.github.raysato.genshincraft.GenshinCraft
import org.bukkit.Color
import org.bukkit.Particle
import org.bukkit.Particle.DustOptions
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable


class GanyuChargeShotTask(private val projectile: Arrow, private val shooter: Player): BukkitRunnable() {
    override fun run() {
        val world = projectile.world
        val dust = DustOptions(Color.AQUA, 1f)
        world.spawnParticle(Particle.REDSTONE, projectile.location, 5, 0.1, 0.1, 0.1, 1.0, dust, true)
        if (projectile.isDead) {
            GenshinCraft.log.info("deleted")
            cancel()
            return
        }
    }
}