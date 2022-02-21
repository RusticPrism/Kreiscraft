package de.rusticprism.kreiscraft.listener;

import de.rusticprism.kreiscraft.commands.Kreiscraft;
import de.rusticprism.kreiscraft.utils.Tps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Tablist implements Listener{
    
    
    public static void setTablist(final Player p) {
    	SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss");
        SDF.setTimeZone(TimeZone.getTimeZone("GMT"));
        final String d = SDF.format(new Date());
        p.setPlayerListHeader("§8§m                            §r§8[§1§lKreiscraft§r§8]§8§m                            \n§1Willkommen §8" + p.getName());
        p.setPlayerListFooter("§1Uhrzeit: §8" + d + "\n§1Dein Ping§8: " + getPing(p) + "\n§1Tps: §8"+ Math.round(Tps.getTPS()*100.0)/100.0 +"\n§1Discord: §8discord.kreiscraft.de \n §8§m                            §r§8[§1§lKreiscraft§r§8]§8§m                            ");
    }
    
    public static void updateDate(final Player p) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Kreiscraft.plugin, () -> {
        	SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss");
            SDF.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
            String d = SDF.format(new Date());
            p.setPlayerListFooter("§1Uhrzeit: §8" + d + "\n§1Dein Ping§8: " + getPing(p) + "\n§1Tps: §8"+ Math.round(Tps.getTPS()*100.0)/100.0 +"\n§1Discord: §8discord.kreiscraft.de \n §8§m                            §r§8[§1§lKreiscraft§r§8]§8§m                            ");
        }, 20L, 20L);
    }
    
    public static Object getPing(final Player player) {
    String ping = String.valueOf(player.getPing());
        return ping;
    }
}
