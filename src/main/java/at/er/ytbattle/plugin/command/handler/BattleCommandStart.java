package at.er.ytbattle.plugin.command.handler;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.timer.timeables.BattleStartCountdown;
import at.er.ytbattle.util.ConfigurationHelper;
import at.er.ytbattle.util.SerializableLocation;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class BattleCommandStart extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (!BattlePlugin.game().isStarted()) {
            if (BattlePlugin.game().getTeamManager().getTeamCount() > 0) {
                int graceTime = 0;
                if (args.length >= 1) {
                    String graceTimeStr = args[0];
                    if (StringUtils.isNumeric(graceTimeStr)) {
                        graceTime = Integer.parseInt(graceTimeStr);
                    }
                }

                if (BattlePlugin.game().getSpawn() == null) {
                    Location spawn = player.getLocation();
                    BattlePlugin.game().setSpawn(new SerializableLocation(spawn));
                    spawn.getWorld().setSpawnLocation((int) spawn.getX(), (int) spawn.getY(), (int) spawn.getZ());
                }

                if (BattlePlugin.configurationHelper().getConfigFile().getBoolean(ConfigurationHelper.MISC_STARTCOUNTDOWN_ENABLED_PATH)) {
                    new BattleStartCountdown(BattlePlugin.configurationHelper().getConfigFile().getInt(ConfigurationHelper.MISC_STARTCOUNTDOWN_DURATION_PATH), graceTime);
                } else {
                    Bukkit.broadcastMessage(BattlePlugin.prefix() + "The Battle has been started! Let the games begin!");
                    BattlePlugin.instance().startGame(graceTime);
                }
            } else {
                player.sendMessage(BattlePlugin.prefix() + "There have to be at least two teams with one or more Player(s) before the battle can be launched!");
            }
        } else {
            player.sendMessage(BattlePlugin.prefix() + "Battle has already been started!");
        }
        return true;
    }
}
