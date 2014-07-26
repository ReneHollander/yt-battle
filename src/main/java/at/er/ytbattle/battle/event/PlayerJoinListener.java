package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.util.BattleUtils;

public class PlayerJoinListener implements Listener {

    public PlayerJoinListener() {
        Bukkit.getPluginManager().registerEvents(this, Battle.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        BattlePlayer player = Battle.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());
        BattleUtils.setDisplayAndListName(player);
        BattleUtils.addToScoreboard(player);
        BattleUtils.updateScoreboard();
    }

}
