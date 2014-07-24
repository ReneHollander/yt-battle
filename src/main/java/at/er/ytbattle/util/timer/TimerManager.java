package at.er.ytbattle.util.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TimerManager {

    private HashMap<Integer, HashSet<Timeable>> timers;

    public TimerManager() {
        this.timers = new HashMap<Integer, HashSet<Timeable>>();
    }

    public void registerTimer(Timeable timer) {
        timer.setTimerManager(this);
        this.getTimers(timer.getManagerId()).add(timer);
    }

    public void unregisterTimer(Timeable timer) {
        this.getTimers(timer.getManagerId()).remove(timer);
    }

    public ArrayList<Timeable> getAllTimers() {
        ArrayList<Timeable> timerlist = new ArrayList<Timeable>();
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timerlist.add(timeable);
            }
        }
        return timerlist;
    }

    public HashSet<Timeable> getTimers(int managerId) {
        HashSet<Timeable> hs = this.timers.get(managerId);
        if (hs == null) {
            this.timers.put(managerId, new HashSet<Timeable>());
        }
        return this.timers.get(managerId);
    }

    public void startAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.startTimer();
            }
        }
    }

    public void startTimers(int managerId) {
        for (Timeable timeable : this.getTimers(managerId)) {
            timeable.startTimer();
        }
    }

    public void stopAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.stopTimer();
            }
        }
    }

    public void stopTimers(int managerId) {
        for (Timeable timeable : this.getTimers(managerId)) {
            timeable.stopTimer();
        }
    }

    public void pauseAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.pauseTimer();
            }
        }
    }

    public void pauseTimers(int managerId) {
        for (Timeable timeable : this.getTimers(managerId)) {
            timeable.pauseTimer();
        }
    }

    public void resumeAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.resumeTimer();
            }
        }
    }

    public void resumeTimers(int managerId) {
        for (Timeable timeable : this.getTimers(managerId)) {
            timeable.resumeTimer();
        }
    }

    public void removeAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.removeTimer();
            }
        }
    }

    public void removeTimers(int managerId) {
        for (Timeable timeable : this.getTimers(managerId)) {
            timeable.removeTimer();
        }
    }

    public enum TimeScale {
        MILISECONDS(1), GAMETICK(50), REDSTONETICK(100), SECOND(1000), MINUTE(60000);

        private int multiplier;

        TimeScale(int multiplier) {
            this.multiplier = multiplier;
        }

        public int getMultiplier() {
            return this.multiplier;
        }
    }

}