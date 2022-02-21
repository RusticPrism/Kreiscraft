package de.rusticprism.kreiscraft.lobby.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.rusticprism.kreiscraft.commands.Kreiscraft;

public class RulesCommand implements CommandExecutor, TabCompleter{
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		ArrayList<String> list = new ArrayList<>();
		if(args.length == 0) {
		}
		if(args.length == 1) {
			list.add("set");
		}
		if(args.length >= 2) {
			list.add("<Rules>");
		}
		ArrayList<String> list2 = new ArrayList<String>();
		String currentare = args[args.length - 1].toLowerCase();
		for (final String s : list) {
            final String s2 = s.toLowerCase();
            if (s2.startsWith(currentare)) {
                list2.add(s);
            }
        }
		return list2;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 final File rulesf = new File(Kreiscraft.getInstance().getDataFolder().getPath(), "data/rules.yml");
         if (!rulesf.exists()) {
             try {
            	 rulesf.createNewFile();
             }
             catch (IOException ext) {
                 ext.printStackTrace();
             }
         }
         final FileConfiguration cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(rulesf);
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				String erklärung = cfg.getString("Rules");
				erklärung = erklärung.replaceAll("&", "§");
				p.sendMessage(erklärung);
			}else if(args.length >= 2) {
				if(args[0].equalsIgnoreCase("set")) {
					String erklärung = "";
					for(int i = 1; i < args.length; i++) {
						erklärung = erklärung + " " + args[i];
					}
					cfg.set("Rules", erklärung);
					p.sendMessage(Kreiscraft.prefix + "§aDie Regeln sind nun: §b" + erklärung);
					try {
						cfg.save(rulesf);
					} catch (IOException e) {
						p.sendMessage(Kreiscraft.prefix + "§4There was an §lError §r§4trying to save the file 'Rules'!");
					}
				}else p.sendMessage(Kreiscraft.prefix + "§cUsage: /rules set <Rules>!");
			}else p.sendMessage(Kreiscraft.prefix + "§cUsage: /rules");
		}else sender.sendMessage(Kreiscraft.nocons);
		return false;
	}

}
