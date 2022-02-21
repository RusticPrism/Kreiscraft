package de.rusticprism.kreiscraft.listener;

import org.bukkit.plugin.Plugin;
import de.rusticprism.kreiscraft.commands.Kreiscraft;
import de.rusticprism.kreiscraft.commands.RebootCommand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class Reboot
{
    private boolean reboot;
    private int time;
    
    public Reboot(final boolean reboot, final int time) {
        this.reboot = reboot;
        this.time = time;
        this.run();
    }
    
    public boolean isReboot() {
        return this.reboot;
    }
    
    public void setReboot(final boolean reboot) {
        this.reboot = reboot;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(final int time) {
        this.time = time;
    }
    
    public void sendTitle() {
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (!this.isReboot()) {
                continue;
            }
            all.sendTitle("§4Neustart!", "§6Neustart in§4 " + this.getTime() + " §6Sekunden", 1, 40, 1);
        }
    }
    
    public void run() {
        new BukkitRunnable() {
            public void run() {
                if (Reboot.this.getTime() == 30) {
                    Reboot.this.sendTitle();
                }
                if (Reboot.this.getTime() == 10) {
                    Reboot.this.sendTitle();
                }
                if (Reboot.this.getTime() <= 10) {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage("§6Neustart in§4 " + Reboot.this.getTime() + " §6Sekunden");
                    }
                }
                if (Reboot.this.getTime() == 0 && Reboot.this.isReboot()) {
                    Reboot.this.setReboot(false);
                    Reboot.this.setTime(30);
                    String reboot = "";
                    for(int i = 1; i < RebootCommand.reason.size(); i++) {
                        reboot = reboot + RebootCommand.reason.get(i) + " ";
                    }
                    String finalReboot = reboot;
                    Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer(
                              "§7==================================="
                            + "\n \n "
                            + "§bNeustart\n"
                            + "\n§aReason: §b" + finalReboot
                            + "\n§aRestarter: §b" + RebootCommand.send.get(0)
                            + "\n§aDiscord: §bdiscord.kreiscraft.de"
                            + "\n \n§bNeustart"
                            + "\n \n"
                            + "§7==================================="));
                    Bukkit.getServer().spigot().restart();
                }
                if (!Reboot.this.isReboot()) {
                    return;
                }
                Reboot.this.setTime(Reboot.this.getTime() - 1);
            }
        }.runTaskTimer((Plugin) Kreiscraft.getInstance(), 20L, 20L);
    }
}
