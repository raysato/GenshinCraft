package com.github.raysato.genshincraft.charactars

import com.github.raysato.genshincraft.utils.Lang

enum class Charactar(val id: Int, val textureValue: String, val displayName: Lang, val lore: Lang) {
    KOKOMI(
        1,
        "ewogICJ0aW1lc3RhbXAiIDogMTY0ODM4MDk0NTQ0MSwKICAicHJvZmlsZUlkIiA6ICJlM2I0NDVjODQ3ZjU0OGZiOGM4ZmEzZjFmN2VmYmE4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaW5pRGlnZ2VyVGVzdCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82M2U2YWYwYmVlNjFiY2ZmNjAzMDNkZDAyY2YzZTczYWFkN2UwZjI5NTVjZWQxMzkxOTU4OGNiMTYxNDc0ZDJkIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=",
        Lang.CHARA_KOKOMI_NAME,
        Lang.ITEM_KOKOMI_LORE,
        ),
    FISCHL(
        2,
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTU2MGYzMmU0N2E5ODlhMDA3YzhjMTZiMmExYzQyNDkxYmQyOTkyNmNkYmUxYzdhYTNhNDVlMDdjM2Y0ODJiYSJ9fX0=",
        Lang.CHARA_FISCHL_NAME,
        Lang.ITEM_FISCHL_LORE,
    ),
    GANYU(
        3,
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY0M2ZhZTk1ZDA1YjgwODZkODdkMWYwNDRhZTZiNmRjYmY0MmZkZGY5OGZiZDZmY2EzYTRlNWJmMGE0NDMwOCJ9fX0=",
        Lang.CHARA_FISCHL_NAME,
        Lang.ITEM_FISCHL_LORE,
    ),
}