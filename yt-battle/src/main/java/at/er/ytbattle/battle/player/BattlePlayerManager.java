package at.er.ytbattle.battle.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BattlePlayerManager implements Listener {

	private static BattlePlayerManager instance;

	private Map<Player, BattlePlayer> players;

	public BattlePlayerManager(JavaPlugin plugin) {
		instance = this;

		this.players = new HashMap<Player, BattlePlayer>();

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public BattlePlayer getBattlePlayer(Player player) {
		return this.players.get(player);
	}

	public List<BattlePlayer> getAllBattlePlayers() {
		return new ArrayList<BattlePlayer>(this.players.values());
	}

	public int getBattlePlayerCount() {
		return this.players.size();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		BattlePlayer battlePlayer = new BattlePlayer(player);

		this.players.put(player, battlePlayer);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		this.players.remove(player);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerKick(PlayerKickEvent event) {
		Player player = event.getPlayer();

		this.players.remove(player);
	}

	public static BattlePlayerManager instance() {
		return instance;
	}

}
