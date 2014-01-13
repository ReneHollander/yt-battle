package at.er.ytbattle.battle.timer;

import java.io.Serializable;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Wool;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;

public class BlockPlaceTimerManager implements Listener, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Battle plugin;
	private Team team;
	private int timetoplace;

	private HashSet<BlockPlaceTimer> timers;

	public BlockPlaceTimerManager(Battle plugin, Team team, int timetoplace) {
		this.plugin = plugin;
		this.team = team;
		this.timetoplace = timetoplace;
		this.timers = new HashSet<BlockPlaceTimer>();
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
		

	}

	public void woolPlace(BlockPlaceEvent e) {
		if (!e.isCancelled()) {
			if ((this.plugin.getGame().isStarted()) && (e.getBlock().getType() == Material.WOOL)) {
				DyeColor color = ((Wool) e.getBlock().getState().getData()).getColor();
				if (color == this.team.getColor()) {
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
			}
		}
	}

	@EventHandler
	public void woolBreak(BlockBreakEvent e) {
		if ((this.plugin.getGame().isStarted()) && (e.getBlock().getType() == Material.WOOL) && (!this.team.getPlayers().contains(e.getPlayer().getName()))) {
			DyeColor color = ((Wool) e.getBlock().getState().getData()).getColor();
			if (color == this.team.getColor()) {
				BlockPlaceTimer bpt = new BlockPlaceTimer(this.plugin, this.team, this.timetoplace);
				this.timers.add(bpt);
			}
		}
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
			BlockPlaceTimer bpt = new BlockPlaceTimer(this.plugin, this.team, this.timetoplace);
			this.timers.add(bpt);
		}
	}

}
