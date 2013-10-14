package at.er.ytbattle.battle.timer;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;

public class BlockPlaceTimer implements Runnable, Listener, Serializable {
	private static final long serialVersionUID = 1L;

	private Battle plugin;
	
	private Team team;
	
	private int time;
	private int tmpTime;
	
	private int wools;
	
	public BlockPlaceTimer(Battle b, Team t, int timeSec, int wools) {
		this.plugin = b;
		this.team = t;
		this.time = timeSec;
		this.setWools(wools);
		this.tmpTime = timeSec;
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(this, plugin);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, 20);
	}
	
	public void run() {
		if (team.getLifes() > 0) {
			if (wools > 0) {
				if (time == 0) {
					for (String s : team.getPlayers()) {
						Player p = Bukkit.getPlayer(s);
						
						p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 120, 0));
						p.sendMessage(Battle.prefix() + "Place your wool to disable this effect");
					}
				}
				
				if (time % 600 == 0 && time >= 600) {
					for (String s : team.getPlayers()) {
						Player p = Bukkit.getPlayer(s);
						
						p.sendMessage(Battle.prefix() + "You have " + time/60 + " minutes left to place your wool.");
					}
				}
				
				if (time % 60 == 0 && time >= 60 && time < 600) {
					for (String s : team.getPlayers()) {
						Player p = Bukkit.getPlayer(s);
						
						p.sendMessage(Battle.prefix() + "You have " + time/60 + " minutes left to place your wool.");
					}
				}
				
				if (time % 10 == 0 && time > 0 && time < 60) {
					for (String s : team.getPlayers()) {
						Player p = Bukkit.getPlayer(s);
						
						p.sendMessage(Battle.prefix() + "You have " + time + " seconds left to place your wool.");
					}
				}
				
				if (time > 0)
					time--;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		int blockID = event.getBlock().getTypeId();
		int blockMeta = event.getBlock().getData();

		Player player = (Player) event.getPlayer();
		
		if (!team.getPlayers().contains(player)) return;
		
		if (blockID == 35 && plugin.getGame().isStarted()
				&& plugin.getGame().getPlayers().contains(player)) {

			switch (blockMeta) {
			case 0:
				if (plugin.getGame().getWhite().getPlayers().contains(player)) {
					if (wools > 0) {
						wools--;
						for (String s : team.getPlayers()) {
							Player p = Bukkit.getPlayer(s);
							
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
							p.sendMessage(Battle.prefix() + "Wool was placed. Place " + wools + " more to disable the timer.");
							time = tmpTime;
						}
					}
				}
				break;
			case 4:
				if (plugin.getGame().getYellow().getPlayers().contains(player)) {
					if (wools > 0) {
						wools--;
						for (String s : team.getPlayers()) {
							Player p = Bukkit.getPlayer(s);
							
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
							p.sendMessage(Battle.prefix() + "Wool was placed. Place " + wools + " more to disable the timer.");
							time = tmpTime;
						}
					}
				}
				break;
			case 5:
				if (plugin.getGame().getGreen().getPlayers().contains(player)) {
					if (wools > 0) {
						wools--;
						for (String s : team.getPlayers()) {
							Player p = Bukkit.getPlayer(s);
							
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
							p.sendMessage(Battle.prefix() + "Wool was placed. Place " + wools + " more to disable the timer.");
							time = tmpTime;
						}
					}
				}
				break;
			case 9:
				if (plugin.getGame().getCyan().getPlayers().contains(player)) {
					if (wools > 0) {
						wools--;
						for (String s : team.getPlayers()) {
							Player p = Bukkit.getPlayer(s);
							
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
							p.sendMessage(Battle.prefix() + "Wool was placed. Place " + wools + " more to disable the timer.");
							time = tmpTime;
						}
					}
				}
				break;
			case 10:
				if (plugin.getGame().getPurple().getPlayers().contains(player)) {
					if (wools > 0) {
						wools--;
						for (String s : team.getPlayers()) {
							Player p = Bukkit.getPlayer(s);
							
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
							p.sendMessage(Battle.prefix() + "Wool was placed. Place " + wools + " more to disable the timer.");
							time = tmpTime;
						}
					}
				}
				break;
			case 11:
				if (plugin.getGame().getBlue().getPlayers().contains(player)) {
					if (wools > 0) {
						wools--;
						for (String s : team.getPlayers()) {
							Player p = Bukkit.getPlayer(s);
							
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
							p.sendMessage(Battle.prefix() + "Wool was placed. Place " + wools + " more to disable the timer.");
							time = tmpTime;
						}
					}
				}
				break;
			case 14:
				if (plugin.getGame().getRed().getPlayers().contains(player)) {
					if (wools > 0) {
						wools--;
						for (String s : team.getPlayers()) {
							Player p = Bukkit.getPlayer(s);
							
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
							p.sendMessage(Battle.prefix() + "Wool was placed. Place " + wools + " more to disable the timer.");
							time = tmpTime;
						}
					}
				}
				break;
			case 15:
				if (plugin.getGame().getBlack().getPlayers().contains(player)) {
					if (wools > 0) {
						wools--;
						for (String s : team.getPlayers()) {
							Player p = Bukkit.getPlayer(s);
							
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
							p.sendMessage(Battle.prefix() + "Wool was placed. Place " + wools + " more to disable the timer.");
							time = tmpTime;
						}
					}
				}
				break;
			}
		}
	}
	
	public ArrayList<String> getPlayers() {
		return team.getPlayers();
	}

	public int getWools() {
		return wools;
	}

	public void setWools(int wools) {
		this.wools = wools;
	}
}
