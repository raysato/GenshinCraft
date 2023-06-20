package com.github.raysato.genshincraft.charactars

import com.destroystokyo.paper.profile.ProfileProperty
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*


abstract class Charactar {
    abstract val textureValue: String
    abstract val textureSignature: String
    fun getSkull(): ItemStack {
        val skull = ItemStack(Material.PLAYER_HEAD)
        val skullMeta = skull.itemMeta as SkullMeta
        val profile =  Bukkit.createProfile(UUID.randomUUID(), null);
        profile.setProperty(ProfileProperty("textures", textureValue, textureSignature))
        skullMeta.playerProfile = profile
        skull.itemMeta = skullMeta
        return skull;
    }
}