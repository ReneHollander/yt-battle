package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.player.BattlePlayer;

public class PlayerChatListener implements Listener {

    public PlayerChatListener() {
        Bukkit.getPluginManager().registerEvents(this, Battle.instance());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        BattlePlayer player = Battle.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());
        if (Battle.game().isStarted()) {
            if (Battle.game().getTeamManager().isInTeam(player)) {
                Team t = Battle.game().getTeamManager().getTeamByPlayer(player);
                if (Battle.game().getTeamManager().isLastTeam(t)) {
                    event.setFormat(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + "%1$s: " + ChatColor.RESET + "%2$s");
                } else {
                    event.setFormat(t.getTeamColor().getChatColor() + "[Battle]" + ChatColor.WHITE + " - " + "%1$s: " + ChatColor.RESET + "%2$s");
                }
            }
        }
    }

}
