package at.er.ytbattle.battle.timer;

import java.io.Serializable;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;

public class BlockPlaceTimerManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private Team team;
	private int timetoplace;

	private HashSet<BlockPlaceTimer> timers;

	public BlockPlaceTimerManager(Team team, int timetoplace) {
		this.team = team;
		this.timetoplace = timetoplace;
		this.timers = new HashSet<BlockPlaceTimer>();
	}

	public void woolPlace() {
		if (this.timers.size() > 0) {
			BlockPlaceTimer bpt = this.findFirstTimer();
			bpt.stopCountdown();
			this.timers.remove(bpt);
			for (String s : team.getPlayers()) {
				Player p = Bukkit.getPlayer(s);
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
				p.sendMessage(Battle.prefix() + "Wool was placed. Place " + this.getRemainingWoolCount() + " more to disable the timer.");
			}
		}
	}

	public void woolBreak() {
		BlockPlaceTimer bpt = new BlockPlaceTimer(Battle.BATTLE, this.team, this.timetoplace);
		this.timers.add(bpt);
	}

	private BlockPlaceTimer findFirstTimer() {
		int ref = this.timetoplace;
		BlockPlaceTimer last = null;
		for (BlockPlaceTimer bpt : timers) {
			if (bpt.getTime() <= ref) {
				last = bpt;
				ref = bpt.getTime();
			}
		}
		return last;
	}

	public int getRemainingWoolCount() {
		return this.timers.size();
	}

	public void setupInitialWool() {
		for (int i = 0; i < this.team.getPlayers().size(); i++) {
			BlockPlaceTimer bpt = new BlockPlaceTimer(Battle.BATTLE, this.team, this.timetoplace);
			this.timers.add(bpt);
		}
	}

}
