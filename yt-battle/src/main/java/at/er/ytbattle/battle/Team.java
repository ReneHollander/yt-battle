package at.er.ytbattle.battle;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.DyeColor;

import at.er.ytbattle.battle.timer.BlockPlaceTimerManager;

public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	private Battle plugin;

	private ArrayList<String> players;
	private int lifes;

	private BlockPlaceTimerManager bptm;

	private DyeColor color;

	public Team(Battle b, ArrayList<String> players, int lifes, DyeColor color) {
		this.color = color;
		this.plugin = b;
		this.players = players;
		this.lifes = lifes;
		this.bptm = new BlockPlaceTimerManager(plugin, this, plugin.getConfig().getInt("config.minutes-till-broken-wool-effects-appears") * 60);
	}

	public Team(Battle b, ArrayList<String> players, DyeColor color) {
		this.color = color;
		this.plugin = b;
		this.players = players;
		this.lifes = 0;
		this.bptm = new BlockPlaceTimerManager(plugin, this, plugin.getConfig().getInt("config.minutes-till-broken-wool-effects-appears") * 60);
	}

	public Team(Battle b, DyeColor color) {
		this.color = color;
		this.plugin = b;
		this.players = new ArrayList<String>();
		this.lifes = 0;
		this.bptm = new BlockPlaceTimerManager(plugin, this, plugin.getConfig().getInt("config.minutes-till-broken-wool-effects-appears") * 60);
	}
	
	public void setupInitialWool() {
		this.bptm.setupInitialWool();
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
	
	public int getWoolsToPlace() {
		return this.bptm.getRemainingWoolCount();
	}

	public DyeColor getColor() {
		return this.color;
	}
	
	public BlockPlaceTimerManager getManager() {
		return this.bptm;
	}

}
