/*
 * package at.er.ytbattle.battle.event;
 * 
 * import java.io.Serializable;
 * 
 * import org.bukkit.entity.Player; import org.bukkit.event.EventHandler; import
 * org.bukkit.event.EventPriority; import org.bukkit.event.Listener; import
 * org.bukkit.event.block.BlockBreakEvent; import
 * org.bukkit.event.entity.EntityDamageByEntityEvent; import
 * org.bukkit.event.inventory.InventoryClickEvent; import
 * org.bukkit.event.player.PlayerDropItemEvent; import
 * org.bukkit.event.player.PlayerPickupItemEvent;
 * 
 * import at.er.ytbattle.battle.Battle;
 * 
 * public class SpectatorListener implements Listener, Serializable { private
 * static final long serialVersionUID = 1L;
 * 
 * private Battle plugin;
 * 
 * public SpectatorListener(Battle plugin) { this.plugin = plugin; }
 * 
 * @EventHandler(priority = EventPriority.HIGH) public void
 * onBlockBreak(BlockBreakEvent event) { Player player = event.getPlayer();
 * 
 * if (plugin.getGame().getSpectators().contains(player.getName())) {
 * event.setCancelled(true); player.sendMessage(Battle.prefix() +
 * "You can't break blocks in spectator mode!"); } }
 * 
 * @EventHandler(priority = EventPriority.HIGH) public void
 * onClickInventory(InventoryClickEvent event) { Player player = (Player)
 * event.getWhoClicked();
 * 
 * if (plugin.getGame().getSpectators().contains(player.getName())) {
 * event.setCancelled(true); } }
 * 
 * public void onPlayerDamage(EntityDamageByEntityEvent event) { Player damager
 * = (Player) event.getDamager();
 * 
 * if (plugin.getGame().getSpectators().contains(damager)) {
 * event.setCancelled(true); } }
 * 
 * @EventHandler(priority = EventPriority.HIGH) public void
 * onPlayerPickupItem(PlayerPickupItemEvent event) { Player player =
 * event.getPlayer();
 * 
 * if (plugin.getGame().getSpectators().contains(player.getName())) {
 * event.setCancelled(true); } }
 * 
 * @EventHandler(priority = EventPriority.HIGH) public void
 * onItemDrop(PlayerDropItemEvent event) { Player player = event.getPlayer();
 * 
 * if (plugin.getGame().getSpectators().contains(player.getName())) {
 * event.setCancelled(true); } } }
 */