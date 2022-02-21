package de.rusticprism.kreiscraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UUID_Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(Kreiscraft.prefix + "§aDeine UUID: §b" + ((Player) sender).getUniqueId());
            } else if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Kreiscraft.prefix + "§cDies ist kein Spieler!");
                } else
                    sender.sendMessage(Kreiscraft.prefix + "§a" + Bukkit.getPlayer(args[0]).getName() + "´s UUID: §b" + Bukkit.getPlayer(args[0]).getUniqueId());
            }else sender.sendMessage(Kreiscraft.prefix + "§cUsage: /uuid <player>!");
            } else if (args.length == 0) {
                sender.sendMessage(Kreiscraft.prefix + "§aDeine UUID: §b00000000000000000000000000000000000");
            } else if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Kreiscraft.prefix + "§cDies ist kein Spieler!");
                } else
                    sender.sendMessage(Kreiscraft.prefix + "§a" + Bukkit.getPlayer(args[0]).getName() + "´s UUID: §b" + Bukkit.getPlayer(args[0]).getUniqueId());
            }else sender.sendMessage(Kreiscraft.prefix + "§cUsage: /uuid <player>!");
            return false;
    }
}
