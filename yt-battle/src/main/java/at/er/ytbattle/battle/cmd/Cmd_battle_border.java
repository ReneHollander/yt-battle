package at.er.ytbattle.battle.cmd;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.BorderCreator;

public class Cmd_battle_border {

	private Cmd_battle cmd;

	public Cmd_battle_border(Cmd_battle c) {
		cmd = c;
	}

	public boolean onCmdBorder(String[] args, Player player) {

		Battle plugin = cmd.getPlugin();

		int radius = 0;
		
		if (args.length >= 2) {
			try {
				radius = Integer.parseInt(args[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (Bukkit.getPluginManager().getPlugin("WorldEdit") != null) {
				if (plugin.getGame().getSpawn() != null) {
					if (radius > 0) {
						BorderCreator creator = new BorderCreator(plugin);
						player.sendMessage(creator.build(radius));
					} else {
						player.sendMessage(Battle.prefix()
								+ "Borderradius has to be greater than 0!");
					}
				} else {
					player.sendMessage(Battle.prefix()
							+ "Set the Battlespawn first: /battle setspawn");
				}
			} else {
				player.sendMessage(Battle.prefix()
						+ "WordEdit has to be installed, for creating the border!");
			}

		} else {
			player.sendMessage(Battle.prefix()
					+ "Correct usage: /battle border <borderradius>");
		}

		return true;
	}
}
