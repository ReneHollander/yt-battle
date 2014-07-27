package at.er.ytbattle.plugin.timer.manager;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.timer.timeables.InvincibilityTimer;
import at.er.ytbattle.util.timer.Timeable;

public class InvincibilityTimerManager {

    public static final int MANAGER_ID = 943156;

    private int time;

    public InvincibilityTimerManager(int time) {
        this.time = time;
    }

    public void stopTimer(BattlePlayer p) {
        Timeable timeable = BattlePlugin.game().getTimerManager().getTimer(MANAGER_ID, p);
        if (timeable != null) {
            timeable.removeTimer();
        }
    }

    public boolean timerRunning(BattlePlayer p) {
        return BattlePlugin.game().getTimerManager().hasTimer(MANAGER_ID, p);
    }

    public InvincibilityTimer getTimerByPlayer(BattlePlayer p) {
        return (InvincibilityTimer) BattlePlugin.game().getTimerManager().getTimer(MANAGER_ID, p);
    }

    public void createTimer(BattlePlayer p) {
        BattlePlugin.game().getTimerManager().removeTimer(MANAGER_ID, p);
        InvincibilityTimer it = new InvincibilityTimer(p, time);
        BattlePlugin.game().getTimerManager().registerTimer(it);
        it.startTimer();
    }
}
