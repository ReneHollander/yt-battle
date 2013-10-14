package at.er.ytbattle.battle.cmd;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import at.er.ytbattle.battle.Battle;

public class Cmd_battle_life {

	private Cmd_battle cmd;

	public Cmd_battle_life(Cmd_battle c) {
		cmd = c;
	}

	public boolean onCmdLife(String[] args, Player player) {

		Battle plugin = cmd.getPlugin();

		if (plugin.getGame().isStarted()) {

			if (plugin.getGame().getRed().getPlayers().contains(player.getName())) {
				if (plugin.getGame().getRed().getLifes() > 0) {
					player.getInventory().addItem(
							new ItemStack(Material.WOOL, 1, (short) 14));
					plugin.getGame().getRed().setLifes(plugin.getGame().getRed().getLifes() - 1);
				} else {
					player.sendMessage(Battle.prefix()
							+ "Your team hasn't enough lifes left!");
				}
			}

			if (plugin.getGame().getBlue().getPlayers().contains(player.getName())) {
				if (plugin.getGame().getBlue().getLifes() > 0) {
					player.getInventory().addItem(
							new ItemStack(Material.WOOL, 1, (short) 11));
					plugin.getGame().getBlue().setLifes(plugin.getGame().getBlue().getLifes() - 1);
				} else {
					player.sendMessage(Battle.prefix()
							+ "Your team hasn't enough lifes left!");
				}
			}

			if (plugin.getGame().getGreen().getPlayers().contains(player.getName())) {
				if (plugin.getGame().getGreen().getLifes() > 0) {
					player.getInventory().addItem(
							new ItemStack(Material.WOOL, 1, (short) 5));
					plugin.getGame().getGreen().setLifes(plugin.getGame().getGreen().getLifes() - 1);
				} else {
					player.sendMessage(Battle.prefix()
							+ "Your team hasn't enough lifes left!");
				}
			}

			if (plugin.getGame().getYellow().getPlayers().contains(player.getName())) {
				if (plugin.getGame().getYellow().getLifes() > 0) {
					player.getInventory().addItem(
							new ItemStack(Material.WOOL, 1, (short) 4));
					plugin.getGame().getYellow().setLifes(plugin.getGame().getYellow().getLifes() - 1);
				} else {
					player.sendMessage(Battle.prefix()
							+ "Your team hasn't enough lifes left!");
				}
			}

			if (plugin.getGame().getPurple().getPlayers().contains(player.getName())) {
				if (plugin.getGame().getPurple().getLifes() > 0) {
					player.getInventory().addItem(
							new ItemStack(Material.WOOL, 1, (short) 10));
					plugin.getGame().getPurple().setLifes(plugin.getGame().getPurple().getLifes() - 1);
				} else {
					player.sendMessage(Battle.prefix()
							+ "Your team hasn't enough lifes left!");
				}
			}
			
			if (plugin.getGame().getCyan().getPlayers().contains(player.getName())) {
				if (plugin.getGame().getCyan().getLifes() > 0) {
					player.getInventory().addItem(
							new ItemStack(Material.WOOL, 1, (short) 9));
					plugin.getGame().getCyan().setLifes(plugin.getGame().getCyan().getLifes() - 1);
				} else {
					player.sendMessage(Battle.prefix()
							+ "Your team hasn't enough lifes left!");
				}
			}
			
			if (plugin.getGame().getBlack().getPlayers().contains(player.getName())) {
				if (plugin.getGame().getBlack().getLifes() > 0) {
					player.getInventory().addItem(
							new ItemStack(Material.WOOL, 1, (short) 15));
					plugin.getGame().getBlack().setLifes(plugin.getGame().getBlack().getLifes() - 1);
				} else {
					player.sendMessage(Battle.prefix()
							+ "Your team hasn't enough lifes left!");
				}
			}
			
			if (plugin.getGame().getWhite().getPlayers().contains(player.getName())) {
				if (plugin.getGame().getWhite().getLifes() > 0) {
					player.getInventory().addItem(
							new ItemStack(Material.WOOL));
					plugin.getGame().getWhite().setLifes(plugin.getGame().getWhite().getLifes() - 1);
				} else {
					player.sendMessage(Battle.prefix()
							+ "Your team hasn't enough lifes left!");
				}
			}
			
			plugin.updateScoreboard();
			
			return true;
		}
		
		return false;
	}
}
