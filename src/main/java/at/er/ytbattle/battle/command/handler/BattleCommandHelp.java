package at.er.ytbattle.battle.command.handler;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandHelp extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        player.sendMessage(Battle.prefix() + "Battle Commands:  - <> = has to be attached [] = can be attached \n" + " - Alias for /battle is /b \n" + " - /battle join <red;blue;green;yellow;purple;cyan;black;white> - Adds you to the attached Team \n"
                + " - /battle leave - Removes you from the Battle queue \n" + " - /battle start [graceperiod] - Starts the Battle \n" + " - /battle life - Adds a live in form of a wool to your inventory \n" + " - /battle setspawn - Battlespawn will be set to you current location \n"
                + " - /battle spawn - Teleports you to spawn if set \n" + " - /battle list - Lists all Battle players and spectators \n" + " - /battle stats <red;blue;green;yellow;purple;cyan;black;white> - Returns a summary of stats from the attached Team \n"
                + " - /battle reset - Resets the Battle\n" + " - /battle resettimer - Resets the Youtube Remind Timer\n");
        return true;
    }

}
