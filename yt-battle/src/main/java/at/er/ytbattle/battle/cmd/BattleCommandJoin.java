package at.er.ytbattle.battle.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamColor;
import at.er.ytbattle.battle.TeamManager;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandJoin {

	private TeamManager teamManager;

	public BattleCommandJoin() {
		this.teamManager = Battle.instance().getGame().getTeamManager();
	}

	public boolean onCmdJoin(String[] args, BattlePlayer player) {
		if (Battle.instance().getGame().isStarted() == false) {
			if (args.length == 1 || args.length > 2) {
				player.sendMessage(Battle.prefix() + "Correct usage: /battle join <teamname>");
				return true;
			}
			TeamColor tc = TeamColor.getTeamByShortName(args[1]);
			if (tc != null) {
				Team t = this.teamManager.getTeam(tc);
				Battle.instance().removeFromLists(player);
				t.addPlayer(player);
				Battle.instance().setDisplayAndListName(player, t);
				Bukkit.broadcastMessage(Battle.prefix() + "Player " + player.getName() + " joined the " + t.getTeamColor().getChatColor() + t.getTeamColor().getLongName() + ChatColor.RESET + " Team!");
				return true;
			} else {
				player.sendMessage(Battle.prefix() + "Correct usage: /battle join <teamname>");
				return true;
			}
		} else {
			player.sendMessage(Battle.prefix() + "Battle has already started!");
			return true;
		}
	}
}
