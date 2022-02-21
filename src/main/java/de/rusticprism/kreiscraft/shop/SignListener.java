package de.rusticprism.kreiscraft.shop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {

        Location location = new Location(Bukkit.getWorld("world"),2,3,5);
    @EventHandler
    public void onSignPlace(BlockPlaceEvent e) {
       if(e.getBlockPlaced().getType().name().endsWith("_SIGN")) {
            if(e.getBlockAgainst().getType() == Material.CHEST) {
                Location loc = e.getBlockAgainst().getLocation();
               location = loc;

            }else return;
       }else return;
    }
    //[Shop]
    //ShulkerShop (ShopName)
    //diamond (Buying)
    //shulker_box (Selling)
    @EventHandler
    public void onSignEdit(SignChangeEvent e) {
        if(e.getLine(0).equalsIgnoreCase("[Shop]")) {
            e.setLine(0,"§6[§aShop§6]");
            ShopConfig.setShopName(location,e.getLine(2));
            ShopConfig.setShopOwner(location,e.getPlayer());
            ShopConfig.setShopLocation(e.getLine(1), e.getPlayer(),location);
        }else return;
    }
}
