package at.er.ytbattle.battle.player;

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

import at.er.ytbattle.battle.Battle;

public class BattlePlayerManager implements Listener {

    private Map<UUID, BattlePlayer> players;

    public BattlePlayerManager() {
        this.players = new HashMap<UUID, BattlePlayer>();

        Battle.instance().getServer().getPluginManager().registerEvents(this, Battle.instance());

        for (Player p : Bukkit.getOnlinePlayers()) {
            BattlePlayer battlePlayer = new BattlePlayer(p);

            this.players.put(p.getUniqueId(), battlePlayer);
        }
    }

    private Object readResolve() {
        Battle.instance().getServer().getPluginManager().registerEvents(this, Battle.instance());
        return this;
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
}
