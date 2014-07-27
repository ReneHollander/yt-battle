package at.er.ytbattle.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

import at.er.ytbattle.plugin.BattlePlugin;

public class PlayerShearListener implements Listener {

    public PlayerShearListener() {
        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerShear(PlayerShearEntityEvent event) {
        event.setCancelled(true);
    }

}
