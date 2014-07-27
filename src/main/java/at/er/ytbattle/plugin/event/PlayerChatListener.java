package at.er.ytbattle.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.Team;
import at.er.ytbattle.plugin.player.BattlePlayer;

public class PlayerChatListener implements Listener {

    public PlayerChatListener() {
        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());
        if (BattlePlugin.game().isStarted()) {
            if (BattlePlugin.game().getTeamManager().isInTeam(player)) {
                Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);
                if (BattlePlugin.game().getTeamManager().isLastTeam(t)) {
                    event.setFormat(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + "%1$s: " + ChatColor.RESET + "%2$s");
                } else {
                    event.setFormat(t.getTeamColor().getChatColor() + "[Battle]" + ChatColor.WHITE + " - " + "%1$s: " + ChatColor.RESET + "%2$s");
                }
            }
        }
    }

}
