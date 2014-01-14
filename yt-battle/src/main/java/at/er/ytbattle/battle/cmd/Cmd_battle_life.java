package at.er.ytbattle.battle.cmd;

import org.bukkit.entity.Player;
import org.bukkit.material.Wool;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamManager;

public class Cmd_battle_life {

	private Cmd_battle cmd;
	
	private TeamManager teamManager;

	public Cmd_battle_life(Cmd_battle c) {
		cmd = c;
		this.teamManager = cmd.getPlugin().getGame().getTeamManager();
	}

	public boolean onCmdLife(String[] args, Player player) {

		Battle plugin = cmd.getPlugin();

		if (plugin.getGame().isStarted() && this.teamManager.isInTeam(player)) {
			Team t = this.teamManager.getTeamByPlayer(player);
			if (t.getLifes() > 1) {
				player.getInventory().addItem(new Wool(t.getTeamColor().getDyeColor()).toItemStack(1));
				t.setLifes(t.getLifes() - 1);
				player.sendMessage(Battle.prefix() + "You recieved a new wool!");
			} else {
				player.sendMessage(Battle.prefix() + "Your team hasn't enough lifes left!");
			}
			plugin.updateScoreboard();
			return true;
		}
		return false;
	}
}
