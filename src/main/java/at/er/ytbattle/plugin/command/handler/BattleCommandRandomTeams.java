package at.er.ytbattle.plugin.command.handler;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.util.BattleUtils;

public class BattleCommandRandomTeams extends AbstractCommand {

	@Override
	public boolean onCommand(String label, String[] args, BattlePlayer bp) {
		if (BattlePlugin.game().isStarted() == false) {
			ArrayList<Team> teams = BattlePlugin.game().getTeamManager().getTeams();
			Random r = new Random();
			for (BattlePlayer player : BattlePlugin.game().getBattlePlayerManager().getAllBattlePlayers()) {
				Team t = teams.get(r.nextInt(teams.size()));
				BattlePlugin.game().getTeamManager().removePlayerFromTeam(player);
				t.addPlayer(player);
				BattleUtils.setDisplayAndListName(player);
				Bukkit.broadcastMessage(BattlePlugin.prefix() + "Player " + player.getName() + " joined the " + t.getTeamColor().getChatColor() + t.getTeamColor().getLongName() + ChatColor.RESET + " Team!");
			}
			return true;
		} else {
			bp.sendMessage(BattlePlugin.prefix() + "Battle has already started!");
			return true;
		}
	}
}
