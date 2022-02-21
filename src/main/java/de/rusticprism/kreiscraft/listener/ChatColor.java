package de.rusticprism.kreiscraft.listener;

import de.rusticprism.kreiscraft.utils.Status;
import org.bukkit.event.EventHandler;
import org.bukkit.Sound;
import de.rusticprism.kreiscraft.commands.Kreiscraft;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Listener;

public class ChatColor implements Listener
{
    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String msg = e.getMessage();
        if (msg.startsWith("@team") || e.getMessage().startsWith("@Team") || e.getMessage().startsWith("@TEAM")) {
            if (p.hasPermission("kreiscraft.team.send")) {
                e.setCancelled(true);
                for (final Player team : Bukkit.getOnlinePlayers()) {
                    if (team.hasPermission("kreiscraft.team.see")) {
                        p.sendMessage(Kreiscraft.teamprefix + p.getName() + " §4§l>> " + org.bukkit.ChatColor.translateAlternateColorCodes('&', msg.replaceAll("@team", "§7")));
                    }
                    else {
                        this.Chatmessage(p, msg, e);
                    }
                }
            }
            else {
                for (final Player team : Bukkit.getOnlinePlayers()) {
                    if (!team.hasPermission("kreiscraft.team.see")) {
                        return;
                    }
                    team.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 100.0f, 0.0f);
                }
            }
        }
        else {
            this.Chatmessage(p, msg, e);
        }
    }
    
    public void Chatmessage(final Player p, final String msg, final AsyncPlayerChatEvent e) {
      e.setFormat(org.bukkit.ChatColor.translateAlternateColorCodes('&',Status.getPrefix(p) + p.getName() +" §7>> " + msg));
    }
}
