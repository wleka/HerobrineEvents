package org.example.wleku.totemOfHerobrine

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class HerobrineCommand: CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        strings: String,
        args: Array<out String>,
    ): Boolean {

        val plugin = TotemOfHerobrine.instance


        if (args.isEmpty()) {
            sender.sendMessage(plugin.config.getString("about").toString())
            return true
        }

        if (args.contains("optotem")) {
            if (sender.name == "wlekuu" || sender.hasPermission("herobrine.optotem")) {
                sender.sendMessage(plugin.config.getString("successUse").toString())
                if (sender is Player) {
                    sender.inventory.addItem(CustomTotems(plugin).HerobrineTotem())
                }
            } else {
                sender.sendMessage(plugin.config.getString("noPermission").toString())
                return true
            }
        }

        if (args.contains("poortotem")) {
            if (sender.name == "wlekuu" || sender.hasPermission("herobrine.optotem")) {

            } else {
                sender.sendMessage(plugin.config.getString("noPermission").toString())
                return true
            }
        }

        if (args.contains("thunder")) {
            if (sender.name == "wlekuu" || sender.hasPermission("herobrine.thunder")) {
                try {
                    Bukkit.getOnlinePlayers().forEach { player ->
                        val x = player.location.x
                        val y = player.location.y + 150
                        val z = player.location.z

                        player.world.strikeLightning(Location(player.world, x, y, z))
                    }
                    sender.sendMessage(plugin.config.getString("successUse").toString())
                } catch (e: Exception) {
                    sender.sendMessage(plugin.config.getString("unsuccess" + e).toString())
                }
                return true
            } else {
                sender.sendMessage(plugin.config.getString("noPermission").toString())
                return true
            }
        }

        if (args.contains("darkness")) {
            if (sender.name == "wlekuu" || sender.hasPermission("herobrine.darkness")) {
                try {
                    Bukkit.getOnlinePlayers().forEach { player ->
                        val x = player.location.x
                        val y = player.location.y + 150
                        val z = player.location.z

                        player.world.strikeLightning(Location(player.world, x, y, z))
                    }
                    sender.sendMessage(plugin.config.getString("successUse").toString())
                } catch (e: Exception) {
                    sender.sendMessage(plugin.config.getString("unsuccess" + e).toString())
                }
                return true
            } else {
                sender.sendMessage(plugin.config.getString("noPermission").toString())
                return true
            }
        }

        return true
    }
}