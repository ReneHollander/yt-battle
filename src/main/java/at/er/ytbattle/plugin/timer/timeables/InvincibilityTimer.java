package at.er.ytbattle.plugin.timer.timeables;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.timer.manager.InvincibilityTimerManager;
import at.er.ytbattle.util.timer.Timeable;
import at.er.ytbattle.util.timer.TimerManager.TimeScale;
import org.bukkit.event.Listener;

public class InvincibilityTimer extends Timeable implements Listener {

    private BattlePlayer player;

    public InvincibilityTimer(BattlePlayer player) {
        super(InvincibilityTimerManager.MANAGER_ID, TimeScale.MINUTE, 1, player);
        this.player = player;
    }

    @Override
    public void tick(long elapsedTime) {
        if (BattlePlugin.configurationHelper().getInvincibilityTimerDuration() == elapsedTime) {
            if (player.hasPlayer()) {
                player.sendMessage(BattlePlugin.prefix() + "Your invincibility ended!");
            }
            this.removeTimer();
        } else {
            if (player.hasPlayer()) {
                player.sendMessage(BattlePlugin.prefix() + "Your invincibility ends in " + (BattlePlugin.configurationHelper().getInvincibilityTimerDuration() - elapsedTime) + " minutes!");
            }
        }
    }
}
