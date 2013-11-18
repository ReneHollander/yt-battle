package at.er.ytbattle.battle.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import at.er.ytbattle.battle.Battle;

public class Cmd_battle_spectate {

	private Cmd_battle cmd;

	public Cmd_battle_spectate(Cmd_battle c) {
		cmd = c;
	}

	public boolean onCmdSpectate(String[] args, Player player) {

		Battle plugin = cmd.getPlugin();

		if (plugin.getGame().isStarted()) {

			if (args.length == 1) {

				if (!plugin.getGame().getSpectators().contains(player.getName())) {
					ItemStack map = new ItemStack(Material.EMPTY_MAP);

					for (Player p : Bukkit.getOnlinePlayers()) {
						if (!plugin.getGame().getSpectators().contains(p.getName())) {
							p.hidePlayer(player);
						}
					}

					player.setAllowFlight(true);
					plugin.removeFromLists(player);
					player.getInventory().clear();
					plugin.getGame().getPlayers().remove(player.getName());
					plugin.getGame().getSpectators().add(player.getName());
					player.getInventory().addItem(map);
					player.sendMessage(Battle.prefix() + "You entered spectator mode. You can leave it with /battle leave");

				} else {
					player.sendMessage(Battle.prefix() + "You are already in Spectator mode! Leave it with /battle leave");
					return true;
				}
			}

			if (args.length > 1 && args[1].equalsIgnoreCase("tp")) {

				if (plugin.getGame().getSpectators().contains(player.getName())) {

					if (args.length == 2) {
						player.sendMessage(Battle.prefix() + "Correct usage: /battle spectate tp <playername>");
					}

					if (args.length > 2 && args[2].equalsIgnoreCase("spawn")) {

						player.sendMessage(Battle.prefix() + "Teleporting to Battlespawn...");
						player.teleport(plugin.getGame().getSpawn().getLocation());
						return true;
					}

					if (args.length > 2) {

						try {
							player.teleport(Bukkit.getPlayer(args[2]).getLocation());
							player.sendMessage(Battle.prefix() + "Teleporting...");

						} catch (Exception e) {

							player.sendMessage(Battle.prefix() + "Player isn't online!");
						}
					}
				} else {
					player.sendMessage(Battle.prefix() + "You have to become a spectator first: /battle spectate");
				}

				return true;
			}

			if (args.length > 1 && args[1].equalsIgnoreCase("inv")) {

				if (plugin.getGame().getSpectators().contains(player.getName())) {

					if (args.length == 2) {
						player.sendMessage(Battle.prefix() + "Correct usage: /battle spectate inv <playername>");
						return true;
					}

					if (args.length > 2) {
						Inventory inv = null;

						try {
							inv = Bukkit.getPlayer(args[2]).getInventory();
						} catch (Exception e) {
							player.sendMessage(Battle.prefix() + "Player isn't online!");
							return true;
						}

						if (inv != null) {
							player.getInventory().clear();
							player.openInventory(inv);
						}

						return true;
					}

				} else {
					player.sendMessage(Battle.prefix() + "You have to become a spectator first: /battle spectate");
					return true;
				}
			}

		} else {
			player.sendMessage(Battle.prefix() + "Game has to be started!");
			return true;
		}

		return false;
	}
}
