package com.github.raysato.genshincraft.charactars.ganyu

import com.github.raysato.genshincraft.utils.DataKey
import com.github.raysato.genshincraft.utils.PersistentDataController
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable


class GanyuChargeCheckTask(private val player: Player): BukkitRunnable() {
    override fun run() {
        PersistentDataController.setData(player, DataKey.BOW_TASK_ID, -1)
        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 2f, 1f)
    }
}