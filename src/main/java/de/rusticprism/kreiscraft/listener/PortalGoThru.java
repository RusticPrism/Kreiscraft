package de.rusticprism.kreiscraft.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PortalGoThru implements Listener {

    @EventHandler
    public void onPortal(PlayerPortalEvent e) {
        if(SpawnGlideElytra.isInSpawnRadius(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
