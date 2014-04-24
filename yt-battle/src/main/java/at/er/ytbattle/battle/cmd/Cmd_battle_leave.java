package at.er.ytbattle.battle.cmd;

import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.TeamManager;

public class Cmd_battle_leave {

	private Cmd_battle cmd;

	private TeamManager teamManager;

	public Cmd_battle_leave(Cmd_battle c) {
		cmd = c;
		this.teamManager = cmd.getPlugin().getGame().getTeamManager();
	}

	public boolean onCmdLeave(String[] args, Player player) {
		Battle plugin = cmd.getPlugin();
		if (this.teamManager.isInTeam(player)) {
			player.setDisplayName(player.getName());
			plugin.removeFromLists(player);
			player.sendMessage(Battle.prefix() + "You have left the Battle");
			return true;
		} else {
			player.sendMessage(Battle.prefix() + "You havn't joined the Battle before");
			return true;
		}

	}
}
