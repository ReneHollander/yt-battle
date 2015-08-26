package at.er.ytbattle.plugin.command.handler;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;

public class BattleCommandReset extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        BattlePlugin.instance().resetGame();
        player.sendMessage(BattlePlugin.prefix() + "The Battle was reloaded and the save file reset!");
        return true;
    }
}
