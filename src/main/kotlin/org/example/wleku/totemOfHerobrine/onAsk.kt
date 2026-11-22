package org.example.wleku.totemOfHerobrine

import io.papermc.paper.event.player.AsyncChatEvent
import org.bukkit.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.Random

class onAsk(private val plugin: TotemOfHerobrine): Listener {

    val varianceHerobrine = arrayListOf<String>(
        "херобрин,",
        "херобрин",
        "Херобрин,",
        "Херобрин",
        "ХЕРОБРИН",
        "ХЕРОБРИН,",
        "herobrine,",
        "herobrine",
        "Herobrine,",
        "Herobrine",
        "HEROBRINE,",
        "HEROBRINE"
    )

    @EventHandler
    fun onAskHerobrine(event: AsyncChatEvent) {
        val player = event.player
        val message = PlainTextComponentSerializer.plainText().serialize(event.message())

        for (variance in varianceHerobrine) {
            if (message.startsWith(variance + " ", ignoreCase = true)) {
                val randomMsg = Random().nextInt(0, 4)
                val args = message.removePrefix(variance).trim()

                if (args.length > 1) {
                    when (randomMsg) {
                        0 -> onSendAction(player, Component.text("Да.", NamedTextColor.WHITE), false)
                        1 -> onSendAction(player, Component.text("Нет.", NamedTextColor.WHITE), false)
                        2 -> onSendAction(player, Component.text("Все может быть.", NamedTextColor.WHITE), false)
                        else -> onSendAction(player, Component.text("Не знаю.", NamedTextColor.WHITE), false)
                    }
                }
                break;
            } else if (message.startsWith(variance, ignoreCase = true)) {
                Bukkit.getScheduler().runTaskLater(plugin, Runnable {
                    player.addPotionEffect(
                        PotionEffect(PotionEffectType.DARKNESS, 500, 3)
                    )

                    player.playSound(player.location, Sound.AMBIENT_CAVE, 1f, 1f)

                    onSendAction(player, Component.text("НЕ ТРЕВОЖИТЬ МЕНЯ", NamedTextColor.RED), true)
                }, 5L)
                break;
            }
        }
    }

    fun onSendAction(player: Player, string: Component, ignoreHMM : Boolean = true) {
        if (ignoreHMM == false) {
            player.sendActionBar("Хм... дай подумаю...")
            Bukkit.getScheduler().runTaskLater(plugin, Runnable {
                player.sendActionBar(string)
            }, 67L)
        } else {
            player.sendActionBar(string)
        }
    }
}