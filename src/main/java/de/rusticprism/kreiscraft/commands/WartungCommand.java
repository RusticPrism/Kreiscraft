package de.rusticprism.kreiscraft.commands;

import de.rusticprism.kreiscraft.utils.WartungsModus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WartungCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(sender.hasPermission("kreiscraft.command.wartung")) {
                    switch (args[0].toLowerCase()) {
                        case "an" : {
                            WartungsModus.an();
                            WartungsModus.kickall(sender);
                            sender.sendMessage(Kreiscraft.prefix + " §eWartungsmodus ist nun an!");
                            break;
                        }
                        case "aus" : {
                            WartungsModus.aus();
                            sender.sendMessage(Kreiscraft.prefix + " §eWartungsmodus ist nun aus!");
                            break;
                        }
                        case "message" : {
                            String message = "";
                            for(int i = 1; i <args.length; i++) {
                                message = message + " " +args[i];
                            }
                            WartungsModus.setWartungsMessage(message);
                            sender.sendMessage(Kreiscraft.prefix + "§eWartungsmodus-message ist nun§a'" + message + "' !");
                            break;
                        }
                        default: {
                            sender.sendMessage(Kreiscraft.prefix + "§cUsage: /wartung an/aus!");
                            break;
                        }
                    }
            }else sender.sendMessage(Kreiscraft.noperms);
        return false;
    }
}
