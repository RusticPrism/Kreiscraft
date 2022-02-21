package de.rusticprism.kreiscraft.listener;

import de.rusticprism.kreiscraft.commands.Kreiscraft;
import de.rusticprism.kreiscraft.utils.User;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Combat implements Listener {
    @EventHandler
    public void onCombat(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            User user = Kreiscraft.USERS.get(e.getEntity().getUniqueId());
            User user1 = Kreiscraft.USERS.get(e.getDamager().getUniqueId());
            user.setCombat(true);
            user1.setCombat(true);
        }
    }
    public static void sendactionbar(User user, int time) {
        Player player = user.getPlayer();
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aIn Combat: §b" + time + "§a!"));
    }
}
