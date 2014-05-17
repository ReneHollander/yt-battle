package at.er.ytbattle.battle.cmd;

import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.TeamManager;

public class BattleCommandLeave {

	private TeamManager teamManager;

	public BattleCommandLeave() {
		this.teamManager = Battle.instance().getGame().getTeamManager();
	}

	public boolean onCmdLeave(String[] args, Player player) {
		if (this.teamManager.isInTeam(player)) {
			player.setDisplayName(player.getName());
			player.setPlayerListName(player.getName());
			Battle.instance().removeFromLists(player);
			player.sendMessage(Battle.prefix() + "You have left the Battle");
			return true;
		} else {
			player.sendMessage(Battle.prefix() + "You havn't joined the Battle before");
			return true;
		}

	}
}
