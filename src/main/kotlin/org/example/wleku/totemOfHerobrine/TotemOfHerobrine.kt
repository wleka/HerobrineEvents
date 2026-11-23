package org.example.wleku.totemOfHerobrine

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TotemOfHerobrine : JavaPlugin() {


    companion object{
        lateinit var instance: TotemOfHerobrine
            private set
    }

    override fun onEnable() {
        // Plugin startup logic
        instance = this
        Bukkit.getPluginManager().registerEvents(onAsk(this), this)

        getCommand("totem")!!.setExecutor(HerobrineCommand())

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
