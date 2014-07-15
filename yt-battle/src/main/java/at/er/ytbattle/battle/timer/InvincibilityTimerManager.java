package at.er.ytbattle.battle.timer;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;

public class InvincibilityTimerManager {

	private static InvincibilityTimerManager instance;

	private Battle b;
	private Map<String, InvincibilityTimer> timers;

	private int time;

	public InvincibilityTimerManager(Battle b, int time) {
		if (instance == null) {
			instance = this;
			this.b = b;
			this.timers = new HashMap<String, InvincibilityTimer>();
			this.time = time;
		} else {
			throw new RuntimeException("manager already running!");
		}
	}

	public void stopTimer(Player p) {
		String name = p.getName();

		InvincibilityTimer it = timers.get(name);
		if (it != null) {
			it.stopTimer();
			timers.remove(name);
		}
	}

	public boolean timerRunning(Player p) {
		return timers.containsKey(p.getName());
	}

	public void createTimer(BattlePlayer p) {
		this.stopTimer(p);
		InvincibilityTimer it = new InvincibilityTimer(this.b, p, time);
		timers.put(p.getName(), it);

	}

	public static InvincibilityTimerManager instance() {
		return instance;
	}
}
