package tv.breaking.dungeon.map.tutorial;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tv.breaking.dungeon.magic.WaterMagic;
import tv.breaking.dungeon.map.utils.Walls;

import java.util.ArrayList;
import java.util.List;

public class Tutorial implements Listener, CommandExecutor {

    public static List<String> PlayerList = new ArrayList<>();
    public static List<String> PlayingTutorial = new ArrayList<>();
    private boolean enteredRegionWater = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
                PlayingTutorial.add(player.getUniqueId().toString());
                player.teleport(Bukkit.getWorld("tutorial").getSpawnLocation());
                player.sendTitle("§aWelcome to your Adventure!", "§2You can choose where to go!");

            // Magic part line: 55
        } else sender.sendMessage("§cYou need to be a player to execute this command!");
        return false;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType().equals(Material.LECTERN)) {
                if (!PlayerList.contains(player.getUniqueId().toString())) {
                    new WaterMagic().activate(player);
                    PlayerList.add(player.getUniqueId().toString());
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (PlayingTutorial.contains(player.getUniqueId().toString())) {
            // magic Part
            if ((e.getTo().getBlockX() == 285 && e.getTo().getBlockY() == 140 && e.getTo().getBlockZ() == 29) || (e.getTo().getBlockX() == 285 && e.getTo().getBlockY() == 140 && e.getTo().getBlockZ() == 30) || (e.getTo().getBlockX() == 285 && e.getTo().getBlockY() == 140 && e.getTo().getBlockZ() == 28)) {
                if (!enteredRegionWater) {
                    new Walls().add(new Location(player.getWorld(), 284, 141, 29), Material.RED_STAINED_GLASS, false);
                    player.sendTitle("§9Water", "The Path of the Healer");
                    enteredRegionWater = true;
                }
            }
        }
        }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        e.getDrops().clear();
    }

    public static List<String> getPlayerList() {
        return PlayerList;
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (PlayingTutorial.contains(player.getUniqueId().toString())) {
            new Walls().remove(new Location(player.getWorld(), 284, 140, 29), false);
        }
    }
}
