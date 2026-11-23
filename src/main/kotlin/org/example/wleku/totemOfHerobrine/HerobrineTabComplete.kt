package org.example.wleku.totemOfHerobrine

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class HerobrineTabComplete: TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): List<String> {
        if (args.size == 1) {
            return listOf(
                "thunder",
                "darkness"
            ).filter { it.startsWith(args[0], ignoreCase = false) }
        }
        return emptyList()
    }
}