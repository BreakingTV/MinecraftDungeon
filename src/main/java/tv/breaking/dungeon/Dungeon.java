package tv.breaking.dungeon;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import tv.breaking.dungeon.commands.Leave;
import tv.breaking.dungeon.listeners.Regular;
import tv.breaking.dungeon.magic.WaterMagic;
import tv.breaking.dungeon.map.utils.world;
import tv.breaking.dungeon.commands.Ping;
import tv.breaking.dungeon.commands.Reload;
import tv.breaking.dungeon.listeners.Chat;
import tv.breaking.dungeon.listeners.Join;
import tv.breaking.dungeon.map.tutorial.Tutorial;
import tv.breaking.dungeon.utils.Config;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public final class Dungeon extends JavaPlugin implements PluginMessageListener {

    private static Dungeon instance;
    private static Config config;

    @Override
    public void onEnable() {
        instance = this;
        config = new Config();
        commands();
        listeners();



        //Bungee part
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        // load worlds
        Bukkit.createWorld(WorldCreator.name("tutorial"));
    }
    @Override
    public void onDisable() {
        config.save();
    }


    private void listeners() {
        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new Join(), this);
        pl.registerEvents(new Chat(), this);
        pl.registerEvents(new Tutorial(), this);
        pl.registerEvents(new Reload(), this);
        pl.registerEvents(new WaterMagic(), this);
        pl.registerEvents(new Regular(), this);
    }

    private void commands() {
        getCommand("rl").setExecutor(new Reload());
        getCommand("leave").setExecutor(new Leave());
        getCommand("l").setExecutor(new Leave());
        getCommand("ping").setExecutor(new Ping());
        getCommand("world").setExecutor(new world());
        getCommand("starttutorial").setExecutor(new Tutorial());
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

    }

    public static Dungeon getInstance() {
        return instance;
    }

    public Config getConfiguration() {
        return config;
    }

    public void connectServer(Player player, String server) {

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException eee) {
            Bukkit.getLogger().info("You'll never see me!");
        }
        Objects.requireNonNull(Bukkit.getPlayer(player.getName())).sendPluginMessage(getInstance(), "BungeeCord", b.toByteArray());
    }
}
