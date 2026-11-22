package org.example.wleku.totemOfHerobrine

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import org.w3c.dom.events.EventListener

class TotemOfHerobrine : JavaPlugin() {


    companion object{
        lateinit var instance: TotemOfHerobrine
            private set
    }

    override fun onEnable() {
        // Plugin startup logic
        instance = this
        Bukkit.getPluginManager().registerEvents(onAsk(this), this)

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
