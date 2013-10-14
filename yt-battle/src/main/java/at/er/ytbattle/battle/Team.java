package at.er.ytbattle.battle;

import java.io.Serializable;
import java.util.ArrayList;

import at.er.ytbattle.battle.timer.BlockPlaceTimer;

public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	private Battle plugin;
	
	private ArrayList<String> players;
	private int lifes;
	
	private BlockPlaceTimer bpt;
	
	public Team(Battle b, ArrayList<String> players, int lifes) {
		this.plugin = b;
		this.players = players;
		this.lifes = lifes;
		this.bpt = new BlockPlaceTimer(plugin, this, plugin.getConfig().getInt("config.minutes-till-broken-wool-effects-appears")*60, 0);
	}
	
	public Team(Battle b, ArrayList<String> players) {
		this.plugin = b;
		this.players = players;
		this.lifes = 0;
		this.bpt = new BlockPlaceTimer(plugin, this, plugin.getConfig().getInt("config.minutes-till-broken-wool-effects-appears")*60, 0);
	}
	
	public Team(Battle b) {
		this.plugin = b;
		this.players = new ArrayList<String>();
		this.lifes = 0;
		this.bpt = new BlockPlaceTimer(plugin, this, plugin.getConfig().getInt("config.minutes-till-broken-wool-effects-appears")*60, 0);
	}

	public Battle getPlugin() {
		return plugin;
	}

	public void setPlugin(Battle plugin) {
		this.plugin = plugin;
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public BlockPlaceTimer getBlockPlaceTimer() {
		return bpt;
	}

	public void setBlockPlaceTimer(BlockPlaceTimer bpt) {
		this.bpt = bpt;
	}
	
	
}
