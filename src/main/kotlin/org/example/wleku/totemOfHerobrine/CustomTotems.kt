package org.example.wleku.totemOfHerobrine

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class CustomTotems(private val plugin: TotemOfHerobrine) {

    val totemKey = NamespacedKey(plugin, "herobrine_totem")

    fun HerobrineTotem(): ItemStack {
        val item = ItemStack(Material.TOTEM_OF_UNDYING)
        val meta = item.itemMeta!!

        meta.setDisplayName("Тотем херобрина")
        meta.lore = listOf(
            "Про этот тотем никто не знает..."
        )

        meta.persistentDataContainer.set(totemKey, PersistentDataType.BYTE, 1.toByte())
        item.itemMeta = meta

        return item
    }

    fun isCustomTotem(item: ItemStack?): Boolean {
        if (item == null || item.type != Material.TOTEM_OF_UNDYING) return false
        val meta = item.itemMeta ?: return false
        return meta.persistentDataContainer.has(totemKey, PersistentDataType.BYTE)
    }
}