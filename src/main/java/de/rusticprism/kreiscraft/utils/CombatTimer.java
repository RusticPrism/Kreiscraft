package de.rusticprism.kreiscraft.utils;

import de.rusticprism.kreiscraft.commands.Kreiscraft;
import de.rusticprism.kreiscraft.listener.Combat;
import de.rusticprism.kreiscraft.listener.Navigator;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class CombatTimer {

    public CombatTimer(Plugin plugin) {

    Bukkit.getScheduler().runTaskTimer(plugin,() ->{
                for (User user : Kreiscraft.USERS.values()) {
                    if (user.getPlayer().getWorld().getName() == Navigator.worldname) return;
                    if (user.isinCombat()) {
                        user.setCombattime(10);
                        Combat.sendactionbar(user, user.getCombattime());
                        user.setCombat(false);
                    } else if (user.getCombattime() > 0) {
                     user.setCombattime(user.getCombattime() - 1);
                     de.rusticprism.kreiscraft.listener.Combat.sendactionbar(user, user.getCombattime());
                    } else if (user.getCombattime() == 0) {
                     user.setCombattime(-10);
                     user.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aOut of Combat"));
                    }
                }
            },0,20);
        }
    }
