package at.er.ytbattle.util.timer;

import java.util.Timer;
import java.util.UUID;

import at.er.ytbattle.util.timer.TimerManager.TimeScale;

public abstract class Timeable {

    private transient Timer timer;
    private transient CustomTimerTask customTimerTask;

    private TimerManager timerManager;
    private Object managerIdentifier;
    private Object identifier;
    protected TimeScale timeScale;
    protected int every;
    protected long elapsedTime;
    private boolean running;

    private int lastCount;

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
        this.lastCount = 0;
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
        if (this.timerManager == null)
            throw new IllegalStateException("register timer through timer manager first!");
        this.customTimerTask = new CustomTimerTask(this, this.lastCount);
        if (this.hasTimer()) {
            this.timer.cancel();
            this.timer.purge();
            this.timer = null;
        }
        this.getTimer().scheduleAtFixedRate(this.customTimerTask, 0, 50);
        this.running = true;
    }

    public void removeTimer() {
        this.stopTimer();
        this.timerManager.unregisterTimer(this);
        this.timerManager = null;
        this.customTimerTask = null;
        this.managerIdentifier = null;
        this.identifier = null;
        this.timeScale = null;
        this.every = 0;
        this.elapsedTime = 0;
        this.running = false;
        this.lastCount = 0;
    }

    public void stopTimer() {
        if (this.hasTimer()) {
            try {
                this.getTimer().cancel();
                this.getTimer().purge();
            } catch (Exception e) {
            }
            this.timer = null;
        }
        this.lastCount = 0;
        this.running = false;
    }

    public void resetTimer() {
        this.stopTimer();
        this.elapsedTime = 0;
        this.running = false;
        this.lastCount = 0;
    }

    public void pauseTimer() {
        this.stopTimer();
        this.running = false;
        this.lastCount = this.customTimerTask.getCount();
    }

    public void resumeTimer() {
        this.startTimer();
        this.running = true;
    }

}
