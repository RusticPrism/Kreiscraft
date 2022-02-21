package de.rusticprism.kreiscraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PortalCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 3) {
                switch (args[0].toLowerCase()) {
                    case "nether": {
                        try {
                            Integer.parseInt(args[1]);
                            Integer.parseInt(args[2]);
                        }catch (NumberFormatException e) {
                            player.sendMessage(Kreiscraft.prefix + "§cArgument 2 oder 3 muss eine Zahl sein!");
                            return false;
                        }
                        int i = Integer.parseInt(args[1]);
                        int i1 = Integer.parseInt(args[2]);
                        player.sendMessage(Kreiscraft.prefix + "§bX im Nether: §a" + i/8 + " §bZ im Nether: §a" + i1/8);
                        break;
                    }
                    case "overworld": {
                        try {
                            Integer.parseInt(args[1]);
                            Integer.parseInt(args[2]);
                        }catch (NumberFormatException e) {
                            player.sendMessage(Kreiscraft.prefix + "§cArgument 2 oder 3 muss eine Zahl sein!");
                            return false;
                        }
                        int i = Integer.parseInt(args[1]);
                        int i1 = Integer.parseInt(args[2]);
                        player.sendMessage(Kreiscraft.prefix + "§bX in der Overworld: §a" + i*8 + " §bZ in der Overworld: §a" + i1*8);
                        break;
                    }
                    default: {
                        player.sendMessage(Kreiscraft.prefix + "§cUsage: /portal nether/overworld x y!");
                        break;
                    }
                }
            }else if(args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "nether": {
                        if(player.getWorld().getEnvironment().getId() == 0) {
                            player.sendMessage(Kreiscraft.prefix + "§bX im Nether: §a" + player.getLocation().getBlockX() / 8 + " §bZ im Nether: §a" + player.getLocation().getBlockZ() / 8);
                        }else player.sendMessage(Kreiscraft.prefix + "§cDu befindest dich nicht in der Overworld!");
                        break;
                    }
                    case "overworld": {
                        if(player.getWorld().getEnvironment().getId() == -1) {
                            player.sendMessage(Kreiscraft.prefix + "§bX in der Overworld: §a" + player.getLocation().getBlockX() * 8 + " §bZ in der Overworld: §a" + player.getLocation().getBlockZ() * 8);
                        }else player.sendMessage(Kreiscraft.prefix + "§cDu befindest dich nicht im Nether!");
                        break;
                    }
                    default: {
                        player.sendMessage(Kreiscraft.prefix + "§cUsage: /portal nether/overworld!");
                        break;
                    }
                }
            }else player.sendMessage(Kreiscraft.prefix + "§cUsage /portal nether/overworld X Z");
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> tab = new ArrayList<>();
        if(args.length == 1) {
            tab.add("nether");
            tab.add("overworld");
        }
        ArrayList<String> cl = new ArrayList<>();
        String current = args[args.length -1];
        for( String s : tab) {
            String s1 = s.toLowerCase();
            if(s1.startsWith(current)|| s.startsWith(current)) {
                cl.add(s);
            }
        }
        return cl;
    }
}
