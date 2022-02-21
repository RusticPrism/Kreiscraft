package de.rusticprism.kreiscraft.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCommand implements CommandExecutor, TabCompleter
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String lable, final String[] args) {
        if (sender.hasPermission("kreiscraft.command.item")) {
            if (sender instanceof Player) {
                final Player p = (Player)sender;
                if (args.length == 2) {
                    final String itemstring = args[0];
                    final String countString = args[1];
                    final ItemStack istack = new ItemStack(Material.DIRT);
                    try {
                        istack.setType(Material.valueOf(itemstring.toUpperCase()));
                    }
                    catch (IllegalArgumentException ex) {
                        p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Ich kenne dieses Item Nicht");
                        return false;
                    }
                    final ItemMeta imeta = istack.getItemMeta();
                    int count = 1;
                    try {
                        count = Integer.parseInt(countString);
                    }
                    catch (NumberFormatException e) {
                        sender.sendMessage(String.valueOf(Kreiscraft.prefix) + " §4Dies ist keine Anzahl!");
                        return false;
                    }
                    if (count >= 65) {
                        p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Bitte gib nicht mehr als 64 Items an!");
                    }
                    else {
                        for (int i = 0; i < count; ++i) {
                            p.getInventory().addItem(new ItemStack[] { istack });
                        }
                    }
                    sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§1Hier " + countString + " neue " + imeta.getDisplayName() + " geh\u00f6ren nun dir.");
                }
                else if (args.length == 1) {
                    final String itemstring = args[0];
                    final ItemStack istack2 = new ItemStack(Material.DIRT);
                    try {
                        istack2.setType(Material.valueOf(itemstring.toUpperCase()));
                    }
                    catch (IllegalArgumentException ex2) {
                        p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Ich kenne dieses Item Nicht");
                        return false;
                    }
                    final ItemMeta imeta2 = istack2.getItemMeta();
                    p.getInventory().addItem(new ItemStack[] { istack2 });
                    sender.sendMessage(String.valueOf(Kreiscraft.prefix) + "§1Hier 1 neues " + imeta2.getDisplayName() + " geh\u00f6rt nun dir.");
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
            Material[] values;
            for (int length = (values = Material.values()).length, j = 0; j < length; ++j) {
                final Material value = values[j];
                list.add(value.toString().toLowerCase());
            }
        }
        else if (args.length == 2) {
            for (int i = 0; i < 10; ++i) {
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
