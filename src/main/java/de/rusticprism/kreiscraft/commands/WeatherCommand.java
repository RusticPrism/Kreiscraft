package de.rusticprism.kreiscraft.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor, TabCompleter
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("kreiscraft.command.weather")) {
                if (args.length == 1) {
                    final Player p = (Player)sender;
                    switch (args[0].toLowerCase()) {
                        case "thunder": {
                            final World world = p.getWorld();
                            world.setStorm(true);
                            world.setThundering(true);
                            p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§aWeather in §b" + p.getWorld().getName() + "§a has been set to thunder!");
                            return false;
                        }
                        case "rain": {
                            final World world = p.getWorld();
                            world.setStorm(true);
                            world.setThundering(false);
                            p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§aWeather in §b" + p.getWorld().getName() + "§a has been set to rain!");
                            return false;
                        }
                        case "clear": {
                            final World world = p.getWorld();
                            world.setStorm(false);
                            world.setThundering(false);
                            p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§aWeather in §b" + p.getWorld().getName() + "§a has been set to clear!");
                            return false;
                        }
                        default:
                            break;
                    }
                    sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Bitte benutze §6/weather clear, rain, thunder");
                }
                else {
                    sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Bitte benutze §6/weather clear, rain, thunder");
                }
            }
            else {
                sender.sendMessage(Kreiscraft.noperms);
            }
        }
        else {
            sender.sendMessage(Kreiscraft.nocons);
        }
        return false;
    }
    
    public List<String> onTabComplete(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final ArrayList<String> list = new ArrayList<String>();
        if (args.length == 0) {
            return null;
        }
        if (args.length == 1) {
            list.add("clear");
            list.add("rain");
            list.add("thunder");
        }
        final ArrayList<String> list2 = new ArrayList<String>();
        final String currentare = args[args.length - 1].toLowerCase();
        for (final String s : list) {
            final String s2 = s.toLowerCase();
            if (s2.startsWith(currentare)) {
                list2.add(s);
            }
        }
        return list2;
    }
}
