package at.er.ytbattle.battle.command.handler;

import java.io.File;

import org.bukkit.Bukkit;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandReset extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        Battle.instance().getGame().getTimerManager().removeAllTimers();
        
        Battle.instance().dontSave(true);

        Battle.instance().unsetTags();
        Battle.instance().updateScoreboard();

        File file = new File(Battle.instance().getDataFolder(), "savegame.xml");

        if (file.exists()) {
            if (!file.delete())
                file.deleteOnExit();
        }

        Bukkit.reload();

        player.sendMessage(Battle.prefix() + "The Battle was reloaded and the save file reset!");

        return true;
    }
}
