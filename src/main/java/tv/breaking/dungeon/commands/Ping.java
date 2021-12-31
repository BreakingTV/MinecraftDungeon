package tv.breaking.dungeon.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("§7Your Ping is §8§l" + player.getPing());
            } else sender.sendMessage("§7Your Ping is §8§l 0");
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                sender.sendMessage("§7The ping of §8§l" + target.getName() + " §r§7is §8§l" + target.getPing());
            } else sender.sendMessage("§cThe Player §l" + args[0] + " §r§cis not Online!");
        } else sender.sendMessage("§cPlease use: §l/ping (player)");

        return false;
    }
}
