package at.er.ytbattle.plugin.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.handler.BattleCommandHelp;
import at.er.ytbattle.plugin.command.handler.BattleCommandJoin;
import at.er.ytbattle.plugin.command.handler.BattleCommandLeave;
import at.er.ytbattle.plugin.command.handler.BattleCommandLife;
import at.er.ytbattle.plugin.command.handler.BattleCommandList;
import at.er.ytbattle.plugin.command.handler.BattleCommandPause;
import at.er.ytbattle.plugin.command.handler.BattleCommandRandomTeams;
import at.er.ytbattle.plugin.command.handler.BattleCommandReset;
import at.er.ytbattle.plugin.command.handler.BattleCommandResume;
import at.er.ytbattle.plugin.command.handler.BattleCommandSetspawn;
import at.er.ytbattle.plugin.command.handler.BattleCommandSpawn;
import at.er.ytbattle.plugin.command.handler.BattleCommandStart;
import at.er.ytbattle.plugin.command.handler.BattleCommandStats;
import at.er.ytbattle.plugin.player.BattlePlayer;

public class CommandManager implements CommandExecutor {

    private HashMap<String, AbstractCommand> commandHandlers;

    public CommandManager() {
        this.commandHandlers = new HashMap<String, AbstractCommand>();

        this.addCommandHandler("help", new BattleCommandHelp());
        this.addCommandHandler("join", new BattleCommandJoin());
        this.addCommandHandler("leave", new BattleCommandLeave());
        this.addCommandHandler("life", new BattleCommandLife());
        this.addCommandHandler("list", new BattleCommandList());
        this.addCommandHandler("setspawn", new BattleCommandSetspawn());
        this.addCommandHandler("spawn", new BattleCommandSpawn());
        this.addCommandHandler("start", new BattleCommandStart());
        this.addCommandHandler("stats", new BattleCommandStats());
        this.addCommandHandler("reset", new BattleCommandReset());
        this.addCommandHandler("pause", new BattleCommandPause());
        this.addCommandHandler("resume", new BattleCommandResume());
        this.addCommandHandler("randteam", new BattleCommandRandomTeams());
    }

    private void addCommandHandler(String label, AbstractCommand abc) {
        if (this.commandHandlers.containsKey(label)) {
            return;
        } else {
            if (this.commandHandlers.containsValue(abc)) {
                for (Map.Entry<String, AbstractCommand> entry : this.commandHandlers.entrySet()) {
                    if (entry.getValue() == abc) {
                        this.commandHandlers.put(label, entry.getValue());
                        return;
                    }
                }
            } else {
                this.commandHandlers.put(label, abc);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("battle") || label.equalsIgnoreCase("b")) {
            if (sender instanceof Player) {
                BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer((Player) sender);

                if (args.length == 0) {
                    player.sendMessage(BattlePlugin.prefix() + "EXSolo's and Rene8888's Battle Plugin: For a command overview do /battle help");
                } else {
                    String sublabel = args[0];
                    AbstractCommand abc = this.commandHandlers.get(sublabel);
                    if (abc != null) {
                        String[] subargs = Arrays.copyOfRange(args, 1, args.length);
                        boolean ret = abc.onCommand(sublabel, subargs, player);
                        if (!ret) {
                            player.sendMessage(BattlePlugin.prefix() + "Something went wrong! :(");
                        }
                    } else {
                        player.sendMessage(BattlePlugin.prefix() + "No command with label " + sublabel + " found!");
                    }
                }
            } else {
                sender.sendMessage(BattlePlugin.prefix() + "The Battle Commands are only available for users!");
            }
            return true;
        } else {
            return false;
        }
    }
}
