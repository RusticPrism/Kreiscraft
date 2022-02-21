package de.rusticprism.kreiscraft.lobby.commands;

import de.rusticprism.kreiscraft.utils.Config;
import de.rusticprism.kreiscraft.utils.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import de.rusticprism.kreiscraft.commands.Kreiscraft;

import java.io.IOException;

public class HubCommand implements CommandExecutor
{
    public static void sendtoHub(final Player p) {
        final FileConfiguration cfg = Config.hublocation();
        final FileConfiguration config = Kreiscraft.getPlugin().getConfig();
        final World world = Bukkit.getWorld(config.getString("Hub.World"));
        final double x = config.getDouble("Hub.X");
        final double z = config.getDouble("Hub.Z");
        final double y = config.getDouble("Hub.Y");
        final float yaw = (float)config.getDouble("Hub.Yaw");
        final float pitch = (float)config.getDouble("Hub.Pitch");
        final Location location = new Location(world, x, y, z, yaw, pitch);
        cfg.set("Hublocation." + p.getUniqueId() + ".World", p.getWorld().getName());
        cfg.set("Hublocation." + p.getUniqueId() + ".X", p.getLocation().getX());
        cfg.set("Hublocation." + p.getUniqueId() + ".Z", p.getLocation().getZ());
        cfg.set("Hublocation." + p.getUniqueId() + ".Y", p.getLocation().getY());
        cfg.set("Hublocation." + p.getUniqueId() + ".Yaw", p.getLocation().getYaw());
        cfg.set("Hublocation." + p.getUniqueId() + ".Pitch", p.getLocation().getPitch());
        User user = Kreiscraft.USERS.get(p.getUniqueId());
        if(user.getCombattime() <= 0) {
            try {
                cfg.save(Config.hublocationfile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.teleport(location); for (int i = 0; i < 200; ++i) {
                p.sendMessage("  ");
            }
            p.sendMessage(Kreiscraft.lobbyprefix + "§aDu wurdest in die Lobby teleportiert!");

        }else {
            p.sendMessage(Kreiscraft.prefix + "§cDu bist in Combat!");
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND,100,10);
            return;
        }
    }
    FileConfiguration config = Kreiscraft.plugin.getConfig();
    String worldname = config.getString("Hub.World");
    public static void sendtohubwithoutsaving(Player p) {
        final FileConfiguration config = Kreiscraft.getPlugin().getConfig();
        final World world = Bukkit.getWorld(config.getString("Hub.World"));
        final double x = config.getDouble("Hub.X");
        final double z = config.getDouble("Hub.Z");
        final double y = config.getDouble("Hub.Y");
        final float yaw = (float)config.getDouble("Hub.Yaw");
        final float pitch = (float)config.getDouble("Hub.Pitch");
        Location location = new Location(world, x, y, z, yaw, pitch);
        p.teleport(location); for (int i = 0; i < 200; ++i) {
            p.sendMessage("  ");
        }
        p.sendMessage(Kreiscraft.lobbyprefix + "§aDu wurdest in die Lobby teleportiert!");
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
                if (args.length == 0) {
                    if (!p.getWorld().getName().equalsIgnoreCase(worldname)) {
                        if(p.getWorld().getName().equalsIgnoreCase("Survival") || p.getWorld().getName().equalsIgnoreCase("Survival_nether") || p.getWorld().getName().equalsIgnoreCase("Survival_the_end")) {
                            sendtoHub(p);
                        }else sendtohubwithoutsaving(p);
                    }else p.sendMessage(Kreiscraft.lobbyprefix + "§cDu bist bereits im Hub");
                }else p.sendMessage(Kreiscraft.prefix + "§9Bitte benutze §6/hub, /l, /lobby §9!");
        }else sender.sendMessage(Kreiscraft.prefix + Kreiscraft.nocons);
        return false;
    }
}
