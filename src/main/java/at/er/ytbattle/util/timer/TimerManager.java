package at.er.ytbattle.util.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimerManager {

	private HashMap<Object, HashMap<Object, Timeable>> timers;

	public TimerManager() {
		this.timers = new HashMap<Object, HashMap<Object, Timeable>>();
	}

	public void registerTimer(Timeable timer) {
		timer.setTimerManager(this);
		this.getTimers(timer.getManagerIdentifier()).put(timer.getIdentifier(), timer);
	}

	public void unregisterTimer(Timeable timer) {
		this.getTimers(timer.getManagerIdentifier()).remove(timer.getIdentifier());
	}

	public Timeable getTimer(Object managerIdentifier, Object identifier) {
		return this.getTimers(managerIdentifier).get(identifier);
	}

	public boolean hasTimer(Object managerIdentifier, Object identifier) {
		return this.getTimers(managerIdentifier).containsKey(identifier);
	}

	public boolean hasTimer(Object managerIdentifier, Timeable timeable) {
		return this.getTimers(managerIdentifier).containsValue(timeable);
	}

	public void removeTimer(Object managerIdentifier, Object identifier) {
		Timeable t = this.getTimer(managerIdentifier, identifier);
		if (t != null) {
			t.removeTimer();
		}
	}

	public int getSize(Object managerIdentifier) {
		return this.getTimers(managerIdentifier).size();
	}

	public Timeable findLongestRunningTimer(Object managerIdentifier) {
		long ref = 0;
		Timeable longestRunning = null;
		for (Timeable timeable : this.getTimersAsList(managerIdentifier)) {
			if (timeable.getElapsedTime() >= ref) {
				longestRunning = timeable;
				ref = timeable.getElapsedTime();
			}
		}
		return longestRunning;
	}

	public ArrayList<Timeable> getAllTimers() {
		ArrayList<Timeable> timerlist = new ArrayList<Timeable>();
		for (Map.Entry<Object, HashMap<Object, Timeable>> entry1 : this.timers.entrySet()) {
			for (Map.Entry<Object, Timeable> entry2 : entry1.getValue().entrySet()) {
				timerlist.add(entry2.getValue());
			}
		}
		return timerlist;
	}

	public HashMap<Object, Timeable> getTimers(Object managerIdentifier) {
		HashMap<Object, Timeable> hs = this.timers.get(managerIdentifier);
		if (hs == null) {
			this.timers.put(managerIdentifier, new HashMap<Object, Timeable>());
		}
		return this.timers.get(managerIdentifier);
	}

	public ArrayList<Timeable> getTimersAsList(Object managerIdentifier) {
		ArrayList<Timeable> timerlist = new ArrayList<Timeable>();
		HashMap<Object, Timeable> hs = this.timers.get(managerIdentifier);
		if (hs == null) {
			this.timers.put(managerIdentifier, new HashMap<Object, Timeable>());
		} else {
			for (Map.Entry<Object, Timeable> entry : hs.entrySet()) {
				timerlist.add(entry.getValue());
			}
		}
		return timerlist;
	}

	public void startAllTimers() {
		for (Timeable timeable : this.getAllTimers()) {
			timeable.startTimer();
		}
	}

	public void startTimers(Object managerIdentifier) {
		for (Timeable timeable : this.getTimersAsList(managerIdentifier)) {
			timeable.startTimer();
		}
	}

	public void stopAllTimers() {
		for (Timeable timeable : this.getAllTimers()) {
			timeable.stopTimer();
		}
	}

	public void stopTimers(Object managerIdentifier) {
		for (Timeable timeable : this.getTimersAsList(managerIdentifier)) {
			timeable.stopTimer();
		}
	}

	public void pauseAllTimers() {
		for (Timeable timeable : this.getAllTimers()) {
			timeable.pauseTimer();
		}
	}

	public void pauseTimers(Object managerIdentifier) {
		for (Timeable timeable : this.getTimersAsList(managerIdentifier)) {
			timeable.pauseTimer();
		}
	}

	public void resumeAllTimers() {
		for (Timeable timeable : this.getAllTimers()) {
			timeable.resumeTimer();
		}
	}

	public void resumeTimers(Object managerIdentifier) {
		for (Timeable timeable : this.getTimersAsList(managerIdentifier)) {
			timeable.resumeTimer();
		}
	}

	public void removeAllTimers() {
		for (Timeable timeable : this.getAllTimers()) {
			timeable.removeTimer();
		}
	}

	public void removeTimers(Object managerIdentifier) {
		for (Timeable timeable : this.getTimersAsList(managerIdentifier)) {
			timeable.removeTimer();
		}
	}

	public enum TimeScale {
		GAMETICK(1), REDSTONETICK(2), SECOND(20), MINUTE(1200);

		private int multiplier;

		TimeScale(int multiplier) {
			this.multiplier = multiplier;
		}

		public int getMultiplier() {
			return this.multiplier;
		}
	}

}