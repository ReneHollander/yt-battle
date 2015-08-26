package at.er.ytbattle.plugin.event;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.util.BattleUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    public PlayerJoinListener() {
        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());
        BattleUtils.setDisplayAndListName(player);
        BattleUtils.addToScoreboard(player);
        BattleUtils.updateScoreboard();
    }

}
