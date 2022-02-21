package de.rusticprism.kreiscraft.lobby;

import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import de.rusticprism.kreiscraft.commands.Kreiscraft;
import de.rusticprism.kreiscraft.lobby.commands.Build_CMD;

import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.event.Listener;

@SuppressWarnings("deprecation")
public class BuildListeners implements Listener{
    public ArrayList<Player> build;
    
    public BuildListeners() {
        this.build = Build_CMD.build;
    }
   public static FileConfiguration config = Kreiscraft.plugin.getConfig();
    public static String worldname = config.getString("Hub.World");
    
    @EventHandler
    public void ondamage(final EntityDamageEvent e) {
        if (!e.getEntity().getWorld().getName().equalsIgnoreCase(worldname)) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        if (!this.build.contains(p)) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void ondamage(final EntityDamageByEntityEvent e) {
        if (!e.getEntity().getWorld().getName().equalsIgnoreCase(worldname)) {
            return;
        }
        if (e.getEntity() instanceof Player) {
        	 final Player p = (Player)e.getEntity();
             if (!this.build.contains(p)) {
                 e.setCancelled(true);
             }
        }
    }
    
    @EventHandler
    public void onbreak(final BlockBreakEvent e) {
        if (!e.getPlayer().getWorld().getName().equalsIgnoreCase(worldname)) {
            return;
        }
        if (!this.build.contains(e.getPlayer())) {
            e.getPlayer().sendMessage(String.valueOf(Kreiscraft.lobbyprefix) + "§4Dies kannst du hier nicht tun!");
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onplace(final BlockPlaceEvent e) {
        if (!e.getPlayer().getWorld().getName().equalsIgnoreCase(worldname)) {
            return;
        }
        if (!this.build.contains(e.getPlayer())) {
            e.getPlayer().sendMessage(String.valueOf(Kreiscraft.lobbyprefix) + "§4Wie hast du den Block erhalten!");
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onworld(final PlayerChangedWorldEvent e) {
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        if (!e.getPlayer().getWorld().getName().equalsIgnoreCase(worldname)) {
            if (!this.build.contains(e.getPlayer())) {
                return;
            }
            this.build.remove(e.getPlayer());
        }
        else {
            e.getPlayer().sendMessage(String.valueOf(Kreiscraft.lobbyprefix) + "§aWillkommen in der Lobby!");
        }
    }
    
    @EventHandler
    public void ondrop(final PlayerDropItemEvent e) {
        if (!e.getPlayer().getWorld().getName().equalsIgnoreCase(worldname)) {
            return;
        }
        if (!this.build.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onpick(final PlayerPickupItemEvent e) {
        if (!e.getPlayer().getWorld().getName().equalsIgnoreCase(worldname)) {
            return;
        }
        if (!this.build.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
    	if(e.getPlayer().getWorld().getName().equalsIgnoreCase(worldname)) {
    		if(!build.contains(e.getPlayer())) {
        if(e.getAction() == Action.PHYSICAL) {
             e.setCancelled(true);
         }else if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ArrayList<Material> interact = new ArrayList<>();
            interact.add(Material.CHEST);
            interact.add(Material.ENDER_CHEST);
            interact.add(Material.OAK_TRAPDOOR );
            interact.add(Material.DARK_OAK_TRAPDOOR );
            interact.add(Material.ACACIA_TRAPDOOR );
            interact.add(Material.BIRCH_TRAPDOOR );
            interact.add(Material.CRIMSON_TRAPDOOR );
            interact.add(Material.JUNGLE_TRAPDOOR);
            interact.add(Material.SPRUCE_TRAPDOOR);
            interact.add(Material.WARPED_TRAPDOOR);
            interact.add(Material.OAK_FENCE_GATE);
            interact.add(Material.DARK_OAK_FENCE_GATE);
            interact.add(Material.ACACIA_FENCE_GATE);
            interact.add(Material.BIRCH_FENCE_GATE);
            interact.add(Material.SPRUCE_FENCE_GATE);
            interact.add(Material.WARPED_FENCE_GATE);
            interact.add(Material.JUNGLE_FENCE_GATE);
            interact.add(Material.CRIMSON_FENCE_GATE);
            interact.add(Material.BREWING_STAND);
            interact.add(Material.CRAFTING_TABLE);
            interact.add(Material.BARREL);
            interact.add(Material.ENCHANTING_TABLE);
            interact.add(Material.CANDLE);
            interact.add(Material.CARTOGRAPHY_TABLE);
            interact.add(Material.TRAPPED_CHEST);
            interact.add(Material.FURNACE);
            interact.add(Material.LEVER);
            interact.add(Material.HOPPER);
            interact.add(Material.BLAST_FURNACE);
            interact.add(Material.SMOKER);
            interact.add(Material.LECTERN);
        	 if(interact.contains(e.getClickedBlock().getType()) || e.getClickedBlock().getType().name().endsWith("_BED")) {
        		 e.setCancelled(true);
        	 }else if (e.getClickedBlock().getType().name().startsWith("POTTED_") || e.getClickedBlock().getType() == Material.FLOWER_POT) {
                 e.setCancelled(true);
             }
         } else return;
         }else return;
    	}else return;
    }
@EventHandler
public void onpainting(HangingBreakByEntityEvent e) {
        if(e.getEntity() instanceof Painting && e.getRemover() instanceof Player && !build.contains(e.getRemover())) {
            e.setCancelled(true);
        }
}
    @EventHandler
    public void onItemFrameManipulate(PlayerInteractEntityEvent e) {
    	if(e.getPlayer().getWorld().getName().equalsIgnoreCase(worldname)) {
        if (e.getRightClicked() instanceof ItemFrame) {

            if (!build.contains(e.getPlayer())) {
                e.setCancelled(true);
            }else return;
        }
    	}else return;
	}
}
