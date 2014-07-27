package at.er.ytbattle.battle.timer.timeables;

import org.bukkit.event.Listener;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.timer.manager.InvincibilityTimerManager;
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
            player.sendMessage(Battle.prefix() + "Your invincibility ended!");
            this.removeTimer();
        } else {
            player.sendMessage(Battle.prefix() + "Your invincibility ends in " + (this.duration - elapsedTime) + " minutes!");
        }
    }
}
