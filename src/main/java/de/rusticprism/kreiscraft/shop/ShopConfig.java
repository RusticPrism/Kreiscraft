package de.rusticprism.kreiscraft.shop;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import de.rusticprism.kreiscraft.commands.Kreiscraft;
import jdk.tools.jmod.Main;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.Locale;

public class ShopConfig {
    private static FileConfiguration getconfig() {
        return ShopRawConfig.getConfig();
    }
    public static Location getShopLocation(String name, Player player) {
        Location location = new Location((World) ShopRawConfig.get(player.getUniqueId() + "." + name + ".World")
                , (Double) ShopRawConfig.get(player.getUniqueId() + "." + name + ".X"),
                (Double) ShopRawConfig.get(player.getUniqueId() + "." + name + ".Y"),
                (Double) ShopRawConfig.get(player.getUniqueId() + "." + name + ".Z"));
       return location;
    }
    public static Player getShopOwner(Location location) {
        FileConfiguration config = getconfig();
        return (Player) ShopRawConfig.get(location.toString() + ".owner");
    }
    public static String getShopName(Location location) {
        FileConfiguration config = getconfig();
        return (String) ShopRawConfig.get(location.toString() + ".name");
    }
    public static void setShopLocation(String name,Player player, Location location) {
       ShopRawConfig.set(player.getUniqueId() + "." + name + ".World", location.getWorld().getName());
        ShopRawConfig.set(player.getUniqueId() + "." + name + ".X", String.valueOf(location.getX()));
        ShopRawConfig.set(player.getUniqueId() + "." + name + ".Y", String.valueOf(location.getY()));
        ShopRawConfig.set(player.getUniqueId() + "." + name + ".Z", String.valueOf(location.getZ()));
    }
    public static void setShopOwner(Location location,Player owner) {
        ShopRawConfig.set(String.valueOf(location), String.valueOf(owner.getUniqueId()));
    }
    public static void setShopName(Location location,String name) {
        ShopRawConfig.set(String.valueOf(location),name);
    }
}
