package de.rusticprism.kreiscraft.utils;

import de.rusticprism.kreiscraft.listener.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Status {
    public static Object getColor(Player player) {return StatusConfig.get(player.getUniqueId()+ ".color");}
    public static String getPrefix(Player player) {
        if(StatusConfig.get(player.getUniqueId() + ".prefix") == null) {
            return null;
        }else return ChatColor.translateAlternateColorCodes('&', (String) StatusConfig.get(player.getUniqueId() + ".prefix"));}
    public static Object getStatus(Player player) {
        return StatusConfig.get(player.getUniqueId() + ".name");
    }
    public static Object getSuffix(Player player) {
        if(StatusConfig.get(player.getUniqueId() + ".suffix") == null) {
            return null;
        }else return ChatColor.translateAlternateColorCodes('&', (String) StatusConfig.get(player.getUniqueId() + ".suffix"));
    }
    public static void setPrefix(Player player,String value) {
        StatusConfig.set(player.getUniqueId() + ".prefix",value + "§7 | ");

    }
    public static void setSuffix(Player player,String value) {
        StatusConfig.set(player.getUniqueId() + ".suffix", " " + value);
    }
    public static void setColor(Player player,String color) {
        StatusConfig.set(player.getUniqueId() + ".color", color);
    }
    public static void setStatus(Player player,String value) {
        StatusConfig.set(player.getUniqueId() + ".name", value);
    }
    public static void setTab(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
           if(StatusConfig.get("amount") == null) {
               StatusConfig.set("amount", "0");
           }
           if(getStatus(player) == null) {
               int i = Integer.parseInt(String.valueOf(StatusConfig.get("amount")));
               StatusConfig.set("amount", String.valueOf(i + 1));
               setStatus(player, String.valueOf(StatusConfig.get("amount")));
           }
               Team team = scoreboard.getTeam(String.valueOf(getStatus(player)));
               if (team == null) {
                   team = scoreboard.registerNewTeam(String.valueOf(getStatus(player)));
               }


        if(getColor(player) != null) {
           team.setColor(ChatColor.valueOf((String) getColor(player)));
        }else setColor(player,"GRAY");
        team.setColor(ChatColor.valueOf((String) getColor(player)));

        try {
            if (getPrefix(player) != null) {
                team.setPrefix(getPrefix(player));
            } else setPrefix(player, "Player");
            team.setPrefix(getPrefix(player));
        }catch (IllegalArgumentException e) {
            setPrefix(player, "ERROR");
            team.setPrefix("ERROR");
        }

        try {
            if (getSuffix(player) != null) {
                team.setSuffix((String) getSuffix(player));
            } else setSuffix(player, " ");
            team.setSuffix((String) getSuffix(player));
        }catch (IllegalArgumentException e) {
            setSuffix(player, "ERROR");
            team.setSuffix("ERROR");
        }


        team.addEntry(player.getName());
    }
    public static void setTabforAll() {
        Bukkit.getOnlinePlayers().forEach(player -> setTab(player));
        Bukkit.getOnlinePlayers().forEach(player -> Tablist.setTablist(player));
        Bukkit.getOnlinePlayers().forEach(player -> Tablist.updateDate(player));
    }
}
