package de.rusticprism.kreiscraft.shop;

import de.rusticprism.kreiscraft.commands.Kreiscraft;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ShopRawConfig {
    public static FileConfiguration getConfig() {
        File file = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data/shop.yml");
        File file1 = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data");
        if(!file.exists()) {
            try {
                file1.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                Kreiscraft.plugin.getLogger().warning("Couldn't create shop file!");
                e.printStackTrace();
                return null;
            }
        }
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration;
    }
    public static void set(String path,String value) {
        File file = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data/shop.yml");
        File file1 = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data");
        if(!file.exists()) {
            try {
                file1.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                Kreiscraft.plugin.getLogger().warning("Couldn't create shop file!");
                e.printStackTrace();
                return;
            }
        }
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set(path,value);
        try {
            configuration.save(file);
        } catch (IOException e) {
            Kreiscraft.plugin.getLogger().warning("Couldn't save shop file!");
            e.printStackTrace();
            return;
        }
    }
    public static Object get(String path) {
        File file = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data/shop.yml");
        File file1 = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data");
        if(!file.exists()) {
            try {
                file1.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                Kreiscraft.plugin.getLogger().warning("Couldn't create status file!");
                e.printStackTrace();
                return null;
            }
        }
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.get(path);
    }
}
