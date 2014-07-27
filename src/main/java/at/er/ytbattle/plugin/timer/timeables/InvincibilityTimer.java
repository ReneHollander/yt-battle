package at.er.ytbattle.plugin.timer.timeables;

import org.bukkit.event.Listener;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.timer.manager.InvincibilityTimerManager;
import at.er.ytbattle.util.timer.Timeable;
import at.er.ytbattle.util.timer.TimerManager.TimeScale;

public class InvincibilityTimer extends Timeable implements Listener {

    private BattlePlayer player;
    private int duration;

    public InvincibilityTimer(BattlePlayer player, int duration) {
        super(InvincibilityTimerManager.MANAGER_ID, TimeScale.MINUTE, 1, player);
        this.player = player;
        this.duration = duration;
    }

    @Override
    public void tick(long elapsedTime) {
        if (this.duration == elapsedTime) {
            player.sendMessage(BattlePlugin.prefix() + "Your invincibility ended!");
            this.removeTimer();
        } else {
            player.sendMessage(BattlePlugin.prefix() + "Your invincibility ends in " + (this.duration - elapsedTime) + " minutes!");
        }
    }
}
