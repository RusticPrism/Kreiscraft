package de.rusticprism.kreiscraft.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldSpawnCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("kreiscraft.command.worldspawn")) {
                if (args.length == 0) {
                    final World world = p.getWorld();
                    world.setSpawnLocation(p.getLocation());
                    p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§bDer WorldSpawn der Welt §a" + world.getName() + " §bwurde bei dir Gesetzt!");
                }
            }
            else {
                p.sendMessage(Kreiscraft.noperms);
            }
        }
        else {
            sender.sendMessage(Kreiscraft.nocons);
        }
        return false;
    }
}
