package at.er.ytbattle.battle.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;

public class Cmd_battle_stats_list {

	private Cmd_battle cmd;
	private Battle plugin;

	public Cmd_battle_stats_list(Cmd_battle c) {
		cmd = c;
		plugin = cmd.getPlugin();
	}

	public boolean onCmdStats(String[] args, Player player) {
		if (plugin.getGame().isStarted()) {
			String list = "";
			String winners = "";

			if (plugin.getGame().getTeams().size() > 1) {
				if (args.length == 1) {
					player.sendMessage(Battle.prefix() + "Please select a team: /battle stats <teamname>");
					return true;
				}
				if (args[1].equalsIgnoreCase("red")) {
					for (int i = 0; i < plugin.getGame().getRed().getPlayers().size(); i++) {
						list += plugin.getGame().getRed().getPlayers().get(i) + " (" + Bukkit.getPlayer(plugin.getGame().getRed().getPlayers().get(i)).getHealth() * 10 / 2 + "%), ";
					}
					if (list.length() > 0)
						list = list.substring(0, list.lastIndexOf(','));
					player.sendMessage(ChatColor.DARK_RED + "Red Team:" + ChatColor.WHITE + "\n" + "Players: " + list + "\n" + "Lifes: " + plugin.getGame().getRed().getLifes() + "\n" + "Wools: "
							+ plugin.getGame().getRed().getBlockPlaceTimer().getWools());
				}
				if (args[1].equalsIgnoreCase("blue")) {
					for (int i = 0; i < plugin.getGame().getBlue().getPlayers().size(); i++) {
						list += plugin.getGame().getBlue().getPlayers().get(i) + " (" + Bukkit.getPlayer(plugin.getGame().getBlue().getPlayers().get(i)).getHealth() * 10 / 2 + "%), ";
					}
					if (list.length() > 0)
						list = list.substring(0, list.lastIndexOf(','));
					player.sendMessage(ChatColor.DARK_BLUE + "Blue Team:" + ChatColor.WHITE + "\n" + "Players: " + list + "\n" + "Lifes: " + plugin.getGame().getBlue().getLifes() + "\n" + "Wools: "
							+ plugin.getGame().getBlue().getBlockPlaceTimer().getWools());
				}
				if (args[1].equalsIgnoreCase("green")) {
					for (int i = 0; i < plugin.getGame().getGreen().getPlayers().size(); i++) {
						list += plugin.getGame().getGreen().getPlayers().get(i) + " (" + Bukkit.getPlayer(plugin.getGame().getGreen().getPlayers().get(i)).getHealth() * 10 / 2 + "%), ";
					}
					if (list.length() > 0)
						list = list.substring(0, list.lastIndexOf(','));
					player.sendMessage(ChatColor.GREEN + "Green Team:" + ChatColor.WHITE + "\n" + "Players: " + list + "\n" + "Lifes: " + plugin.getGame().getGreen().getLifes() + "\n" + "Wools: "
							+ plugin.getGame().getGreen().getBlockPlaceTimer().getWools());
				}
				if (args[1].equalsIgnoreCase("yellow")) {
					for (int i = 0; i < plugin.getGame().getYellow().getPlayers().size(); i++) {
						list += plugin.getGame().getYellow().getPlayers().get(i) + " (" + Bukkit.getPlayer(plugin.getGame().getYellow().getPlayers().get(i)).getHealth() * 10 / 2 + "%), ";
					}
					if (list.length() > 0)
						list = list.substring(0, list.lastIndexOf(','));
					player.sendMessage(ChatColor.YELLOW + "Yellow Team:" + ChatColor.WHITE + "\n" + "Players: " + list + "\n" + "Lifes: " + plugin.getGame().getYellow().getLifes() + "\n" + "Wools: "
							+ plugin.getGame().getYellow().getBlockPlaceTimer().getWools());
				}
				if (args[1].equalsIgnoreCase("purple")) {
					for (int i = 0; i < plugin.getGame().getPurple().getPlayers().size(); i++) {
						list += plugin.getGame().getPurple().getPlayers().get(i) + " (" + Bukkit.getPlayer(plugin.getGame().getPurple().getPlayers().get(i)).getHealth() * 10 / 2 + "%), ";
					}
					if (list.length() > 0)
						list = list.substring(0, list.lastIndexOf(','));
					player.sendMessage(ChatColor.DARK_PURPLE + "Purple Team:" + ChatColor.WHITE + "\n" + "Players: " + list + "\n" + "Lifes: " + plugin.getGame().getPurple().getLifes() + "\n" + "Wools: "
							+ plugin.getGame().getPurple().getBlockPlaceTimer().getWools());
				}
				if (args[1].equalsIgnoreCase("cyan")) {
					for (int i = 0; i < plugin.getGame().getCyan().getPlayers().size(); i++) {
						list += plugin.getGame().getCyan().getPlayers().get(i) + " (" + Bukkit.getPlayer(plugin.getGame().getCyan().getPlayers().get(i)).getHealth() * 10 / 2 + "%), ";
					}
					if (list.length() > 0)
						list = list.substring(0, list.lastIndexOf(','));
					player.sendMessage(ChatColor.DARK_AQUA + "Cyan Team:" + ChatColor.WHITE + "\n" + "Players: " + list + "\n" + "Lifes: " + plugin.getGame().getCyan().getLifes() + "\n" + "Wools: "
							+ plugin.getGame().getCyan().getBlockPlaceTimer().getWools());
				}
				if (args[1].equalsIgnoreCase("black")) {
					for (int i = 0; i < plugin.getGame().getBlack().getPlayers().size(); i++) {
						list += plugin.getGame().getBlack().getPlayers().get(i) + " (" + Bukkit.getPlayer(plugin.getGame().getBlack().getPlayers().get(i)).getHealth() * 10 / 2 + "%), ";
					}
					if (list.length() > 0)
						list = list.substring(0, list.lastIndexOf(','));
					player.sendMessage(ChatColor.BLACK + "Black Team:" + ChatColor.WHITE + "\n" + "Players: " + list + "\n" + "Lifes: " + plugin.getGame().getBlack().getLifes() + "\n" + "Wools: "
							+ plugin.getGame().getBlack().getBlockPlaceTimer().getWools());
				}
				if (args[1].equalsIgnoreCase("white")) {
					for (int i = 0; i < plugin.getGame().getWhite().getPlayers().size(); i++) {
						list += plugin.getGame().getWhite().getPlayers().get(i) + " (" + Bukkit.getPlayer(plugin.getGame().getWhite().getPlayers().get(i)).getHealth() * 10 / 2 + "%), ";
					}
					if (list.length() > 0)
						list = list.substring(0, list.lastIndexOf(','));
					player.sendMessage(ChatColor.BOLD + "White Team:" + ChatColor.WHITE + "\n" + "Players: " + list + "\n" + "Lifes: " + plugin.getGame().getWhite().getLifes() + "\n" + "Wools: "
							+ plugin.getGame().getWhite().getBlockPlaceTimer().getWools());
				}
			} else {
				winners = plugin.getGame().getTeams().get(0);
				player.sendMessage(Battle.prefix() + "The " + winners + " team has won the battle - Stats are disabled now!");
			}

			return true;
		}

		return false;
	}

	public boolean onCmdList(String[] args, Player player) {
		String list = "";
		for (int i = 0; i < plugin.getGame().getPlayers().size(); i++) {
			list = list + plugin.getGame().getPlayers().get(i) + ", ";
		}

		for (int i = 0; i < plugin.getGame().getSpectators().size(); i++) {
			list = list + ChatColor.DARK_AQUA + plugin.getGame().getSpectators().get(i) + ChatColor.GOLD + ", ";
		}

		if (list.equals("")) {
			player.sendMessage(Battle.prefix() + "The Playerlist and Spectatorlist are empty!");
		} else {
			player.sendMessage(Battle.prefix() + "Battleplayers " + "[P] " + ChatColor.DARK_AQUA + "[S]" + ChatColor.GOLD + ": " + list.substring(0, list.lastIndexOf(',')));
		}
		return true;
	}
}
