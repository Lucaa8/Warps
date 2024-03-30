package ch.luca008;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class UnsetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player sender) {
            if (args.length == 0) {
                sender.sendMessage("§6Utilisation: §f/unsetwarp <nom>");
                return true;
            }
            Optional<Warp> warp = Main.getInstance().findWarp(args[0]);
            if (warp.isPresent()) {
                Main.getInstance().deleteWarp(warp.get());
                sender.sendMessage("§aTu as supprimé le warp §b" + warp.get().getName() + " §aavec succès!");
                return true;
            } else {
                sender.sendMessage("§cWarp introuvable. Trouve la liste des warps disponibles avec la commande /warps");
                return false;
            }
        }
        return false;
    }
}
