package de.rusticprism.kreiscraft.listener;

import de.rusticprism.kreiscraft.utils.Config;
import de.rusticprism.kreiscraft.utils.ItemBuilder;
import de.rusticprism.kreiscraft.utils.Speed;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.Location;
import de.rusticprism.kreiscraft.commands.Kreiscraft;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.Listener;

public class Navigator implements Listener
{
	  public static FileConfiguration config = Kreiscraft.plugin.getConfig();
	    public static String worldname = config.getString("Hub.World");
	    public static void givenavigator(Player p) {
            p.getInventory().clear();
            final ItemStack bundlestack = new ItemStack(Material.BUNDLE);
            final ItemMeta bundlemeta = bundlestack.getItemMeta();
            ItemStack speedstack = new ItemStack(Material.LEATHER_BOOTS);
            ItemMeta speedmeta = speedstack.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("§aCurrent Speed: " + Speed.getSpeed(p));
            speedmeta.setLore(lore);
            speedmeta.setDisplayName("§aSpeed");
            speedstack.setItemMeta(speedmeta);
            bundlemeta.setDisplayName("§5Rucksack");
            bundlemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            bundlestack.setItemMeta(bundlemeta);
            bundlestack.addUnsafeEnchantment(Enchantment.MENDING, 100000);
	    	  final ItemStack istack = new ItemStack(Material.COMPASS);
	            final ItemMeta imeta = istack.getItemMeta();
	            imeta.setDisplayName("§cNavigator");
	            istack.setItemMeta(imeta);
	            p.getInventory().setItem(4, istack);
                p.getInventory().setItem(0  , speedstack);
	    }
    @EventHandler
    public void onWorldchange(final PlayerChangedWorldEvent e) {
        final Player p = e.getPlayer();
        if (p.getWorld().getName().equalsIgnoreCase(worldname)) {
          givenavigator(p);
        }else Speed.setSpeed(e.getPlayer(), 0.1);
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if (!e.getAction().equals((Object)Action.RIGHT_CLICK_AIR) && !e.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (e.getItem() == null) {
            return;
        }
        if (!e.getItem().getType().equals((Object)Material.COMPASS)) {
            return;
        }
        if (e.getPlayer().getWorld().getName().equalsIgnoreCase(worldname)) {
            final Inventory inv = Bukkit.createInventory(null, 9, "§cNavigator");
            final ItemStack istack1 = new ItemStack(Material.GRASS_BLOCK);
            final ItemMeta imeta = istack1.getItemMeta();
            imeta.setDisplayName("§aSurvival");
            istack1.setItemMeta(imeta);
            inv.setItem(2, istack1);
            ItemStack itemStack = new ItemStack(Material.NETHERITE_BOOTS);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§6[§cWarps§6]");
            itemStack.setItemMeta(itemMeta);
            inv.setItem(6,itemStack);
            e.getPlayer().openInventory(inv);
        }
    }
    
    @EventHandler
    public void onInventory(final InventoryClickEvent e) {
        final FileConfiguration cfg = Config.hublocation();
        final Player p = (Player)e.getWhoClicked();
        if (!p.getWorld().getName().equalsIgnoreCase(worldname)) {
            return;
        }
        if (!e.getView().getTitle().equals("§cNavigator")) {
            return;
        }
        e.setCancelled(true);
        if(e.getCurrentItem() == null) {
        	return;
        }
        if (e.getCurrentItem().getType() == Material.GRASS_BLOCK) {
            if (Bukkit.getWorld(cfg.getString("Hublocation." + p.getUniqueId().toString() + ".World"))== null) {
                p.teleport(Bukkit.getWorld("Survival").getSpawnLocation());
                for (int i = 0; i < 200; ++i) {
                    p.sendMessage("  ");
                }
                p.sendMessage(Kreiscraft.lobbyprefix + "§aDu wurdest nach Survival teleportiert!");
                return;
            }
            final World world = Bukkit.getWorld(cfg.getString("Hublocation." + p.getUniqueId().toString() + ".World"));
            final double x = cfg.getDouble("Hublocation." + p.getUniqueId().toString() + ".X");
            final double y = cfg.getDouble("Hublocation." + p.getUniqueId().toString() + ".Y");
            final double z = cfg.getDouble("Hublocation." + p.getUniqueId().toString() + ".Z");
            final float yaw = (float) cfg.getDouble("Hublocation." + p.getUniqueId().toString() + ".Yaw");
            final float pitch = (float) cfg.getDouble("Hublocation." + p.getUniqueId().toString() + ".Pitch");
            Location location = new Location(world, x, y, z, yaw, pitch);
            p.teleport(location);
            for (int i = 0; i < 200; ++i) {
                p.sendMessage("  ");
            }
            p.sendMessage(Kreiscraft.lobbyprefix + "§aDu wurdest nach Survival teleportiert!");
            e.setCancelled(true);
        }else if (e.getCurrentItem().getType() == Material.NETHERITE_BOOTS) {
            Inventory inv = Bukkit.createInventory(null, 9*2, "§cNavigator");
            inv.setItem(2, ItemBuilder.createitem("§dAsiaDorf",1,Material.PINK_WOOL));
            inv.setItem(6,ItemBuilder.createitem("§8KirchenDorf",1,Material.DARK_OAK_LOG));
            inv.setItem(9,ItemBuilder.createitem("§1Parkour",1,Material.CYAN_STAINED_GLASS));
            inv.setItem(13,ItemBuilder.createitem("§6StartDorf",1,Material.STRIPPED_SPRUCE_WOOD));
            inv.setItem(17,ItemBuilder.createitem("§8Burg",1,Material.STONE_BRICKS));
            p.openInventory(inv);
        }else if(e.getCurrentItem().getType() == Material.PINK_WOOL) {
            ((Player) e.getWhoClicked()).chat("/warp Asia_Dorf");
        }else if(e.getCurrentItem().getType() == Material.DARK_OAK_LOG) {
            ((Player) e.getWhoClicked()).chat("/warp Kirchen_Dorf");
        }else if(e.getCurrentItem().getType() == Material.CYAN_STAINED_GLASS){
            ((Player) e.getWhoClicked()).chat("/warp Parkour");
                }else if(e.getCurrentItem().getType() == Material.STRIPPED_SPRUCE_WOOD){
            ((Player) e.getWhoClicked()).chat("/warp Start_Dorf");
                }else if(e.getCurrentItem().getType() == Material.STONE_BRICKS){
        ((Player) e.getWhoClicked()).chat("/warp Burg");
            }
            }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (p.getWorld().getName().equalsIgnoreCase(worldname)) {
        	p.getInventory().clear();
        	p.setGameMode(GameMode.SURVIVAL);
           givenavigator(p);
        }
    }
}
