package at.er.ytbattle.util.timer;

import java.util.HashSet;

public class TimerManager {

    private HashSet<Timeable> timers;

    public TimerManager() {
        this.timers = new HashSet<Timeable>();
    }

    public void registerTimer(Timeable timer) {
        timer.setTimerManager(this);
        this.timers.add(timer);
    }

    public void unregisterTimer(Timeable timer) {
        this.timers.remove(timer);
    }

    public void startAllTimers() {
        for (Timeable t : this.timers) {
            t.startTimer();
        }
    }

    public void stopAllTimers() {
        for (Timeable t : this.timers) {
            t.stopTimer();
        }
    }

    public void pauseAllTimers() {
        for (Timeable t : this.timers) {
            t.pauseTimer();
        }
    }

    public void resumeAllTimers() {
        for (Timeable t : this.timers) {
            t.resumeTimer();
        }
    }

    public void removeAllTimers() {
        for (Timeable t : this.timers) {
            t.removeTimer();
        }
    }

    enum TimeScale {
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