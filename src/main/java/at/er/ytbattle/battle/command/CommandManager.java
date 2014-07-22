package at.er.ytbattle.battle.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.handler.BattleCommandHelp;
import at.er.ytbattle.battle.command.handler.BattleCommandJoin;
import at.er.ytbattle.battle.command.handler.BattleCommandLeave;
import at.er.ytbattle.battle.command.handler.BattleCommandLife;
import at.er.ytbattle.battle.command.handler.BattleCommandList;
import at.er.ytbattle.battle.command.handler.BattleCommandReset;
import at.er.ytbattle.battle.command.handler.BattleCommandResetTimer;
import at.er.ytbattle.battle.command.handler.BattleCommandSetspawn;
import at.er.ytbattle.battle.command.handler.BattleCommandSpawn;
import at.er.ytbattle.battle.command.handler.BattleCommandStart;
import at.er.ytbattle.battle.command.handler.BattleCommandStats;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.player.BattlePlayerManager;

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
        this.addCommandHandler("resettimer", new BattleCommandResetTimer());
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
                BattlePlayer player = BattlePlayerManager.instance().getBattlePlayer((Player) sender);

                if (args.length == 0) {
                    player.sendMessage(Battle.prefix() + "EXSolo's and Rene8888's Battle Plugin: For a command overview do /battle help");
                } else {
                    String sublabel = args[0];
                    AbstractCommand abc = this.commandHandlers.get(sublabel);
                    if (abc != null) {
                        String[] subargs = Arrays.copyOfRange(args, 1, args.length);
                        boolean ret = abc.onCommand(sublabel, subargs, player);
                        if (!ret) {
                            player.sendMessage(Battle.prefix() + "Something went wrong! :(");
                        }
                    } else {
                        player.sendMessage(Battle.prefix() + "No command with label " + sublabel + " found!");
                    }
                }
            } else {
                sender.sendMessage(Battle.prefix() + "The Battle Commands are only available for users!");
            }
            return true;
        } else {
            return false;
        }
    }
}
