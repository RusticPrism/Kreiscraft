// 
// Decompiled by Procyon v0.5.36
// 

package de.rusticprism.kreiscraft.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rusticprism.kreiscraft.commands.Kreiscraft;

public class DiscordCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            p.sendMessage(String.valueOf(Kreiscraft.prefix) + "§bUnser Discord: §ddisocrd.link/kreiscraft");
        }
        return false;
    }
}
