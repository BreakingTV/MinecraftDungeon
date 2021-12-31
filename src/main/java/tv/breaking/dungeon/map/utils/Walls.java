package tv.breaking.dungeon.map.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class Walls {

    public Walls() {

    }

    public void add(Location location, Material block, boolean x) {
        World world = location.getWorld(); // Using Player World to get the Level you are playing right now

        world.getBlockAt(location).setType(block);
        world.getBlockAt(new Location(world, location.getX(), location.getY() + 1, location.getZ())).setType(block);
        world.getBlockAt(new Location(world, location.getX(), location.getY() - 1, location.getZ())).setType(block);
        if (x) {
            world.getBlockAt(new Location(world, location.getX() + 1, location.getY(), location.getZ())).setType(block);
            world.getBlockAt(new Location(world, location.getX() - 1, location.getY(), location.getZ())).setType(block);
            world.getBlockAt(new Location(world, location.getX() + 1, location.getY() + 1, location.getZ())).setType(block);
            world.getBlockAt(new Location(world, location.getX() - 1, location.getY() + 1, location.getZ())).setType(block);
            world.getBlockAt(new Location(world, location.getX() + 1, location.getY() - 1, location.getZ())).setType(block);
            world.getBlockAt(new Location(world, location.getX() - 1, location.getY() - 1, location.getZ())).setType(block);
        } else {
            world.getBlockAt(new Location(world, location.getX(), location.getY(), location.getZ() + 1)).setType(block);
            world.getBlockAt(new Location(world, location.getX(), location.getY(), location.getZ() - 1)).setType(block);
            world.getBlockAt(new Location(world, location.getX(), location.getY() + 1, location.getZ() + 1)).setType(block);
            world.getBlockAt(new Location(world, location.getX(), location.getY() + 1, location.getZ() - 1)).setType(block);
            world.getBlockAt(new Location(world, location.getX(), location.getY() - 1, location.getZ() + 1)).setType(block);
            world.getBlockAt(new Location(world, location.getX(), location.getY() - 1, location.getZ() - 1)).setType(block);
        }
    }

    public void remove(Location location, boolean x) {
        World world = location.getWorld(); // Using Player World to get the Level you are playing right now

        world.getBlockAt(location).setType(Material.AIR);
        world.getBlockAt(new Location(world, location.getX(), location.getY() + 1, location.getZ())).setType(Material.AIR);
        world.getBlockAt(new Location(world, location.getX(), location.getY() - 1, location.getZ())).setType(Material.AIR);
        if (x) {
            world.getBlockAt(new Location(world, location.getX() + 1, location.getY(), location.getZ())).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX() - 1, location.getY(), location.getZ())).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX() + 1, location.getY() + 1, location.getZ())).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX() - 1, location.getY() + 1, location.getZ())).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX() + 1, location.getY() - 1, location.getZ())).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX() - 1, location.getY() - 1, location.getZ())).setType(Material.AIR);
        } else {
            world.getBlockAt(new Location(world, location.getX(), location.getY(), location.getZ() + 1)).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX(), location.getY(), location.getZ() - 1)).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX(), location.getY() + 1, location.getZ() + 1)).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX(), location.getY() + 1, location.getZ() - 1)).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX(), location.getY() - 1, location.getZ() + 1)).setType(Material.AIR);
            world.getBlockAt(new Location(world, location.getX(), location.getY() - 1, location.getZ() - 1)).setType(Material.AIR);
        }
    }
}
