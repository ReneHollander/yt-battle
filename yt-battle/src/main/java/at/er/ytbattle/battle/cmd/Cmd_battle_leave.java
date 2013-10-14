package at.er.ytbattle.battle.cmd;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;

public class Cmd_battle_leave {

	private Cmd_battle cmd;

	public Cmd_battle_leave(Cmd_battle c) {
		cmd = c;
	}

	public boolean onCmdLeave(String[] args, Player player) {

		Battle plugin = cmd.getPlugin();

		if (plugin.getGame().getPlayers().contains(player.getName())
				|| plugin.getGame().getSpectators().contains(player.getName())) {
			player.setDisplayName(player.getName());
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.showPlayer(player);
			}
			plugin.removeFromLists(player);
			plugin.getGame().getPlayers().remove(player.getName());
			plugin.getGame().getSpectators().remove(player.getName());

			if (player.getGameMode() != GameMode.CREATIVE) {
				player.setAllowFlight(false);
				player.setFlying(false);
			}

			player.sendMessage(Battle.prefix() + "You have left the Battle");
		} else {
			player.sendMessage(Battle.prefix()
					+ "You havn't joined the Battle before");
		}

		return true;
	}
}
