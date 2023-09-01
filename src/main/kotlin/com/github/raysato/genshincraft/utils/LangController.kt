package com.github.raysato.genshincraft.utils

import com.github.raysato.genshincraft.GenshinCraft
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import java.lang.Exception

class LangController(_player: Player) {
    val EN = 0
    val JP = 1
    private val player = _player
    val language = getPlayerLang()

    fun getPlayerLang(): Int {
        return player.persistentDataContainer.get(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER) ?: 0
    }

    fun setPlayerLang(int: Int) {
        player.persistentDataContainer.set(NamespacedKey(GenshinCraft.instance, "genshinChar"), PersistentDataType.INTEGER, int)
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
}