package ch.luca008;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player sender) {
            sender.sendMessage("§6--- Liste des warps existants ---");
            if(Main.getInstance().getWarpList().size()==0)
                sender.sendMessage("§cAucun warp! :(");
            Main.getInstance().getWarpList().forEach(w->{
                if(w.isValid())
                    sender.sendMessage(w.toString());
            });
            sender.sendMessage("§6--- Liste des commandes ---");
            sender.sendMessage("§f- §6/warp <nom>§f : Permet de se téléporter à un warp");
            sender.sendMessage("§f- §6/setwarp <nom> [<description>]§f : Permet de créer un warp à votre position");
            sender.sendMessage("§f- §6/unsetwarp <nom>§f : Permet de supprimer définitivement le warp associé au nom");
            sender.sendMessage("§6------");
            return true;
        }
        return false;
    }
}