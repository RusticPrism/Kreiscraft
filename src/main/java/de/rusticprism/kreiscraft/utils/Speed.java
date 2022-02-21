package de.rusticprism.kreiscraft.utils;

import de.rusticprism.kreiscraft.commands.Kreiscraft;
import de.rusticprism.kreiscraft.listener.Navigator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Speed implements Listener {
    FileConfiguration config = Kreiscraft.plugin.getConfig();
    String worldname = config.getString("Hub.World");
    public static double getSpeed(Player player) {
        double speed = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();
        return speed *10;
    }
    public static void setSpeed(Player player, double amplifier) {
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(amplifier);
    }
    @EventHandler
    public void onspeedclick(PlayerInteractEvent e) {
        if (!e.getAction().equals((Object) Action.RIGHT_CLICK_AIR) && !e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (e.getItem() == null) {
            return;
        }
        if (!e.getItem().getType().equals( Material.LEATHER_BOOTS)) {
            return;
        }
        if (e.getPlayer().getWorld().getName().equalsIgnoreCase(worldname)) {
            Inventory inventory = Bukkit.createInventory(null, 9, "§aSpeed");
            ItemStack grayglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);
            ItemMeta bootsmeta = boots.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("§aSetze dein Speed auf: 1");
            bootsmeta.setLore(lore);
            boots.setItemMeta(bootsmeta);
            inventory.setItem(0,grayglass);
            inventory.setItem(1,grayglass);
            inventory.addItem(boots);
            boots.setType(Material.CHAINMAIL_BOOTS);
            lore.clear();
            lore.add("§aSetze dein Speed auf: 2");
            bootsmeta.setLore(lore);
            boots.setItemMeta(bootsmeta);
            inventory.addItem(boots);
            boots.setType(Material.IRON_BOOTS);
            lore.clear();
            lore.add("§aSetze dein Speed auf: 3");
            bootsmeta.setLore(lore);
            boots.setItemMeta(bootsmeta);
            inventory.addItem(boots);
            boots.setType(Material.DIAMOND_BOOTS);
            lore.clear();
            lore.add("§aSetze dein Speed auf: 4");
            bootsmeta.setLore(lore);
            boots.setItemMeta(bootsmeta);
            inventory.addItem(boots);
            boots.setType(Material.NETHERITE_BOOTS);
            lore.clear();
            lore.add("§aSetze dein Speed auf: 5");
            bootsmeta.setLore(lore);
            boots.setItemMeta(bootsmeta);
            inventory.addItem(boots);
            inventory.setItem(7,grayglass);
            inventory.setItem(8,grayglass);
            e.setCancelled(true);
            e.getPlayer().openInventory(inventory);
        }
    }
    @EventHandler
    public void onspeedinv(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (!p.getWorld().getName().equalsIgnoreCase(worldname)) {
            return;
        }
        if (!e.getView().getTitle().equals("§aSpeed")) {
            return;
        }
        e.setCancelled(true);
        if(e.getCurrentItem() == null) {
            return;
        }
        if(e.getCurrentItem().getType() == Material.GOLDEN_BOOTS) {
            setSpeed(p,0.1);
            p.sendMessage(Kreiscraft.lobbyprefix + "§bDein Speed ist nun §a" + getSpeed(p));
            Navigator.givenavigator(p);
        }else if(e.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS) {
            setSpeed(p,0.125);
            p.sendMessage(Kreiscraft.lobbyprefix + "§bDein Speed ist nun §a" + getSpeed(p));
            Navigator.givenavigator(p);
        }else if(e.getCurrentItem().getType() == Material.IRON_BOOTS) {
            setSpeed(p,0.15);
            p.sendMessage(Kreiscraft.lobbyprefix + "§bDein Speed ist nun §a" + getSpeed(p));
            Navigator.givenavigator(p);
        }else if(e.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
            setSpeed(p,0.175);
            p.sendMessage(Kreiscraft.lobbyprefix + "§bDein Speed ist nun §a" + getSpeed(p));
            Navigator.givenavigator(p);
        }else if(e.getCurrentItem().getType() == Material.NETHERITE_BOOTS) {
            setSpeed(p,0.2);
            p.sendMessage(Kreiscraft.lobbyprefix + "§bDein Speed ist nun §a" + getSpeed(p));
            Navigator.givenavigator(p);
        }else return;
    }
}
