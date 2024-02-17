package com.github.raysato.genshincraft.utils

import org.bukkit.persistence.PersistentDataType
import kotlin.reflect.typeOf

sealed class DataKey<T: Any>(val keyName: String, val dataType: PersistentDataType<*, T>, val defaultValue: T) {
    data object GUI_TYPE: DataKey<Int> (
        "genshinGUI",
        PersistentDataType.INTEGER,
        0,
    )
    data object CHARACTER_TYPE: DataKey<Int> (
        "genshinChar",
        PersistentDataType.INTEGER,
        0,
    )
    data object STATIC: DataKey<Boolean> (
        "static",
        PersistentDataType.BOOLEAN,
        false,
    )
    data object BOW_CHARGED_FOR: DataKey<Int> (
        "genshinBowCharged",
        PersistentDataType.INTEGER,
        0,
    )
    data object BOW_IS_CHARGED: DataKey<Boolean> (
        "genshinCanShootCharged",
        PersistentDataType.BOOLEAN,
        false,
    )
    data object GUI_ACTION_TYPE: DataKey<Int> (
        "genshinGUIAction",
        PersistentDataType.INTEGER,
        0,
    )
    data object LOCALE_TYPE: DataKey<Int> (
        "rLocale",
        PersistentDataType.INTEGER,
        0,
    )
}