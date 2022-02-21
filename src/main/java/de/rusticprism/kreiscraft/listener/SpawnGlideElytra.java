package de.rusticprism.kreiscraft.listener;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.KeybindComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SpawnGlideElytra implements Listener {

    private final int multiplyValue;
    public static int spawnRadius;
    private final List<Player> flying = new ArrayList<>();
    private final List<Player> boosted = new ArrayList<>();

    public SpawnGlideElytra(Plugin plugin) {
        this.multiplyValue = plugin.getConfig().getInt("SpawnGlideElytra.multiplyvalue");
        this.spawnRadius = plugin.getConfig().getInt("SpawnGlideElytra.spawnRadius");

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if(Bukkit.getWorld("Survival") == null) {
                return;
            }
            Bukkit.getWorld("Survival").getPlayers().forEach(player -> {
                if(player.getGameMode() !=GameMode.SURVIVAL) return;
                player.setAllowFlight(isInSpawnRadius(player));
                if(flying.contains(player) && !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {
                    player.setAllowFlight(false);
                    player.setGliding(false);
                    boosted.remove(player);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        flying.remove(player);
                    }, 5);
                }
            });
            Bukkit.getWorld(Navigator.worldname).getPlayers().forEach(player -> {
                if(flying.contains(player)) {
                    flying.remove(player);
                    boosted.remove(player);
                    player.setGliding(false);
                    player.setAllowFlight(false);
                }
            });
        },0,3);
    }



    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        if(!isInSpawnRadius(e.getPlayer())) return;
        e.setCancelled(true);
        e.getPlayer().setGliding(true);
        flying.add(e.getPlayer());
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
    if((e.getEntityType() == EntityType.PLAYER)
        && (e.getCause() == EntityDamageEvent.DamageCause.FALL
         || e.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL)
        && flying.contains(e.getEntity())) e.setCancelled(true);
    if(e.getEntityType() == EntityType.PLAYER && isInSpawnRadius((Player) e.getEntity())) {
        e.setCancelled(true);
    }
    }
    @EventHandler
    public void onSwapHand(PlayerSwapHandItemsEvent e) {
        if(boosted.contains(e.getPlayer())) return;
        if(!e.getPlayer().isGliding()) return;
        if(!isInSpawnRadius(e.getPlayer())) return;
        e.setCancelled(true);
        boosted.add(e.getPlayer());
        e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(multiplyValue));
    }
    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent e){
        if(e.getEntityType() == EntityType.PLAYER && flying.contains(e.getEntity())) {
            e.setCancelled(true);
            Player player = (Player) e.getEntity();
            if(boosted.contains(player)) return;
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new ComponentBuilder("Drücke ")
                            .append(new KeybindComponent("key.swapOffhand"))
                            .append( " um dich zu boosten")
                            .create());
        }
    }
    public static boolean isInSpawnRadius(Player player) {
        if(!player.getWorld().getName().equalsIgnoreCase("Survival")) return false;
        return player.getWorld().getSpawnLocation().distance(player.getLocation()) <= spawnRadius;
    }
}
