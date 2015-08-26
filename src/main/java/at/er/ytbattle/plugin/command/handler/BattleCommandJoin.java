package at.er.ytbattle.plugin.command.handler;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.plugin.team.TeamColor;
import at.er.ytbattle.util.BattleUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class BattleCommandJoin extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (BattlePlugin.game().isStarted() == false) {
            if (args.length == 0 || args.length > 1) {
                player.sendMessage(BattlePlugin.prefix() + "Correct usage: /battle join <teamname>");
                return true;
            }
            TeamColor tc = TeamColor.getTeamByShortName(args[0]);
            if (tc != null) {
                Team t = BattlePlugin.game().getTeamManager().getTeam(tc);
                BattlePlugin.game().getTeamManager().removePlayerFromTeam(player);
                t.addPlayer(player);
                BattleUtils.setDisplayAndListName(player);
                Bukkit.broadcastMessage(BattlePlugin.prefix() + "Player " + player.getName() + " joined the " + t.getTeamColor().getChatColor() + t.getTeamColor().getLongName() + ChatColor.RESET + " Team!");
                return true;
            } else {
                player.sendMessage(BattlePlugin.prefix() + "Correct usage: /battle join <teamname>");
                return true;
            }
        } else {
            player.sendMessage(BattlePlugin.prefix() + "Battle has already started!");
            return true;
        }
    }
}
