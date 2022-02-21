package de.rusticprism.kreiscraft.utils;

import de.rusticprism.kreiscraft.commands.Kreiscraft;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public static File hublocationfile() {
        File hublocation = new File(Kreiscraft.plugin.getDataFolder().getPath(),"data/hublocation.yml");
        if(!hublocation.exists()){
            try {
                hublocation.createNewFile();
            } catch (IOException e) {
                System.out.println(Kreiscraft.lobbyprefix + "§cHublocation konnte nicht erstellt werden!");
            }
        }
        return hublocation;
    }
    public static FileConfiguration hublocation(){
        FileConfiguration hublocationcfg = YamlConfiguration.loadConfiguration(hublocationfile());
        return hublocationcfg;
    }
    public static void savehublocation() {
        try {
            hublocation().save(hublocationfile());
        } catch (IOException e) {
            System.out.println(Kreiscraft.lobbyprefix + "§cHublocation konnt nicht gespeichert werden!");
        }
    }
    private static File buildf() {
        File buildf = new File(Kreiscraft.plugin.getDataFolder().getPath(), "data/build.yml");
        if(!buildf.exists()) {
            try {
                buildf.createNewFile();
            } catch (IOException e) {
                System.out.println(Kreiscraft.prefix + "Error: BuildFile!");
            }
        }
        return buildf;
    }
    public static FileConfiguration buildcfg() {
        FileConfiguration buildcfg = YamlConfiguration.loadConfiguration(buildf());
        return buildcfg;
    }
    public static void savebuildf() {
        try {
            buildcfg().save(buildf());
        } catch (IOException e) {
            System.out.println(Kreiscraft.prefix + "Error: Save BuildFile!");
        }
    }
}
