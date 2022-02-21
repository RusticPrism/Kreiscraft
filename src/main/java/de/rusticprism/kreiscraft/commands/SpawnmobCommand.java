package de.rusticprism.kreiscraft.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnmobCommand implements CommandExecutor, TabCompleter
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String lable, final String[] args) {
        if (sender.hasPermission("kreiscraft.command.spawnmob")) {
            if (sender instanceof Player) {
                final Player p = (Player)sender;
                if (args.length == 2) {
                    final String entitystring = args[0];
                    final String countString = args[1];
                    try {
                        EntityType.valueOf(entitystring.toUpperCase());
                    }
                    catch (IllegalArgumentException ex) {
                        p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Ich kenne diesen Mob Nicht");
                        return false;
                    }
                    final EntityType type = EntityType.valueOf(entitystring.toUpperCase());
                    if (type != null) {
                        if (type.isSpawnable()) {
                            int count = 1;
                            try {
                                count = Integer.parseInt(countString);
                            }
                            catch (NumberFormatException e) {
                                sender.sendMessage(String.valueOf(Kreiscraft.prefix) + " §4Dies ist keine Anzahl!");
                                return false;
                            }
                            for (int i = 0; i < count; ++i) {
                                ((Player)sender).getLocation().getWorld().spawnEntity(((Player)sender).getLocation(), type);
                            }
                            sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§1Es wurden " + countString + " " + entitystring + " gespawnt!");
                        }
                        else {
                            sender.sendMessage(String.valueOf(Kreiscraft.prefix) + " §4Diesen Mob kann man nicht spawnen!");
                        }
                    }
                    else {
                        sender.sendMessage(String.valueOf(Kreiscraft.prefix) + " §4Ich kenne diesen Mob nicht");
                    }
                }
                else if (args.length == 1) {
                    final String entitystring = args[0];
                    try {
                        EntityType.valueOf(entitystring.toUpperCase());
                    }
                    catch (IllegalArgumentException ex2) {
                        p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Ich kenne diesen Mob Nicht");
                        return false;
                    }
                    final EntityType type2 = EntityType.valueOf(entitystring.toUpperCase());
                    if (type2 != null) {
                        if (type2.isSpawnable()) {
                            ((Player)sender).getLocation().getWorld().spawnEntity(((Player)sender).getLocation(), type2);
                            sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§1Es wurde 1 " + entitystring + " gespawnt!");
                        }
                        else {
                            sender.sendMessage(String.valueOf(Kreiscraft.prefix) + " §4Diesen Mob kann man nicht spawnen!");
                        }
                    }
                    else {
                        sender.sendMessage(String.valueOf(Kreiscraft.prefix) + " §4Ich kenne diesen Mob nicht");
                    }
                }
                else {
                    sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Bitte benutze §6/spawnmob <Monster> <Anzahl>§4!");
                }
            }
            else {
                sender.sendMessage(Kreiscraft.nocons);
            }
        }
        else {
            sender.sendMessage(Kreiscraft.noperms);
        }
        return true;
    }
    
    public ArrayList<String> onTabComplete(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final ArrayList<String> list = new ArrayList<String>();
        if (args.length == 0) {
            return list;
        }
        if (args.length == 1) {
            EntityType[] values;
            for (int length = (values = EntityType.values()).length, j = 0; j < length; ++j) {
                final EntityType value = values[j];
                list.add(value.toString().toLowerCase());
            }
        }
        else if (args.length == 2) {
            for (int i = 0; i < 100; ++i) {
                list.add(String.valueOf(i));
            }
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
