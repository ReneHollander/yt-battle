package at.er.ytbattle.battle.timer;

import java.util.HashMap;
import java.util.Map;

import at.er.ytbattle.battle.player.BattlePlayer;

public class InvincibilityTimerManager {

    private Map<BattlePlayer, InvincibilityTimer> timers;

    private int time;

    public InvincibilityTimerManager(int time) {
        this.timers = new HashMap<BattlePlayer, InvincibilityTimer>();
        this.time = time;
    }

    public void stopTimer(BattlePlayer p) {
        InvincibilityTimer it = timers.get(p);
        if (it != null) {
            it.stopTimer();
            timers.remove(p);
        }
    }

    public boolean timerRunning(BattlePlayer p) {
        return timers.containsKey(p.getName());
    }

    public void createTimer(BattlePlayer p) {
        this.stopTimer(p);
        InvincibilityTimer it = new InvincibilityTimer(p, time);
        timers.put(p, it);

    }
}
