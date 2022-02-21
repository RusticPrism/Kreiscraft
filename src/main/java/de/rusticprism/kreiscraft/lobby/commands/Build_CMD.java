package de.rusticprism.kreiscraft.lobby.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.rusticprism.kreiscraft.commands.Kreiscraft;
import de.rusticprism.kreiscraft.listener.Navigator;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.bukkit.command.CommandExecutor;

public class Build_CMD implements CommandExecutor{
    

    public static ArrayList<Player> build = new ArrayList<Player>();
    
    FileConfiguration config = Kreiscraft.plugin.getConfig();
    String worldname = config.getString("Hub.World");
    public HashMap<String, ItemStack[]> buildinventories = new HashMap<>();
    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
    	File buildf = new File(Kreiscraft.plugin.getDataFolder().getPath(), "data/build.yml");
		if(!buildf.exists()) {
			try {
				buildf.createNewFile();
			} catch (IOException e) {
				System.out.println(Kreiscraft.prefix + "§cError BuildFile!");
			}
		}
		FileConfiguration buildcfg = YamlConfiguration.loadConfiguration(buildf);
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.getWorld().getName().equalsIgnoreCase(worldname)) {
                if (args.length == 0) {
                    if (p.hasPermission("kreiscraft.lobby.command.build")) {
                        if (!Build_CMD.build.contains(p)) {
                            Build_CMD.build.add(p);
                            p.getInventory().clear();
                            buildcfg.getConfigurationSection("Build").getKeys(false).forEach(key ->{
								ItemStack[] content = ((List<ItemStack>)  buildcfg.get("Build." + key)).toArray(new ItemStack[0]);
                            	buildinventories.put(key, content);
                            });
                            try {
                            p.getInventory().setContents(buildinventories.get(p.getUniqueId().toString()));
                            }catch(NullPointerException ex) {
                            	p.sendMessage(Kreiscraft.lobbyprefix + "§cEs konnte kein Inventar geladen werden!");
                            }
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(String.valueOf(Kreiscraft.lobbyprefix) + "§aDu kannst nun bauen!");
                            return true;
                        }
                        Build_CMD.build.remove(p);
                        buildinventories.put(p.getUniqueId().toString(), p.getInventory().getContents());
                        for(Entry<String, ItemStack[]> entry: buildinventories.entrySet()) {
                        	buildcfg.set("Build." + entry.getKey(), entry.getValue());
                        }
                        try {
							buildcfg.save(buildf);
						} catch (IOException e) {
							System.out.println(Kreiscraft.prefix + "§cError BuildFile!");
						}
                        p.getInventory().clear();
                        Navigator.givenavigator(p);
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(String.valueOf(Kreiscraft.lobbyprefix) + "§cDu kannst nun nicht mehr bauen!");
                    }
                    else {
                        p.sendMessage(Kreiscraft.lobbynoperms);
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Kreiscraft.lobbyprefix) + "§4Bitte benutze §6/build <Player>§4!");
                }
            }
            else {
                p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§4Du bist nicht im Hub!");
            }
        }
        else {
            sender.sendMessage(Kreiscraft.lobbynocons);
        }
        return false;
    }
}
