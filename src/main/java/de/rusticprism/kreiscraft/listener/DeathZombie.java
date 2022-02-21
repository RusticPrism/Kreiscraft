package de.rusticprism.kreiscraft.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class DeathZombie implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location deathloc = p.getLocation();
        World world = deathloc.getWorld();
       Entity entity = world.spawnEntity(deathloc, EntityType.ZOMBIE);
       entity.setCustomNameVisible(true);
       entity.setCustomName("§b" + p.getName() + " §7[§aTime§7]");
       entity.setSilent(true);
       entity.setGlowing(true);
        Zombie zombie = (Zombie) entity;
        Inventory inv = (Inventory) zombie.getEquipment();
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(p);
        itemStack.setItemMeta(skullMeta);
        for(ItemStack itemStack1 : e.getDrops()) {
            inv.addItem(itemStack1);
            inv.setItem(5,itemStack);
        }
    }
}
