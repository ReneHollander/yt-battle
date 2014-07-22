package at.er.ytbattle.battle.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.player.BattlePlayerManager;

public class BattleCommands implements CommandExecutor {

    private BattleCommandHelpReset help_reset;
    private BattleCommandJoin join;
    private BattleCommandLeave leave;
    private BattleCommandLife life;
    private BattleCommandSetSpawn spawn_setspawn;
    private BattleCommandStart start;
    private BattleCommandStatList stats_list;
    private BattleCommandResetTimer resettimer;

    public BattleCommands() {
        this.help_reset = new BattleCommandHelpReset();
        this.join = new BattleCommandJoin();
        this.leave = new BattleCommandLeave();
        this.life = new BattleCommandLife();
        this.spawn_setspawn = new BattleCommandSetSpawn();
        this.start = new BattleCommandStart();
        this.stats_list = new BattleCommandStatList();
        this.resettimer = new BattleCommandResetTimer();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            BattlePlayer player = BattlePlayerManager.instance().getBattlePlayer((Player) sender);

            if (label.equalsIgnoreCase("battle") || label.equalsIgnoreCase("b")) {

                if (args.length == 0) {
                    player.sendMessage(Battle.prefix() + "EXSolo's and Rene8888's Battle Plugin: For a command overview do /battle help");
                    return true;
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

                if (args[0].equalsIgnoreCase("start")) {
                    return start.onCmdStart(args, player);
                }

                if (args[0].equalsIgnoreCase("stats")) {
                    return stats_list.onCmdStats(args, player);
                }

                if (args[0].equalsIgnoreCase("reset")) {
                    return help_reset.onCmdReset(args, player);
                }

                if (args[0].equalsIgnoreCase("resettimer")) {
                    return resettimer.onCmdResetTimer(args, player);
                }

                return false;

            }
        }

        return false;
    }

}
