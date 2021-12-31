package tv.breaking.dungeon.map.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class world implements CommandExecutor {

    private static final String[] options = {"load", "unload", "teleport"};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args[0].equalsIgnoreCase(options[0])) {
                Bukkit.createWorld(WorldCreator.name(args[1]));
                sender.sendMessage("§aYou created successfully a World!");
            } else if (args[0].equalsIgnoreCase(options[1])) {

                try {
                    Bukkit.unloadWorld(args[1], true);
                } catch (Exception e) {
                    sender.sendMessage("This World does not exist!");
                    return false;
                }

                sender.sendMessage("§UnLoaded the World successful!\nThe World file is still saved in the Server Folder!");
            } else if (args[0].equalsIgnoreCase(options[2])) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    World world = Bukkit.getWorld(args[1]);

                    try {
                        player.teleport(world.getSpawnLocation());
                    } catch (NullPointerException e) {
                        player.sendMessage("§cThis World does not exist\nplease try using load to load a new world!");
                        return false;
                    }

                    player.sendMessage("§aYou teleported successfully to the World");
                } else sender.sendMessage("§aYou need to be a player to teleport!");
            }
        }
        return false;
    }
}
