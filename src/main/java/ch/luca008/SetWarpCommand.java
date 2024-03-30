package ch.luca008;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player sender) {
            if(args.length == 0) {
                sender.sendMessage("§6Utilisation: §f/setwarp <nom> [<description>]");
                return true;
            }
            String name = args[0];
            Location loc = sender.getLocation();
            String description = null;
            if(args.length > 1)
            {
                description = "";
                for(int i=1;i<args.length;i++)
                {
                    description += args[i]+" ";
                }
            }
            Warp w = new Warp(loc, name, description, sender);
            if(Main.getInstance().createWarp(w)) {
                sender.sendMessage("§aWarp §b"+w.getName()+" §acréé avec succès!");
                return true;
            } else {
                sender.sendMessage("§cImpossible de créer le warp " + w.getName() + "! Ce warp existe-il déjà?");
                return false;
            }
        }
        return false;
    }
}
