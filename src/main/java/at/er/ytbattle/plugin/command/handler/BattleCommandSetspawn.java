package at.er.ytbattle.plugin.command.handler;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.util.SerializableLocation;
import org.bukkit.Location;

public class BattleCommandSetspawn extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        Location spawn = player.getLocation();
        BattlePlugin.game().setSpawn(new SerializableLocation(spawn));
        spawn.getWorld().setSpawnLocation((int) spawn.getX(), (int) spawn.getY(), (int) spawn.getZ());
        player.sendMessage(BattlePlugin.prefix() + "Battlespawn has been set to your current location!");
        return true;
    }
}
