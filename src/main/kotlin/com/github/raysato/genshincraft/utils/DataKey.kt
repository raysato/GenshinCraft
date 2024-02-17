package com.github.raysato.genshincraft.utils

import org.bukkit.persistence.PersistentDataType

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
    data object BOW_TASK_ID: DataKey<Int> (
        "genshinCanShootCharged",
        PersistentDataType.INTEGER,
        0,
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