package de.rusticprism.kreiscraft.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class WorldCommand implements CommandExecutor, TabCompleter
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if(p.hasPermission("kreiscraft.command.world")) {
                if (args.length == 1) {
                    final World world = Bukkit.getWorld(args[0]);
                    p.teleport(world.getSpawnLocation());
                } else {
                    p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Bitte benutze §6/world <Welt>§4!");
                }
            }else p.sendMessage(Kreiscraft.noperms);
        }
        else {
            sender.sendMessage(Kreiscraft.nocons);
        }
        return false;
    }
    
    public List<String> onTabComplete(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final ArrayList<String> worlds = new ArrayList<String>();
        if (args.length == 0) {
            return worlds;
        }
        if (args.length == 1) {
            for (int i = 0; i < Bukkit.getWorlds().size(); i++) {
                worlds.add(Bukkit.getWorlds().get(i).getName());
            }
            final ArrayList<String> list2 = new ArrayList<String>();
            final String currentare = args[args.length - 1];
            for (final String s : worlds) {
                final String s2 = s.toLowerCase();
                if (s2.startsWith(currentare)) {
                    list2.add(s);
                }
            }
            return list2;
        }
        return null;
    }
}
