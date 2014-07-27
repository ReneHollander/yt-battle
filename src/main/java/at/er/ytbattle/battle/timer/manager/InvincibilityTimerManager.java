package at.er.ytbattle.battle.timer.manager;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.timer.timeables.InvincibilityTimer;
import at.er.ytbattle.util.timer.Timeable;

public class InvincibilityTimerManager {

    public static final int MANAGER_ID = 943156;

    private int time;

    public InvincibilityTimerManager(int time) {
        this.time = time;
    }

    public void stopTimer(BattlePlayer p) {
        Timeable timeable = Battle.game().getTimerManager().getTimer(MANAGER_ID, p);
        if (timeable != null) {
            timeable.removeTimer();
        }
    }

    public boolean timerRunning(BattlePlayer p) {
        return Battle.game().getTimerManager().hasTimer(MANAGER_ID, p);
    }

    public InvincibilityTimer getTimerByPlayer(BattlePlayer p) {
        return (InvincibilityTimer) Battle.game().getTimerManager().getTimer(MANAGER_ID, p);
    }

    public void createTimer(BattlePlayer p) {
        Battle.game().getTimerManager().removeTimer(MANAGER_ID, p);
        InvincibilityTimer it = new InvincibilityTimer(p, time);
        Battle.game().getTimerManager().registerTimer(it);
        it.startTimer();
    }
}
