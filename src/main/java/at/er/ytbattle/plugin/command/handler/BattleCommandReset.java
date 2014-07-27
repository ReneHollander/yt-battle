package at.er.ytbattle.plugin.command.handler;

import java.io.File;

import org.bukkit.Bukkit;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.util.BattleUtils;

public class BattleCommandReset extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        BattlePlugin.game().getTimerManager().removeAllTimers();

        BattlePlugin.instance().dontSave(true);

        BattleUtils.unsetTags();
        BattleUtils.updateScoreboard();

        File file = new File(BattlePlugin.instance().getDataFolder(), "savegame.xml");

        if (file.exists()) {
            if (!file.delete())
                file.deleteOnExit();
        }

        Bukkit.reload();

        player.sendMessage(BattlePlugin.prefix() + "The Battle was reloaded and the save file reset!");

        return true;
    }
}
