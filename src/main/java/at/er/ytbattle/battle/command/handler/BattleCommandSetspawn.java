package at.er.ytbattle.battle.command.handler;

import org.bukkit.Location;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.util.SerializableLocation;

public class BattleCommandSetspawn extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        Location spawn = player.getLocation();
        Battle.instance().getGame().setSpawn(new SerializableLocation(spawn));
        spawn.getWorld().setSpawnLocation((int) spawn.getX(), (int) spawn.getY(), (int) spawn.getZ());
        player.sendMessage(Battle.prefix() + "Battlespawn has been set to your current location!");
        return true;
    }
}
