package com.github.raysato.genshincraft.guis

import com.github.raysato.genshincraft.charactars.CharactarIDs
import com.github.raysato.genshincraft.charactars.Kokomi
import de.tr7zw.nbtapi.NBT
import de.tr7zw.nbtapi.iface.ReadWriteNBT
import de.tr7zw.nbtapi.iface.ReadableNBT
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

class CharacterGUI(): GUI() {
    override val title = Component.text("Character")
    override val items = mapOf(
        Pair(
            CharactarIDs.KOKOMI.id, GUIItem(
            Kokomi().getSkull(),
            Component.text("Kokomi"),
            listOf(Component.text("lore")),
            fun(e: InventoryClickEvent) { NBT.modify(e.whoClicked) { nbt: ReadWriteNBT -> nbt.setInteger("genshinChar", GUITypes.CHARACTER.id) }},
        ))
    )
    constructor(player: Player): this() {
        if (NBT.get(player) { nbt: ReadableNBT -> nbt.hasTag("genshinChar") }) {
            items[NBT.get(player) { nbt: ReadableNBT -> nbt.getInteger("genshinChar") }]?.doEnchant = true
        }
    }
}