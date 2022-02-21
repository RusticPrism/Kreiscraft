package de.rusticprism.kreiscraft.commands;

import de.rusticprism.kreiscraft.listener.*;
import de.rusticprism.kreiscraft.lobby.Bossbar;
import de.rusticprism.kreiscraft.lobby.BuildListeners;
import de.rusticprism.kreiscraft.lobby.XpBar;
import de.rusticprism.kreiscraft.lobby.commands.*;
import de.rusticprism.kreiscraft.shop.SignListener;
import de.rusticprism.kreiscraft.utils.*;
import de.rusticprism.kreiscraft.listener.Reboot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class Kreiscraft extends JavaPlugin
{
    public File f;
    public static Kreiscraft instance;
    public static Kreiscraft plugin;
    private Tablist tablist;
    private Reboot reboot;
    public static String noperms;
    public static String prefix;
    public static String clprefix;
    public static String clnocons;
    public static String lobbyprefix;
    public static String lobbynocons;
    public static String lobbynoperms;
    public static String nocons;
    public static String teamprefix;
    public static HashMap<UUID, User> USERS = new HashMap<>();
    
    static {
        Kreiscraft.noperms = "§b[Kreiscraft] §4Dazu hast du keine Rechte!";
        Kreiscraft.prefix = "§b[Kreiscraft]";
        Kreiscraft.clprefix = "§6[Challenge]";
        Kreiscraft.clnocons = "§6[Challenge] §4Du musst ein Spieler sein!";
        Kreiscraft.lobbyprefix = "§6[Lobby]";
        Kreiscraft.lobbynocons = "§6[Lobby] §4Du musst ein Spieler sein!";
        Kreiscraft.lobbynoperms = "§6[Lobby] §4Dazu hast du keine Rechte!";
        Kreiscraft.nocons = "§b[Kreiscraft] §4Du musst ein Spieler sein!";
        Kreiscraft.teamprefix = "§4[§aT§be§ea§5m§4]§4";
    }
    
    public Kreiscraft() {
        f = new File(getDataFolder().getPath(), "HubPunkte.yml");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void onLoad() {
        Kreiscraft.instance = this;
    }
    
    public void onEnable() {
        getLogger().info(Kreiscraft.prefix + "§aHello");
        Kreiscraft.plugin = this;
        (reboot = new Reboot(false, 0)).setReboot(false);
        Bossbar.createBar();
        reboot.setTime(30);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Tps(), 100L, 1L);
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("erklarung").setExecutor(new ErklaerungCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("build").setExecutor(new Build_CMD());
        getCommand("setworldspawn").setExecutor(new WorldSpawnCommand());
        getCommand("sudo").setExecutor(new SudoCommand());
        getCommand("reboot").setExecutor(new RebootCommand());
        getCommand("hub").setExecutor(new HubCommand());
        getCommand("sethub").setExecutor(new SetHubCommand());
        getCommand("spawnmob").setExecutor(new SpawnmobCommand());
        getCommand("pos").setExecutor(new PositionCommand());
        getCommand("skull").setExecutor(new SkullCommand());
        getCommand("world").setExecutor(new WorldCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("weather").setExecutor(new WeatherCommand());
        getCommand("item").setExecutor(new ItemCommand());
        getCommand("uuid").setExecutor(new UUID_Command());
        getCommand("ip").setExecutor(new Ip_Command());
        getCommand("wartung").setExecutor(new WartungCommand());
        getCommand("status").setExecutor(new StatusCommand());
        getCommand("portal").setExecutor(new PortalCommand());
        Bukkit.getPluginManager().registerEvents(new SpawnGlideElytra(plugin),this);
        Bukkit.getPluginManager().registerEvents(new MotdListener(),this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PortalGoThru(), this);
        Bukkit.getPluginManager().registerEvents(new Tablist(), this);
        Bukkit.getPluginManager().registerEvents(new ChatColor(), this);
        Bukkit.getPluginManager().registerEvents(new SignColor(), this);
        Bukkit.getPluginManager().registerEvents(new PositionCommand(), this);
        Bukkit.getPluginManager().registerEvents(new Navigator(), this);
        Bukkit.getPluginManager().registerEvents(new BuildListeners(), this);
        Bukkit.getPluginManager().registerEvents(new XpBar(), this);
        Bukkit.getPluginManager().registerEvents(new Bossbar(), this);
        Bukkit.getPluginManager().registerEvents(new SeedCommand(), this);
        Bukkit.getPluginManager().registerEvents(new Speed(), this);
        Bukkit.getPluginManager().registerEvents(new Combat(),this);
        Bukkit.getPluginManager().registerEvents(new SignListener(),this);
        StatusConfig.savedefaultConfig();
        WartungsModus.CreateDefaultConfig();
        getConfig().addDefault("SpawnGlideElytra.multiplyvalue", 3);
        getConfig().addDefault("SpawnGlideElytra.spawnRadius", 30);
        getConfig().options().copyDefaults(true);
        saveConfig();
        new CombatTimer(plugin);

        for (Player p : Bukkit.getOnlinePlayers()) {
            USERS.put(p.getUniqueId(), new User(p));
        }
    }
    public void onDisable() {
       getLogger().info(Kreiscraft.prefix + "§4Bye Bye");
    }

    
    public static Kreiscraft getInstance() {
        return Kreiscraft.instance;
    }
    
    public Reboot getReboot() {
        return reboot;
    }
    
    public String getNoperms() {
        return Kreiscraft.noperms;
    }
    
    public String getPrefix() {
        return Kreiscraft.prefix;
    }
    
    public String getNocons() {
        return Kreiscraft.nocons;
    }
    
    public static Kreiscraft getPlugin() {
        return Kreiscraft.plugin;
    }
    
    public Tablist getTablist() {
        return tablist;
    }
}
