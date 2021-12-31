package tv.breaking.dungeon.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import tv.breaking.dungeon.Dungeon;

public class Reload implements CommandExecutor, Listener {

    private int taskID;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.isOp()) {
            if (args.length == 0) {
                taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Dungeon.getInstance(), new Runnable() {
                    int countdown = 5;

                    @Override
                    public void run() {
                        if (countdown <= 0) {
                            Bukkit.broadcastMessage("§7§lDer Server Reloaded!");
                            Bukkit.reload();
                            Bukkit.getScheduler().cancelTask(taskID);
                            return;
                        }
                        Bukkit.broadcastMessage("§7Der Server Reloaded in: §7§l" + countdown);
                        countdown--;
                    }
                }, 0, 20);
            } else if (args[0].equalsIgnoreCase("force")) {
                     Bukkit.broadcastMessage("§7§lDer Server Reloaded!");
                     Bukkit.reload();
            } else sender.sendMessage("§cPlease use:§l /rl §r§cor §l/reload");
        }
        return false;
    }

    @EventHandler
    public void onReload(ServerLoadEvent e) {
        for(Player p : Dungeon.getInstance().getServer().getOnlinePlayers()) {
            p.setWalkSpeed(0.2F);
            p.setFlySpeed(0.2F);
            p.setInvisible(false);
            p.setHealthScale(20);
            if (p.getGameMode().equals(GameMode.ADVENTURE)) {
                p.setAllowFlight(false);
                p.setFlying(false);
            }
        }
    }
}
