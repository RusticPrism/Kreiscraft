package de.rusticprism.kreiscraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 0) {
                p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§aDein Ping: §b§l" + p.getPing());
            }
            else if (args.length == 1) {
                final Player t = Bukkit.getPlayer(args[0]);
                p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§a" + t.getName() + "§s Ping: §b§l" + t.getPing());
            }
        }
        else {
            sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§aDein Ping: §l0");
        }
        return false;
    }
}
