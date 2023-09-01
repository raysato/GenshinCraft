package com.github.raysato.genshincraft.enchants

import org.bukkit.enchantments.Enchantment
import java.lang.reflect.Field
import java.util.*

object EnchantRegister {
    val GLITTER = Glitter()
    fun register() {
        val registered = Enchantment.values().contains(GLITTER)
        if (!registered) {
            registerEnchant(GLITTER);
        }
    }

    private fun registerEnchant(enchantment: Enchantment) {
        try {
            val f: Field = Enchantment::class.java.getDeclaredField("acceptingNew")
            f.isAccessible = true
            f.set(null, true)
            Enchantment.registerEnchantment(enchantment)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}