package tv.breaking.dungeon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tv.breaking.dungeon.Dungeon;
import tv.breaking.dungeon.utils.Config;

public class Join implements Listener {

    private static final Location spawn = new Location(Bukkit.getWorld("world"), 107.991, 14, -27.011);
    Config config = Dungeon.getInstance().getConfiguration();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (!config.getConfig().contains(e.getPlayer().getUniqueId() + ".level")) {
            config.getConfig().set(player.getUniqueId() + ".level", 0);
        }

        int level = config.getConfig().getInt(e.getPlayer().getUniqueId() + ".level");

        player.setDisplayName("§8[§7" + level + "§8]§7 " + player.getName());
        player.setPlayerListName("§8[§7" + level + "§8]§7 " + player.getName());

        e.setJoinMessage("§8§l" + player.getDisplayName() + "§r§7 ist beigetreten");
        player.teleport(spawn);

        player.setInvisible(false);
    }
}
