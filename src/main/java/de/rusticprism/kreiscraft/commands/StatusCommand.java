package de.rusticprism.kreiscraft.commands;

import de.rusticprism.kreiscraft.utils.Status;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StatusCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length >= 1) {
                switch (args[0]) {
                    case "prefix": {
                        StringBuilder prefix = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            prefix.append(" ").append(args[i]);
                        }
                        Status.setPrefix(player, prefix.toString());
                        Status.setTab(player);
                        break;
                    }
                    case "suffix": {
                        StringBuilder suffix = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            suffix.append(" ").append(args[i]);
                        }

                        Status.setSuffix(player, suffix.toString());
                        Status.setTab(player);
                        break;
                    }
                    case "color": {
                        try {
                            ChatColor.valueOf(args[1].toUpperCase());
                        } catch (IllegalArgumentException e) {
                            player.sendMessage(Kreiscraft.prefix + "§cArgument 2 ist keine Farbe!");
                            return false;
                        }
                        Status.setColor(player, args[1].toUpperCase());
                        Status.setTab(player);
                        break;
                    }
                    default: {
                        player.sendMessage(Kreiscraft.prefix + "§cUsage: /status -> prefix/suffix/color <- !");
                    }
                }
            }else player.sendMessage(Kreiscraft.prefix + "§cUsage: /status ->prefix/suffix/color <- !");
        }else sender.sendMessage(Kreiscraft.nocons);
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> tab = new ArrayList<>();
        if(args.length == 1) {
            tab.add("prefix");
            tab.add("suffix");
            tab.add("color");
        }else if(args.length == 2 && args[0].equalsIgnoreCase("color")) {
            tab.add("Black");
            tab.add("Dark_Blue");
            tab.add("Dark_Green");
            tab.add("Dark_Aqua");
            tab.add("Dark_Red");
            tab.add("Dark_Purple");
            tab.add("Gold");
            tab.add("Gray");
            tab.add("Dark_Gray");
            tab.add("Blue");
            tab.add("Green");
            tab.add("Aqua");
            tab.add("Red");
            tab.add("Light_Purple");
            tab.add("Yellow");
            tab.add("White");
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
