package com.github.raysato.genshincraft.charactars.kokomi

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.charactars.Skill
import com.github.raysato.genshincraft.utils.DataKey
import com.github.raysato.genshincraft.utils.Lang
import com.github.raysato.genshincraft.utils.PersistentDataController
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import kotlin.math.floor

class KokomiSkill: Skill() {
    override val name = Lang.CHARA_KOKOMI_SKILL
    override val desc = Lang.CHARA_KOKOMI_SKILL
    override val coolDown = 240
    override val energyRequired = 0.0

    override fun click(e: PlayerInteractEvent) {
        val item = e.item
        if (item !is ItemStack || item.type !== Material.BOOK) {
            return
        }
        when (e.action) {
            Action.LEFT_CLICK_AIR -> basicAttack(e)
            Action.RIGHT_CLICK_BLOCK -> skill(e)
            else -> return
        }
    }

    private fun basicAttack(e: PlayerInteractEvent) {
        val player = e.player
        val world = player.world
        if (player.attackCooldown != 1.0f) {
            return
        }
        world.playSound(player.location, Sound.AMBIENT_UNDERWATER_EXIT, SoundCategory.NEUTRAL, 1f, 1.5f)
        val projectile = world.spawnEntity(player.location.add(0.0, 1.5, 0.0) , EntityType.FIREWORK) as Firework
        projectile.setGravity(false)
        projectile.setItem(ItemStack(Material.HEART_OF_THE_SEA))
        projectile.isShotAtAngle = true
        projectile.isSilent = true
        projectile.velocity = player.location.direction.multiply(2.5)
        KokomiBasicTask(projectile, player).runTaskTimer(GenshinCraft.instance, 0, 1)
        e.isCancelled = true
    }

    private fun skill(e: PlayerInteractEvent) {
        val item = e.item
        val player = e.player
        val pos = e.interactionPoint
        val world = player.world
        if (item == null || pos == null || e.player.hasCooldown(item.type)) {
            return
        }
        val skillEntity = world.spawnEntity(Location(world, floor(pos.x) + 0.5, floor(pos.y) - 0.3, floor(pos.z) + 0.5, 0f, 0f) , EntityType.ARMOR_STAND) as ArmorStand
        skillEntity.isInvulnerable = true
        skillEntity.setAI(false)
        skillEntity.setGravity(false)
        skillEntity.isInvisible = true
        val squid = world.spawnEntity(pos , EntityType.GLOW_SQUID)
        squid.isInvulnerable = true
        PersistentDataController.setData(squid, DataKey.STATIC, true)
        PersistentDataController.setData(skillEntity, DataKey.STATIC, true)
        skillEntity.addPassenger(squid)
        KokomiSkillTask(skillEntity, 6, player).runTaskLater(GenshinCraft.instance, 0)
        player.setCooldown(item.type, coolDown)
        e.isCancelled = true
    }
}