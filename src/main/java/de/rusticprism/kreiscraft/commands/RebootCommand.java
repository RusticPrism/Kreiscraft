package de.rusticprism.kreiscraft.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.rusticprism.kreiscraft.listener.Reboot;

public class RebootCommand implements CommandExecutor
{
    public static ArrayList<String> reason = new ArrayList<>();
    public static ArrayList<String> send = new ArrayList<>();

    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender.hasPermission("kreiscraft.command.reboot")) {
            if (args.length >= 1) {
                final Reboot timer = Kreiscraft.getInstance().getReboot();
                if(timer.isReboot()) {
                    sender.sendMessage(Kreiscraft.prefix + "§cDer Server startet schon neu");
                   return false;
                }
                try { Integer.parseInt(args[0]);
                }catch (NumberFormatException e) {
                    sender.sendMessage(Kreiscraft.prefix + "§c Argument 1 muss eine Zahl sein!");
                    return false;
                }

                timer.setTime(Integer.parseInt(args[0]));

                for(String var : args) {
                        RebootCommand.reason.add(var);
                    }
                        send.add(sender.getName());
                    timer.setReboot(true);
                }
            else {
                sender.sendMessage("§b[Kreiscraft]§4Bitte benutze /reboot <Grund>");
            }
        }
        else {
            sender.sendMessage(Kreiscraft.noperms);
        }
        return false;
    }
}
