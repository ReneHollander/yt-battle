package at.er.ytbattle.util.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import at.er.ytbattle.util.timer.TimerManager.TimeScale;

public abstract class Timeable extends TimerTask {

    private transient Timer timer;

    private TimerManager timerManager;
    private Object managerIdentifier;
    private Object identifier;
    private TimeScale timeScale;
    private int every;
    private long elapsedTime;
    private boolean running;

    public Timeable(Object managerIdentifier, TimeScale timeScale, int every) {
        this(managerIdentifier, timeScale, every, UUID.randomUUID());
    }

    public Timeable(Object managerIdentifier, TimeScale timeScale, int every, Object identifier) {
        this.managerIdentifier = managerIdentifier;
        this.timeScale = timeScale;
        this.every = every;
        this.identifier = identifier;

        this.elapsedTime = 0;
        this.running = false;
    }

    public void setTimerManager(TimerManager timerManager) {
        this.timerManager = timerManager;
    }

    public TimeScale getTimeScale() {
        return this.timeScale;
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public int getEvery() {
        return this.every;
    }

    public Object getManagerIdentifier() {
        return this.managerIdentifier;
    }

    public boolean isRunning() {
        return this.running;
    }

    public Object getIdentifier() {
        return this.identifier;
    }

    public abstract void tick(long elapsedTime);

    public void run() {
        this.tick(this.elapsedTime);
        this.elapsedTime += every;
    }

    public Timer getTimer() {
        if (this.timer == null) {
            this.timer = new Timer("timeable " + this.identifier.toString(), true);
        }
        return this.timer;
    }

    public boolean hasTimer() {
        return this.timer != null;
    }

    public void startTimer() {
        this.getTimer().scheduleAtFixedRate(this, 0, this.getTimeScale().getMultiplier() * this.getEvery());
        this.running = true;
    }

    public void removeTimer() {
        this.stopTimer();
        this.timerManager.unregisterTimer(this);
        this.timerManager = null;
        this.managerIdentifier = null;
        this.identifier = null;
        this.timeScale = null;
        this.every = 0;
        this.elapsedTime = 0;
        this.running = false;
    }

    public void stopTimer() {
        if (this.hasTimer()) {
            this.getTimer().cancel();
            this.timer = null;
        }
        this.running = false;
    }

    public void resetTimer() {
        this.stopTimer();
        this.elapsedTime = 0;
        this.running = false;
    }

    public void pauseTimer() {
        this.startTimer();
        this.running = false;
    }

    public void resumeTimer() {
        this.startTimer();
        this.running = true;
    }

}
