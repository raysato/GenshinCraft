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
        return PersistentDataController.getData(player, DataKey.LOCALE_TYPE) ?: DataKey.LOCALE_TYPE.defaultValue
    }

    fun setPlayerLang(int: Int): HumanEntity {
        PersistentDataController.setData(player, DataKey.LOCALE_TYPE, int)
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