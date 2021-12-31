package tv.breaking.dungeon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import tv.breaking.dungeon.Dungeon;
import tv.breaking.dungeon.utils.Config;

public class Chat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setFormat("%1$s §7≫§r %2$s");
    }
}
