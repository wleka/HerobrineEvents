package org.example.wleku.totemOfHerobrine

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class TotemOfHerobrine : JavaPlugin() {


    companion object{
        lateinit var instance: TotemOfHerobrine
            private set
        lateinit var customTotems: CustomTotems
    }

    override fun onEnable() {
        // Plugin startup logic
        instance = this
        customTotems = CustomTotems(this)
        Bukkit.getPluginManager().registerEvents(onAsk(this), this)

        getCommand("herobrine")?.apply {
            setExecutor(HerobrineCommand())
            tabCompleter = HerobrineTabComplete()
        }

        Bukkit.getScheduler().runTaskTimer(this, Runnable {
            for (player in Bukkit.getOnlinePlayers()) {
                val mainHand = player.inventory.itemInMainHand
                val offHand = player.inventory.itemInOffHand

                if (customTotems.isCustomTotem(mainHand) || customTotems.isCustomTotem(offHand)) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SLOWNESS, 40, 1, true, false))
                    player.getAttribute(Attribute.MAX_HEALTH)?.baseValue = 40.0
                    player.getAttribute(Attribute.SCALE)?.baseValue = 0.8
                } else {
                    player.getAttribute(Attribute.SCALE)?.baseValue = 1.0
                    player.getAttribute(Attribute.MAX_HEALTH)?.baseValue = 20.0
                }
            }
        }, 0L, 20L)

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
