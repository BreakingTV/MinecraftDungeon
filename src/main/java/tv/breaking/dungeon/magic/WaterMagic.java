package tv.breaking.dungeon.magic;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import tv.breaking.dungeon.Dungeon;
import tv.breaking.dungeon.map.tutorial.Tutorial;

import java.util.ArrayList;
import java.util.List;

public class WaterMagic implements Listener {

    public List<String> PlayerList = Tutorial.getPlayerList();
    public List<String> SneakList = new ArrayList<>();
    Material HandItem;

    private int taskID;
    private int magicID;

    public void activate(Player player) {
        if (!PlayerList.contains(player.getUniqueId().toString())) {
            magicID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Dungeon.getInstance(), () -> {
                double radius = 1;
                for (double t = 0; t <= 2 * Math.PI * radius; t += 0.05) {
                    if (player.isOnline()) {
                        if (!player.isDead()) {
                            double x = (radius * Math.cos(t)) + player.getLocation().getX();
                            double z = player.getLocation().getZ() + radius * Math.sin(t);
                            Location particle = new Location(player.getWorld(), x, player.getLocation().getY() + 0.2, z);
                            player.getWorld().spawnParticle(Particle.WATER_BUBBLE, particle, 1);

                            if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                                if (player.getLocation().getBlock().getType().equals(Material.WATER)) {
                                    player.setAllowFlight(true);
                                    player.setFlying(true);
                                    player.setFlySpeed(0.2F);
                                } else {
                                    player.setAllowFlight(false);
                                    player.setFlying(false);
                                    player.setFlySpeed(0.2F);
                                }
                            }
                        } else Bukkit.getScheduler().cancelTask(magicID);
                    } else Bukkit.getScheduler().cancelTask(magicID);
                }
            }, 0L, 0);
            PlayerList.add(player.getUniqueId().toString());
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        if (PlayerList.contains(player.getUniqueId().toString())) {
            if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                if (player.getLocation().getBlock().getType().equals(Material.AIR) || player.getLocation().getBlock().getType().equals(Material.WATER)) {
                    if (e.isSneaking()) {
                        player.setInvisible(true);
                        HandItem = player.getInventory().getItemInMainHand().getType();
                        player.getInventory().setHeldItemSlot(2);
                        Bukkit.getWorld("tutorial").getBlockAt(player.getLocation()).setType(Material.WATER);
                        SneakList.add(player.getUniqueId().toString());

                        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Dungeon.getInstance(), () -> {
                            if (!(player.getHealth() >= player.getHealthScale() - 0.9)) {
                                player.setHealth(player.getHealth() + 1);
                            }
                        }, 10, 10);

                    } else {
                        player.setInvisible(false);
                        Bukkit.getWorld("tutorial").getBlockAt(player.getLocation().getBlock().getLocation()).setType(Material.AIR);
                        SneakList.remove(player.getUniqueId().toString());
                        Bukkit.getScheduler().cancelTask(taskID);
                        player.getInventory().setHeldItemSlot(0);
                    }
                } else if (!player.isSneaking()) player.sendMessage("§cYou cant do this on this Block!");
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (e.getTo().getX() != e.getFrom().getX() || e.getTo().getZ() != e.getFrom().getZ() || e.getTo().getY() != e.getFrom().getY()) e.setCancelled(SneakList.contains(player.getUniqueId().toString()));
    }
}
