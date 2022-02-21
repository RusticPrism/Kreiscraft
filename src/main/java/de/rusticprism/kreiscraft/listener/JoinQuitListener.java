package de.rusticprism.kreiscraft.listener;

import de.rusticprism.kreiscraft.lobby.BuildListeners;
import de.rusticprism.kreiscraft.utils.Status;
import de.rusticprism.kreiscraft.utils.User;
import de.rusticprism.kreiscraft.utils.WartungsModus;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import de.rusticprism.kreiscraft.lobby.commands.HubCommand;

import org.bukkit.event.player.PlayerJoinEvent;
import de.rusticprism.kreiscraft.commands.Kreiscraft;
import org.bukkit.event.Listener;

public class JoinQuitListener implements Listener
{
    private static Tablist tablists;
    
    static {
        JoinQuitListener.tablists = Kreiscraft.getInstance().getTablist();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onconnect(PlayerLoginEvent e) {
        if(WartungsModus.isEnabled()) {
            if(!e.getPlayer().hasPermission("kreiscraft.wartung.bypass")) {
                e.setKickMessage(WartungsModus.getWartungsMessage());
                e.setResult(PlayerLoginEvent.Result.KICK_WHITELIST);
                return;
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(final PlayerJoinEvent e) {
        Kreiscraft.USERS.put(e.getPlayer().getUniqueId(),new User(e.getPlayer()));
        //JoinQuitListener.tablists.setAllPlayerTeams();
        //JoinQuitListener.tablists.setTablistRank(e.getPlayer());
        Status.setTab(e.getPlayer());
        Status.setTabforAll();
        e.setJoinMessage("§b[Kreiscraft]§6[§a+§6]" + e.getPlayer().getName());
        if(e.getPlayer().getWorld().getName().equalsIgnoreCase(BuildListeners.worldname)) {
        	
        }else HubCommand.sendtoHub(e.getPlayer());
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
            e.setQuitMessage("§b[Kreiscraft]§6[§c-§6]" + e.getPlayer().getName());
            Kreiscraft.USERS.remove(e.getPlayer().getUniqueId());
    }
}
