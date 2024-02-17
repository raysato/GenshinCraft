package com.github.raysato.genshincraft.utils

import com.github.raysato.genshincraft.GenshinCraft
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.inventory.meta.ItemMeta

object PersistentDataController {
    fun <T: Any> getData(entity: Entity, key: DataKey<T>): T? {
        if (entity.persistentDataContainer.has(NamespacedKey(GenshinCraft.instance, key.keyName), key.dataType)) {
            return entity.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, key.keyName), key.dataType) ?: key.defaultValue
        }
        return null
    }

    fun <T: Any> getData(meta: ItemMeta, key: DataKey<T>): T? {
        if (meta.persistentDataContainer.has(NamespacedKey(GenshinCraft.instance, key.keyName), key.dataType)) {
            return meta.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, key.keyName), key.dataType) ?: key.defaultValue
        }
        return null
    }

    fun <T: Any> setData(entity: Entity, key: DataKey<T>, value: T) {
        entity.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, key.keyName), key.dataType, value)
    }

    fun <T: Any> setData(meta: ItemMeta, key: DataKey<T>, value: T) {
        meta.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, key.keyName), key.dataType, value)
    }
}