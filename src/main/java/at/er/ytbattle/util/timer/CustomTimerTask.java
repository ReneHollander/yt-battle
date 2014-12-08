package at.er.ytbattle.util.timer;

import java.util.TimerTask;

public class CustomTimerTask extends TimerTask {

	private Timeable timeable;
	private int every;
	private int count;

	private boolean firstRun;

	public CustomTimerTask(Timeable timeable, int count) {
		this.timeable = timeable;
		this.count = count;
		this.every = this.timeable.timeScale.getMultiplier() * this.timeable.getEvery();

		this.firstRun = this.timeable.elapsedTime == 0 && this.count == 0;
	}

	@Override
	public void run() {
		if (this.firstRun) {
			this.tickTimeable(this.timeable.elapsedTime);
			this.firstRun = false;
		} else {
			if (count >= every) {
				count = 0;
				this.timeable.elapsedTime += this.timeable.every;
				this.tickTimeable(this.timeable.elapsedTime);
			}
		}
		count++;
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
