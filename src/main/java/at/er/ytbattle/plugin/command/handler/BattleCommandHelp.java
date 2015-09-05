package at.er.ytbattle.plugin.command.handler;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;

public class BattleCommandHelp extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        player.sendMessage(BattlePlugin.prefix() + "Battle Commands: - <> = has to be attached [] = can be attached");
        player.sendMessage("Alias for /battle is /b");

        player.sendMessage("/battle setspawn - Battlespawn will be set to you current location");
        player.sendMessage("/battle spawn - Teleports you to spawn if set");
        player.sendMessage("/battle join <red;blue;green;yellow;purple;cyan;black;white> - Adds you to the attached Team");
        player.sendMessage("/battle leave - Removes you from the Battle queue");
        player.sendMessage("/battle randteam - Adds every online player to a random team");

        player.sendMessage("/battle start [graceperiod] - Starts the Battle");
        player.sendMessage("/battle pause - Pauses the Battle");
        player.sendMessage("/battle resume - Resumes the Battle");
        player.sendMessage("/battle reset - Resets the Battle");

        player.sendMessage("/battle life - Adds a live in form of a wool to your inventory");

        player.sendMessage("/battle list - Lists all Battle players and spectators");
        player.sendMessage("/battle stats <red;blue;green;yellow;purple;cyan;black;white> - Returns a summary of stats from the attached Team");

        return true;
    }
}
