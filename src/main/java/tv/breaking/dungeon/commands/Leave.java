package tv.breaking.dungeon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import tv.breaking.dungeon.Dungeon;

public class Leave implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Dungeon.getInstance().connectServer(player,"lobby"); // only if you have a Bungee Server
        }
        return false;
    }
}
