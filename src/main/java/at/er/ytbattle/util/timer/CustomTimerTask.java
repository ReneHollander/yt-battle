package at.er.ytbattle.util.timer;

import java.util.TimerTask;

public class CustomTimerTask extends TimerTask {

    private Timeable timeable;
    private int multiplier;

    private int count;

    private boolean firstRun;

    public CustomTimerTask(Timeable timeable, int count) {
        this.timeable = timeable;
        this.count = count;
        this.multiplier = this.timeable.timeScale.getMultiplier();

        this.firstRun = true;
    }

    @Override
    public void run() {
        if (firstRun == true) {
            if (this.timeable.elapsedTime == 0) {
                this.tickTimeable(0);
            }
            firstRun = false;
        }
        if (multiplier == 1 && firstRun == false) {
            this.tickTimeable(this.timeable.elapsedTime);
            this.timeable.elapsedTime += this.timeable.every;
        } else {
            if (count >= multiplier) {
                count = 0;
                this.tickTimeable(this.timeable.elapsedTime);
                this.timeable.elapsedTime += this.timeable.every;
            }
            count++;
        }
    }

    private void tickTimeable(long elapsedTime) {
        try {
            this.timeable.tick(elapsedTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return this.count;
    }
}
