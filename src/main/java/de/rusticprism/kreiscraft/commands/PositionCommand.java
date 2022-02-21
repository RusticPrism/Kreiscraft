package de.rusticprism.kreiscraft.commands;

import org.bukkit.event.EventHandler;
import org.bukkit.World;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import java.util.ArrayList;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.IOException;
import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandExecutor;

public class PositionCommand implements CommandExecutor, TabCompleter, Listener{
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 45, "§1§lDeine Positionen");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            final File Positionf = new File(Kreiscraft.getInstance().getDataFolder().getPath(), String.valueOf(p.getName()) + "Positions.yml");
            if (!Positionf.exists()) {
                try {
                    Positionf.createNewFile();
                }
                catch (IOException ext) {
                    ext.printStackTrace();
                }
            }
            final FileConfiguration Positioncfg = (FileConfiguration)YamlConfiguration.loadConfiguration(Positionf);
            final List<String> positions = (List<String>)Positioncfg.getStringList("PositionsList");
            if (args.length == 0) {
                if (!Positioncfg.getStringList("PositionsList").isEmpty()) {
                    for (int i = 0; i < Positioncfg.getStringList("PositionsList").size(); ++i) {
                        if (Positioncfg.get("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".World").equals("Survival")) {
                            final ArrayList<String> cords = new ArrayList<String>();
                            cords.add("§1X: " + Positioncfg.getInt("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".X"));
                            cords.add("§1Y: " + Positioncfg.getInt("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".Y"));
                            cords.add("§1Z: " + Positioncfg.getInt("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".Z"));
                            final ItemStack istack = new ItemStack(Material.GRASS_BLOCK);
                            final ItemMeta Meta = istack.getItemMeta();
                            Meta.setLore(cords);
                            Meta.setDisplayName("§1" + Positioncfg.getStringList("PositionsList").get(i));
                            istack.setItemMeta(Meta);
                            inv.setItem(i, istack);
                        }
                        else if (Positioncfg.get("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".World").equals("Survival_nether")) {
                            final ItemStack istack2 = new ItemStack(Material.NETHERRACK);
                            final ItemMeta Meta2 = istack2.getItemMeta();
                            final ArrayList<String> cords2 = new ArrayList<String>();
                            cords2.add("§1X: " + Positioncfg.getInt("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".X"));
                            cords2.add("§1Y: " + Positioncfg.getInt("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".Y"));
                            cords2.add("§1Z: " + Positioncfg.getInt("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".Z"));
                            Meta2.setDisplayName("§1" + Positioncfg.getStringList("PositionsList").get(i));
                            Meta2.setLore(cords2);
                            istack2.setItemMeta(Meta2);
                            inv.setItem(i, istack2);
                        }
                        else if (Positioncfg.get("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".World").equals("Survival_the_end")) {
                            final ItemStack istack2 = new ItemStack(Material.END_STONE);
                            final ItemMeta Meta2 = istack2.getItemMeta();
                            final ArrayList<String> cords2 = new ArrayList<String>();
                            cords2.add("§1X: " + Positioncfg.getInt("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".X"));
                            cords2.add("§1Y: " + Positioncfg.getInt("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".Y"));
                            cords2.add("§1Z: " + Positioncfg.getInt("Position." + Positioncfg.getStringList("PositionsList").get(i) + ".Z"));
                            Meta2.setDisplayName("§1" + Positioncfg.getStringList("PositionsList").get(i));
                            Meta2.setLore(cords2);
                            istack2.setItemMeta(Meta2);
                            inv.setItem(i, istack2);
                        }
                        else {
                            p.sendMessage("§4Null!\n§4Pls Contact a Server Admin!");
                        }
                        p.openInventory(inv);
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Du hast noch keine Positionen gespeichert!");
                }
            }
            else if (args.length == 2) {
                switch (args[0].toLowerCase()) {
                    case "delete":
                    case "del":
                    case "remove": {
                        if (Positioncfg.getStringList("PositionsList").contains(args[1])) {
                            Positioncfg.set("Position." + args[1], (Object)null);
                            final List<String> list = (List<String>)Positioncfg.getStringList("PositionsList");
                            list.remove(args[1]);
                            Positioncfg.set("PositionsList", (Object)list);
                            try {
                                Positioncfg.save(Positionf);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§1Die Position wurde erfolgreich gel\u00f6scht!");
                            return false;
                        }
                        p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Diese Position hast du noch nicht gesetzt!");
                        return false;
                    }
                    case "get": {
                        if (Positioncfg.getStringList("PositionsList").contains(args[1])) {
                            final int x = Positioncfg.getInt("Position." + args[1] + ".X");
                            final int z = Positioncfg.getInt("Position." + args[1] + ".Z");
                            final int y = Positioncfg.getInt("Position." + args[1] + ".Y");
                            p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§1Die Position ist bei den Koordinaten: " + x + " " + y + " " + z + "!");
                            return false;
                        }
                        p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Diese Position hast du noch nicht gesetzt!");
                        return false;
                    }
                    case "set": {
                        if (!Positioncfg.contains("Position." + args[1] + ".World")) {
                            positions.add(args[1]);
                            Positioncfg.set("PositionsList", (Object)positions);
                            Positioncfg.set("Position." + args[1] + ".World", (Object)p.getWorld().getName());
                            Positioncfg.set("Position." + args[1] + ".X", (Object)p.getLocation().getBlockX());
                            Positioncfg.set("Position." + args[1] + ".Z", (Object)p.getLocation().getBlockZ());
                            Positioncfg.set("Position." + args[1] + ".Y", (Object)p.getLocation().getBlockY());
                            p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§1Die Position " + args[1] + " wurde gesetzt.");
                            try {
                                Positioncfg.save(Positionf);
                            }
                            catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            return false;
                        }
                        p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Diese Position hast du schon gesetzt!");
                        return false;
                    }
                    default:
                        break;
                }
                p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§1Bitte benutze /position set/get <Position>!");
            }
            else {
                p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§1Bitte benutze /position set/get <Position>!");
            }
        }
        else {
            sender.sendMessage(Kreiscraft.nocons);
        }
        return false;
    }
    
    public ArrayList<String> onTabComplete(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final File Positionf = new File(Kreiscraft.getInstance().getDataFolder().getPath(), String.valueOf(sender.getName()) + "Positions.yml");
        if (!Positionf.exists()) {
            try {
                Positionf.createNewFile();
            }
            catch (IOException ext) {
                ext.printStackTrace();
            }
        }
        final FileConfiguration Positioncfg = (FileConfiguration)YamlConfiguration.loadConfiguration(Positionf);
        final ArrayList<String> list = new ArrayList<String>();
        if (args.length == 0) {
            return list;
        }
        if (args.length == 1) {
            list.add("set");
            list.add("get");
            list.add("remove");
            list.add("delete");
            list.add("del");
        }
        else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("set")) {
                return null;
            }
            list.addAll(Positioncfg.getStringList("PositionsList"));
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
    
    @EventHandler
    public void onInteract(final InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§1§lDeine Positionen")) {
            final World world = e.getWhoClicked().getWorld();
            world.playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_LAND, 100.0f, 0.0f);
            e.setCancelled(true);
        }
    }
}
