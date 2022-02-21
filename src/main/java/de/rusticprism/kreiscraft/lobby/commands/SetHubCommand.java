package de.rusticprism.kreiscraft.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.rusticprism.kreiscraft.commands.Kreiscraft;

public class SetHubCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("kreiscraft.lobby.command.sethub")) {
                if (args.length == 0) {
                    final FileConfiguration config = Kreiscraft.getPlugin().getConfig();
                    config.set("Hub.World", (Object)p.getWorld().getName());
                    config.set("Hub.X", (Object)p.getLocation().getX());
                    config.set("Hub.Z", (Object)p.getLocation().getZ());
                    config.set("Hub.Y", (Object)p.getLocation().getY());
                    config.set("Hub.Yaw", (Object)p.getLocation().getYaw());
                    config.set("Hub.Pitch", (Object)p.getLocation().getPitch());
                    Kreiscraft.getPlugin().saveConfig();
                    p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Der Spawn wurde bei dir gesetzt, benutze §6/hub , /l, /lobby §4um dort hinzukommen");
                }
                else {
                    p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§9Bitte benutze nur §6/sethub");
                }
            }
            else {
                p.sendMessage(String.valueOf(Kreiscraft.prefix) + Kreiscraft.noperms);
            }
        }
        else {
            sender.sendMessage(String.valueOf(Kreiscraft.prefix) + Kreiscraft.nocons);
        }
        return false;
    }
}
