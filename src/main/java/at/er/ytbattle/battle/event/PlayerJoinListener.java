package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;

public class PlayerJoinListener implements Listener {

    public PlayerJoinListener() {
        Bukkit.getPluginManager().registerEvents(this, Battle.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        BattlePlayer player = Battle.instance().getGame().getBattlePlayerManager().getBattlePlayer(event.getPlayer());
        Battle.instance().setDisplayAndListName(player);
        Battle.instance().addToScoreboard(player);
        Battle.instance().updateScoreboard();
    }

}
