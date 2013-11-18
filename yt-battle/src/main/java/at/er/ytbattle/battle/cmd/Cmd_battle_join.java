package at.er.ytbattle.battle.cmd;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;

public class Cmd_battle_join {

	private Cmd_battle cmd;

	public Cmd_battle_join(Cmd_battle c) {
		cmd = c;
	}

	public boolean onCmdJoin(String[] args, Player player) {

		Battle plugin = cmd.getPlugin();

		if (plugin.getGame().isStarted() == false) {

			if (!plugin.getGame().getPlayers().contains(player.getName()))
				plugin.getGame().getPlayers().add(player.getName());

			if (args.length == 1 || args.length > 2) {
				player.sendMessage(Battle.prefix() + "Correct usage: /battle join <teamname>");
				return true;
			}

			if (args[1].equalsIgnoreCase("red")) {
				plugin.removeFromLists(player);
				plugin.getGame().getRed().getPlayers().add(player.getName());
				player.setDisplayName(ChatColor.DARK_RED + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				player.sendMessage(Battle.prefix() + "You have joined the red team");
				return true;
			}
			if (args[1].equalsIgnoreCase("blue")) {
				plugin.removeFromLists(player);
				plugin.getGame().getBlue().getPlayers().add(player.getName());
				player.setDisplayName(ChatColor.DARK_BLUE + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				player.sendMessage(Battle.prefix() + "You have joined the blue team");
				return true;
			}
			if (args[1].equalsIgnoreCase("green")) {
				plugin.removeFromLists(player);
				plugin.getGame().getGreen().getPlayers().add(player.getName());
				player.setDisplayName(ChatColor.DARK_GREEN + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				player.sendMessage(Battle.prefix() + "You have joined the green team");
				return true;
			}
			if (args[1].equalsIgnoreCase("yellow")) {
				plugin.removeFromLists(player);
				plugin.getGame().getYellow().getPlayers().add(player.getName());
				player.setDisplayName(ChatColor.YELLOW + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				player.sendMessage(Battle.prefix() + "You have joined the yellow team");
				return true;
			}
			if (args[1].equalsIgnoreCase("purple")) {
				plugin.removeFromLists(player);
				plugin.getGame().getPurple().getPlayers().add(player.getName());
				player.setDisplayName(ChatColor.DARK_PURPLE + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				player.sendMessage(Battle.prefix() + "You have joined the purple team");
				return true;
			}
			if (args[1].equalsIgnoreCase("cyan")) {
				plugin.removeFromLists(player);
				plugin.getGame().getCyan().getPlayers().add(player.getName());
				player.setDisplayName(ChatColor.DARK_AQUA + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				player.sendMessage(Battle.prefix() + "You have joined the cyan team");
				return true;
			}
			if (args[1].equalsIgnoreCase("black")) {
				plugin.removeFromLists(player);
				plugin.getGame().getBlack().getPlayers().add(player.getName());
				player.setDisplayName(ChatColor.BLACK + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				player.sendMessage(Battle.prefix() + "You have joined the black team");
				return true;
			}

			if (args[1].equalsIgnoreCase("white")) {
				plugin.removeFromLists(player);
				plugin.getGame().getWhite().getPlayers().add(player.getName());
				player.setDisplayName(ChatColor.BOLD + "[Battle]" + ChatColor.WHITE + " - " + player.getName());
				player.sendMessage(Battle.prefix() + "You have joined the white team");
				return true;
			}

			player.sendMessage("Correct usage: /battle join <teamname>");
			return true;
		} else {
			player.sendMessage(Battle.prefix() + "Battle has already started!");
			return true;
		}
	}
}
