package com.github.raysato.genshincraft.charactars.fischl

import com.github.raysato.genshincraft.GenshinCraft
import com.github.raysato.genshincraft.charactars.Skill
import com.github.raysato.genshincraft.charactars.kokomi.KokomiBasicTask
import com.github.raysato.genshincraft.charactars.kokomi.KokomiSkillTask
import com.github.raysato.genshincraft.utils.Lang
import org.bukkit.*
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework
import org.bukkit.entity.Phantom
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import kotlin.math.floor

class FischlSkill: Skill() {
    override val name = Lang.CHARA_KOKOMI_SKILL
    override val desc = Lang.CHARA_KOKOMI_SKILL
    override val coolDown = 120
    override val energyRequired = 0.0

    override fun click(e: PlayerInteractEvent) {
        val item = e.item
        if (item !is ItemStack || item.type !== Material.BOW) {
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
    }

    private fun skill(e: PlayerInteractEvent) {
        val item = e.item
        val player = e.player
        val pos = e.interactionPoint
        val world = player.world
        if (item == null || pos == null || e.player.hasCooldown(item.type)) {
            return
        }
        val skillEntity = world.spawnEntity(Location(world, pos.x, pos.y + 1.2, pos.z, 0f, 0f) , EntityType.PHANTOM) as Phantom
        skillEntity.isInvulnerable = true
        skillEntity.setAI(false)
        skillEntity.setGravity(false)
        FischlSkillTask(skillEntity, 6, player).runTaskLater(GenshinCraft.instance, 0)
        player.setCooldown(item.type, coolDown)
        e.isCancelled = true
    }
}