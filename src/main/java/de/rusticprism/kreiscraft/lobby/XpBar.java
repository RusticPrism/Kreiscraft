package de.rusticprism.kreiscraft.lobby;

import org.bukkit.event.EventHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import org.bukkit.event.player.PlayerJoinEvent;

import de.rusticprism.kreiscraft.commands.Kreiscraft;

import org.bukkit.event.Listener;

public class XpBar implements Listener
{
	public static FileConfiguration config = Kreiscraft.plugin.getConfig();
    public static String worldname = config.getString("Hub.World");
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (p.getWorld().getName().equalsIgnoreCase(worldname)) {
            final SimpleDateFormat SDF = new SimpleDateFormat("y");
            SDF.setTimeZone(TimeZone.getTimeZone("GMT"));
            final String var1 = SDF.format(new Date());
            final int var2 = Integer.parseInt(var1);
            p.setLevel(var2);
            final SimpleDateFormat SDF2 = new SimpleDateFormat("M");
            final String var3 = SDF2.format(new Date());
            final float var4 = (float)Integer.parseInt(var3);
            p.setExp(0.99f);
            p.setExp(var4 / 12.0f);
        }
    }
}
