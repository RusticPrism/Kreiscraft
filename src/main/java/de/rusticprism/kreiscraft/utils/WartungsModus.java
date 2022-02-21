package de.rusticprism.kreiscraft.utils;

import de.rusticprism.kreiscraft.commands.Kreiscraft;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class WartungsModus {
    private static boolean enabled;
    private static String WARTUNGS_MESSAGE = "§7===================================" +
            "\n \n§c>> Wartung << " +
            "\n§7Kreiscraft führt derzeit Wartungsarbeiten durch... " +
            "\n§7Kreiscraft bittet um dein Verständniss! " +
            "\n §7\u25a1 §bKreiscraft §7\u25a1 \n " +
            "\n §7===================================";

    public static boolean isEnabled() {
        File file = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data/wartungsmodus.yml");
        File file1 = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data");
        if(!file.exists()) {
            file1.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config.getBoolean("Enabled");
    }
    public static void kickall(CommandSender kicker) {
        for(Player all : Bukkit.getOnlinePlayers()) {
            if(!all.hasPermission("kreiscraft.wartung.bypass")) {
                all.kickPlayer(getWartungsMessage());
            }else all.sendMessage(Kreiscraft.prefix + "Der Spieler " + kicker.getName() + " hat den Wartungsmodus aktiviert, " +
                    "du wurdest aber nicht gekickt da du genügend Rechte hattest!");
        }
    }

    private static void setEnabled(boolean enabled) {
        File file = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data/wartungsmodus.yml");
        File file1 = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data");
        if(!file.exists()) {
            file1.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
       config.set("Enabled",enabled);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getWartungsMessage() {
        File file = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data/wartungsmodus.yml");
        File file1 = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data");
        if(!file.exists()) {
            file1.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
              Kreiscraft.plugin.getLogger().warning("Interaction mit der Wartungs Config Fehlgeschlagen!");
              return null;
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config.getString("Message");
    }

    public static void setWartungsMessage(String wartungsMessage) {
        File file = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data/wartungsmodus.yml");
        File file1 = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data");
        if(!file.exists()) {
            file1.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                Kreiscraft.plugin.getLogger().warning("Interaction mit der Wartungs Config Fehlgeschlagen!");
                return;
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("Message",wartungsMessage);
        try {
            config.save(file);
        } catch (IOException e) {
           Kreiscraft.plugin.getLogger().warning("Wartungs Config konnte nicht gespeichert werden!");
        }
    }
    public static void an() {
        setEnabled(true);
    }
    public static void aus() {
        setEnabled(false);
    }
    public static void CreateDefaultConfig() {
        Kreiscraft.plugin.saveResource("data/wartungsmodus.yml",false);
    }
}
