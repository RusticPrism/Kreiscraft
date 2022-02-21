package de.rusticprism.kreiscraft.listener;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MotdListener implements Listener {
    @EventHandler
    public void onPing(PaperServerListPingEvent e) {
        e.setMotd("§bKreiscraft                                §a[§61.18.9§a] \n                                  §1Enjoy your stay");
        e.setMaxPlayers(2022);
        e.setVersion("§bKreiscraft                           Kreis");
    }
}
