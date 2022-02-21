package de.rusticprism.kreiscraft.commands;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCommand implements CommandExecutor
{
    @SuppressWarnings("deprecation")
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("kreiscraft.command.skull")) {
                if (args.length == 0) {
                    final ItemStack is = new ItemStack(Material.PLAYER_HEAD);
                    final SkullMeta im = (SkullMeta)is.getItemMeta();
                    im.setOwningPlayer((OfflinePlayer)p);
                    im.setDisplayName("§e" + p.getName() + "§s Skull");
                    is.setItemMeta((ItemMeta)im);
                    p.getInventory().addItem(new ItemStack[] { is });
                }
                else if (args.length == 1) {
                    final ItemStack is = new ItemStack(Material.PLAYER_HEAD);
                    final SkullMeta im = (SkullMeta)is.getItemMeta();
                    im.setOwner(args[0]);
                    im.setDisplayName("§e" + args[0] + "§s Skull");
                    is.setItemMeta((ItemMeta)im);
                    p.getInventory().addItem(new ItemStack[] { is });
                }
                else {
                    p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Bitte benutze §6/Skull <Spieler>§4!");
                }
            }
            else {
                p.sendMessage(Kreiscraft.noperms);
            }
        }
        else {
            sender.sendMessage(Kreiscraft.nocons);
        }
        return false;
    }
}
