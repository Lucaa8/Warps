package ch.luca008;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player sender) {
            if(args.length == 0) {
                sender.sendMessage("§6Utilisation: §f/warp <nom> (faire /warps pour avoir la liste des noms)");
                return true;
            }
            Optional<Warp> warp = Main.getInstance().findWarp(args[0]);
            if(warp.isPresent()) {
                Warp w = warp.get();
                if(w.isValid()) {
                    w.teleport(sender);
                    sender.sendMessage("§aTu as été téléporté au warp §b" + warp.get().getName());
                    return true;
                } else {
                    sender.sendMessage("§cCe warp est invalide. Le monde sur lequel il a été créé existe toujours ?");
                }
            } else {
                sender.sendMessage("§cWarp introuvable. Trouve la liste des warps disponibles avec la commande /warps");
            }
            return false;
        }
        return false;
    }
}
