package de.rusticprism.kreiscraft.listener;

import de.rusticprism.kreiscraft.commands.Kreiscraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class SeedCommand implements Listener {
    @EventHandler
    public void oncommand(PlayerCommandPreprocessEvent e) {
        if(e.getMessage().equalsIgnoreCase("/seed") || e.getMessage().equalsIgnoreCase("/minecraft:seed")) {
          e.setCancelled(true);
           if(e.getPlayer().isOp()) {
               e.getPlayer().sendMessage(Kreiscraft.prefix + "§4Selbst als Admin! So nicht!");
           }else if(e.getPlayer().hasPermission("kreiscraft.lobby.command.seed")) {
                    e.getPlayer().sendMessage(Kreiscraft.prefix + "So nicht!");
           }else return;
        }
    }
}
