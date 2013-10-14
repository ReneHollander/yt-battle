package at.er.ytbattle.battle.cmd;

import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.util.SerializableLocation;

public class Cmd_battle_spawn_setspawn {

	private Cmd_battle cmd;

	private Battle plugin;

	public Cmd_battle_spawn_setspawn(Cmd_battle c) {
		cmd = c;
		plugin = cmd.getPlugin();
	}

	public boolean onCmdSpawn(String[] args, Player player) {
		if (plugin.getGame().getSpawn() != null) {
			player.teleport(plugin.getGame().getSpawn().getLocation());
		} else {
			player.sendMessage(Battle.prefix()
					+ "Battlespawn hasn't been set yet!");
		}

		return true;
	}

	public boolean onCmdSetspawn(String[] args, Player player) {
		plugin.getGame().setSpawn(new SerializableLocation(player.getLocation()));
		plugin.getGame().getSpawn().getLocation()
				.getWorld()
				.setSpawnLocation((int) plugin.getGame().getSpawn().getLocation().getX(),
						(int) plugin.getGame().getSpawn().getLocation().getY(),
						(int) plugin.getGame().getSpawn().getLocation().getZ());

		plugin.getConfig().set("saves.spawn.world",
				player.getLocation().getWorld().getName());
		plugin.getConfig().set("saves.spawn.x", player.getLocation().getX());
		plugin.getConfig().set("saves.spawn.y", player.getLocation().getY());
		plugin.getConfig().set("saves.spawn.z", player.getLocation().getZ());

		plugin.saveConfig();

		player.sendMessage(Battle.prefix()
				+ "Battlespawn has been set to your current location!");

		return true;
	}
}
