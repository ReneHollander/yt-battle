package at.er.ytbattle.battle.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;

public class Cmd_battle implements CommandExecutor {

	private Battle plugin;

	private Cmd_battle_border border;
	private Cmd_battle_help_reset help_reset;
	private Cmd_battle_join join;
	private Cmd_battle_leave leave;
	private Cmd_battle_life life;
	private Cmd_battle_spawn_setspawn spawn_setspawn;
	private Cmd_battle_spectate spectate;
	private Cmd_battle_start start;
	private Cmd_battle_stats_list stats_list;

	public Cmd_battle(Battle b) {
		this.plugin = b;

		this.border = new Cmd_battle_border(this);
		this.help_reset = new Cmd_battle_help_reset(this, b);
		this.join = new Cmd_battle_join(this);
		this.leave = new Cmd_battle_leave(this);
		this.life = new Cmd_battle_life(this);
		this.spawn_setspawn = new Cmd_battle_spawn_setspawn(this);
		this.spectate = new Cmd_battle_spectate(this);
		this.start = new Cmd_battle_start(this);
		this.stats_list = new Cmd_battle_stats_list(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		/* TODO Fix command system (No message on wrong input) */
		
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (label.equalsIgnoreCase("battle") || label.equalsIgnoreCase("b")) {

				if (args.length == 0) {
					player.sendMessage(Battle.prefix() + "EXSolo's and Rene8888's Battle Plugin: For a command overview do /battle help");
					return true;
				}

				if (args[0].equalsIgnoreCase("border")) {
					return border.onCmdBorder(args, player);
				}

				if (args[0].equalsIgnoreCase("help")) {
					return help_reset.onCmdHelp(args, player);
				}

				if (args[0].equalsIgnoreCase("join")) {
					return join.onCmdJoin(args, player);
				}

				if (args[0].equalsIgnoreCase("leave")) {
					return leave.onCmdLeave(args, player);
				}

				if (args[0].equalsIgnoreCase("life")) {
					return life.onCmdLife(args, player);
				}

				if (args[0].equalsIgnoreCase("list")) {
					return stats_list.onCmdList(args, player);
				}

				if (args[0].equalsIgnoreCase("setspawn")) {
					return spawn_setspawn.onCmdSetspawn(args, player);
				}

				if (args[0].equalsIgnoreCase("spawn")) {
					return spawn_setspawn.onCmdSpawn(args, player);
				}

				if (args[0].equalsIgnoreCase("spectate")) {
					return spectate.onCmdSpectate(args, player);
				}

				if (args[0].equalsIgnoreCase("start")) {
					return start.onCmdStart(args, player);
				}

				if (args[0].equalsIgnoreCase("stats")) {
					return stats_list.onCmdStats(args, player);
				}

				if (args[0].equalsIgnoreCase("reset")) {
					return help_reset.onCmdReset(args, player);
				}
			}
		}

		return false;
	}

	public Battle getPlugin() {
		return plugin;
	}

	public void setPlugin(Battle plugin) {
		this.plugin = plugin;
	}
}
