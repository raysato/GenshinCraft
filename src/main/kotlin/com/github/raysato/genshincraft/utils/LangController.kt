package com.github.raysato.genshincraft.utils

import com.github.raysato.genshincraft.GenshinCraft
import net.kyori.adventure.text.Component
import org.bukkit.NamespacedKey
import org.bukkit.entity.HumanEntity
import org.bukkit.persistence.PersistentDataType

class LangController(var player: HumanEntity) {
    val EN = 0
    val JP = 1
    var language = getPlayerLang()

    fun getPlayerLang(): Int {
        return player.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "rLocale"), PersistentDataType.INTEGER) ?: 0
    }

    fun setPlayerLang(int: Int): HumanEntity {
        player.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "rLocale"), PersistentDataType.INTEGER, int)
        language = int
        return player
    }

    fun togglePlayerLang() {
        if (language == EN) {
            setPlayerLang(JP)
            return
        }
        setPlayerLang(EN)
    }

    fun getText(lang: Lang): String {
        if (language == EN) {
            return lang.en
        }
        return lang.ja
    }

    fun getComponent(lang: Lang): Component {
        return Component.text().content(getText(lang)).build()
    }
}