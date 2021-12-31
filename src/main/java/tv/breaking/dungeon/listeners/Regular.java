package tv.breaking.dungeon.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Regular implements Listener {

    @EventHandler
    public void onMovingBlock(BlockFromToEvent e) {
        e.setCancelled(e.getBlock().getType().equals(Material.WATER));
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) { e.setFoodLevel(20); }
}
