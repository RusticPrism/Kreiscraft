package de.rusticprism.kreiscraft.lobby;

import org.bukkit.plugin.Plugin;
import de.rusticprism.kreiscraft.commands.Kreiscraft;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BarColor;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class Bossbar implements Listener
{
    private static BossBar bar;
    public static FileConfiguration config = Kreiscraft.plugin.getConfig();
    public static String worldname = config.getString("Hub.World");
    @EventHandler
    public void onworlchange(final PlayerChangedWorldEvent e) {
        final Player p = e.getPlayer();
        if (p.getWorld().getName().equalsIgnoreCase(worldname)) {
            Bossbar.bar.addPlayer(p);
            Bossbar.bar.setVisible(true);
        }
        else {
            if (!Bossbar.bar.getPlayers().contains(p)) {
                return;
            }
            Bossbar.bar.removePlayer(p);
        }
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onjoin(final PlayerJoinEvent e) {
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (!all.getWorld().getName().equalsIgnoreCase(worldname)) {
                return;
            }
            if (Bossbar.bar.getPlayers().contains(e.getPlayer())) {
                Bossbar.bar.removePlayer(e.getPlayer());
            }
            Bossbar.bar.setVisible(true);
            Bossbar.bar.addPlayer(all);
            all.setFoodLevel(20);
            all.setHealth(all.getMaxHealth());
        }
    }
    
    public BossBar getBar() {
        return Bossbar.bar;
    }
    
    public static void createBar() {
        (Bossbar.bar = Bukkit.createBossBar("§bKreiscraft", BarColor.BLUE, BarStyle.SOLID, new BarFlag[0])).setVisible(true);
        run();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) Kreiscraft.plugin, (Runnable)new Runnable() {
            int count = -1;
            double progress = 1.0;
            double time = 0.0033333333333333335;
            
            @SuppressWarnings("deprecation")
			@Override
            public void run() {
                Bossbar.bar.setProgress(this.progress);
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    if (!all.getWorld().getName().equalsIgnoreCase(worldname)) {
                        return;
                    }
                    all.setFoodLevel(20);
                    all.setHealth(all.getMaxHealth());
                }
                switch (this.count) {
                    case -1: {
                        break;
                    }
                    case 0: {
                        Bossbar.bar.setColor(BarColor.RED);
                        Bossbar.bar.setTitle("§4Owner: Ballicusonnic");
                        break;
                    }
                    case 1: {
                        Bossbar.bar.setColor(BarColor.BLUE);
                        Bossbar.bar.setTitle("§bDeveloper: RusticPrism");
                        break;
                    }
                    case 2: {
                        Bossbar.bar.setColor(BarColor.BLUE);
                        Bossbar.bar.setTitle("§bBuilder: mo9050be");
                        break;
                    }
                    default: {
                        Bossbar.bar.setColor(BarColor.BLUE);
                        Bossbar.bar.setTitle("§bKreiscraft");
                        this.count = -1;
                        break;
                    }
                }
                this.progress -= this.time;
                if (this.progress <= 0.0) {
                    ++this.count;
                    this.progress = 1.0;
                }
            }
        }, 0L, 2L);
    }
}
