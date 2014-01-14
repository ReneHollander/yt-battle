package at.er.ytbattle.battle.cmd;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamColor;
import at.er.ytbattle.battle.TeamManager;

public class Cmd_battle_join {

	private Cmd_battle cmd;

	private TeamManager teamManager;

	public Cmd_battle_join(Cmd_battle c) {
		cmd = c;
		this.teamManager = c.getPlugin().getGame().getTeamManager();
	}

	public boolean onCmdJoin(String[] args, Player player) {
		Battle plugin = cmd.getPlugin();
		if (plugin.getGame().isStarted() == false) {
			if (args.length == 1 || args.length > 2) {
				player.sendMessage(Battle.prefix() + "Correct usage: /battle join <teamname>");
				return true;
			}
			TeamColor tc = TeamColor.getTeamByShortName(args[1]);
			if (tc != null) {
				Team t = this.teamManager.getTeam(tc);
				plugin.removeFromLists(player);
				t.addPlayer(player.getName());
				Bukkit.broadcastMessage("Player " + player.getName() + " joined the " + t.getTeamColor().getLongName() + "Team!");
				return true;
			} else {
				player.sendMessage("Correct usage: /battle join <teamname>");
				return true;
			}
		} else {
			player.sendMessage(Battle.prefix() + "Battle has already started!");
			return true;
		}
	}
}
