package com.github.raysato.genshincraft.enchants

import com.github.raysato.genshincraft.GenshinCraft
import io.papermc.paper.enchantments.EnchantmentRarity
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget
import org.bukkit.entity.EntityCategory
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import java.util.Arrays
import java.util.HashSet

class Glitter: Enchantment(NamespacedKey(GenshinCraft.instance,"Glitter")) {
    override fun translationKey(): String {
        return ""
    }

    override fun getName(): String {
        return "Glitter"
    }

    override fun getMaxLevel(): Int {
        return 0
    }

    override fun getStartLevel(): Int {
        return 1
    }

    override fun getItemTarget(): EnchantmentTarget {
        return EnchantmentTarget.ARMOR
    }

    override fun isTreasure(): Boolean {
        return false
    }

    override fun isCursed(): Boolean {
        return false
    }

    override fun conflictsWith(p0: Enchantment): Boolean {
        return false
    }

    override fun canEnchantItem(p0: ItemStack): Boolean {
        return false
    }

    override fun displayName(p0: Int): Component {
        return Component.text { name }
    }

    override fun isTradeable(): Boolean {
        return false
    }

    override fun isDiscoverable(): Boolean {
        return false
    }

    override fun getRarity(): EnchantmentRarity {
        return EnchantmentRarity.COMMON
    }

    override fun getDamageIncrease(p0: Int, p1: EntityCategory): Float {
        return 0.toFloat()
    }

    override fun getActiveSlots(): MutableSet<EquipmentSlot> {
        return HashSet<EquipmentSlot>(listOf(EquipmentSlot.CHEST))
    }
}