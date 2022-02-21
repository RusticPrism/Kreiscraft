package de.rusticprism.kreiscraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SudoCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender.hasPermission("kreiscraft.command.sudo")) {
            if (args.length >= 1) {
                final Player tar = Bukkit.getPlayer(args[0]);
                String spigot = "";
                if (tar != null) {
                    for (int i = 1; i < args.length; ++i) {
                        spigot = String.valueOf(spigot) + args[i] + " ";
                    }
                    tar.chat(spigot);
                }
                else {
                    sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Diese Spieler ist nicht online!");
                }
            }
            else {
                sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Bitte benutze §6/sudo <Spieler> <Command>§4!");
            }
        }
        else {
            sender.sendMessage(Kreiscraft.noperms);
        }
        return false;
    }
}
