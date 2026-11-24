package org.example.wleku.totemOfHerobrine

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

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
                    val target = Bukkit.getPlayer(args[1])

                    if (target != null) {
                        target.sendMessage(Component.text("Herobrine's watching you."))
                        target.playSound(target.location, Sound.ENTITY_WARDEN_HEARTBEAT, 1f, 1f)


                        Bukkit.getScheduler().runTaskLater(plugin, Runnable {

                            target.showTitle(Title.title(
                                Component.text("I SEE YOU.", NamedTextColor.RED),
                                Component.text("")
                            ))

                            target.addPotionEffect(PotionEffect(PotionEffectType.DARKNESS, 167, 3, true, false))
                            target.addPotionEffect(PotionEffect(PotionEffectType.SLOWNESS, 167, 5, true, false))
                            target.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 167, 1, true, false))

                            target.playSound(target.location, Sound.AMBIENT_CAVE, 1f, 1f)
                        }, 67L)

                        sender.sendMessage(plugin.config.getString("successUse").toString() + " : ${target.getName()}")
                    } else {
                        sender.sendMessage("Player's not online.")
                    }
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