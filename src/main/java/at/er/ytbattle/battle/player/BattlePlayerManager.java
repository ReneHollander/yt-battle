package at.er.ytbattle.battle.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BattlePlayerManager implements Listener, Serializable {

    private static final long serialVersionUID = 1123813768710789814L;

    private transient static BattlePlayerManager instance;

    private Map<UUID, BattlePlayer> players;

    public BattlePlayerManager(JavaPlugin plugin) {
        instance = this;

        this.players = new HashMap<UUID, BattlePlayer>();

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        for (Player p : Bukkit.getOnlinePlayers()) {
            BattlePlayer battlePlayer = new BattlePlayer(p);

            this.players.put(p.getUniqueId(), battlePlayer);
        }
    }

    public BattlePlayer getBattlePlayer(UUID uuid) {
        return this.players.get(uuid);
    }

    public BattlePlayer getBattlePlayer(Player player) {
        return this.getBattlePlayer(player.getUniqueId());
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

        this.players.put(player.getUniqueId(), battlePlayer);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        this.players.remove(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerKick(PlayerKickEvent event) {
        Player player = event.getPlayer();

        this.players.remove(player.getUniqueId());
    }

    public static BattlePlayerManager instance() {
        return instance;
    }

    public static void setInstance(BattlePlayerManager newInstance) {
        instance = newInstance;
    }
}
