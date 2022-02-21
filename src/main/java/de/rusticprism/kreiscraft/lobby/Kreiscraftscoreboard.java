package de.rusticprism.kreiscraft.lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;


import org.bukkit.entity.Player;

public class Kreiscraftscoreboard extends ScoreboardBuilder
{
    private int socialId;
    public Kreiscraftscoreboard(Player player) {
        super(player, "§7\u25a1 §b§lKreiscraft §7\u25a1");
        socialId = 0;
    }
    public static String getrank(final Player p) {
     if(p.isOp()) {
         return "§4Operator";
     }else if(p.hasPermission("rank.owner")) {
         return "§4Owner";
     }else if(p.hasPermission("rank.dev")) {
         return "§bDeveloper";
     }else if(p.hasPermission("rank.builder")) {
         return "§bBuilder";
     }else if(p.hasPermission("rank.mvp")) {
         return "§6[MVP§c◯◯§6]";
     }else return "§7Player";


             }
    @Override
    public void createScoreboard() {
        setDisplayName("§7\u25a1 §b§lKreiscraft §7\u25a1");
        setScore("&6Willkommen §b" + player.getName(),9);
        setScore("  ",8);
        setScore("§7\u25a1 Dein Rang:",7);
        setScore("§7 ● " + getrank(player),6);
        setScore("    ",5);
        setScore("§7\u25a1 §6§lGeld: ",4);
        setScore("§e ● Tasche: Geld",3);
        setScore("§e ● Bank: Geld",2);
        setScore("    ",1);
        setScore("§7\u25a1 §bkreiscraft.tk §7\u25a1",0);
        player.setScoreboard(scoreboard);
        setTablistRank(player);
    }



    @Override
    public void update() {

    }
    public void setTablistRank(final Player p) {
        Team players = scoreboard.getTeam("011players");
        if (players == null) {
            players = scoreboard.registerNewTeam("011players");
        }
        Team mvps = scoreboard.getTeam("010mpvs");
        if (mvps == null) {
            mvps = scoreboard.registerNewTeam("010mpvs");
        }
        Team operatores = scoreboard.getTeam("001operatores");
        if (operatores == null) {
            operatores = scoreboard.registerNewTeam("001operatores");
        }
        Team developers = scoreboard.getTeam("003developers");
        if (developers == null) {
            developers = scoreboard.registerNewTeam("003developers");
        }
        Team builders = scoreboard.getTeam("004builders");
        if (builders == null) {
            builders = scoreboard.registerNewTeam("004builders");
        }
        Team owners = scoreboard.getTeam("002owners");
        if (owners == null) {
            owners = scoreboard.registerNewTeam("002owners");
        }
        players.setPrefix("§7Player | ");
        players.setColor(ChatColor.GRAY);
        mvps.setPrefix("§6[MVP§c◯◯§6] §7| ");
        mvps.setColor(ChatColor.GOLD);
        operatores.setPrefix("§4Operator §7| ");
        operatores.setColor(ChatColor.DARK_RED);
        developers.setPrefix("§bDeveloper §7| ");
        developers.setColor(ChatColor.AQUA);
        builders.setPrefix("§bBuilder §7| ");
        builders.setColor(ChatColor.AQUA);
        owners.setPrefix("§4Owner §7| ");
        owners.setColor(ChatColor.DARK_RED);
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (all.isOp()) {
                operatores.addEntry(all.getName());
            }
            else if (all.hasPermission("rank.owner")) {
                owners.addEntry(all.getName());
            }
            else if (all.hasPermission("rank.dev")) {
                developers.addEntry(all.getName());
            }
            else if (all.hasPermission("rank.builder")) {
                builders.addEntry(all.getName());
            }else if(all.hasPermission("rank.mvp")) {
                mvps.addEntry(all.getName());
            }
            else {
                players.addEntry(all.getName());
            }
        }
    }
}
