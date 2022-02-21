// 
// Decompiled by Procyon v0.5.36
// 

package de.rusticprism.kreiscraft.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.ChatColor;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.Listener;

public class SignColor implements Listener
{
    @EventHandler
    public void onSignChange(final SignChangeEvent e) {
        final String message = e.getLine(0);
        final String message2 = e.getLine(1);
        final String message3 = e.getLine(2);
        final String message4 = e.getLine(3);
        e.setLine(0, ChatColor.translateAlternateColorCodes('&', message));
        e.setLine(1, ChatColor.translateAlternateColorCodes('&', message2));
        e.setLine(2, ChatColor.translateAlternateColorCodes('&', message3));
        e.setLine(3, ChatColor.translateAlternateColorCodes('&', message4));
    }
}
