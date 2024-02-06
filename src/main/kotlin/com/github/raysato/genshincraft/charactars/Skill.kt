package com.github.raysato.genshincraft.charactars

import com.github.raysato.genshincraft.utils.Lang
import org.bukkit.event.player.PlayerInteractEvent

abstract class Skill {
    abstract val name: Lang
    abstract val desc: Lang
    abstract val coolDown: Int
    abstract val energyRequired: Double

    open fun click(e: PlayerInteractEvent) {

    }
}