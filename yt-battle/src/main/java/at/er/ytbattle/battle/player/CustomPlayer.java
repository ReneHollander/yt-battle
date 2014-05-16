package at.er.ytbattle.battle.player;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

public class CustomPlayer implements Player, Externalizable {

	private UUID uuid;
	private Player player;

	public CustomPlayer(Player player) {
		this.player = player;
		this.uuid = player.getUniqueId();
	}

	@Override
	public Player getPlayer() {
		if (this.player == null) {
			this.player = Bukkit.getPlayer(this.uuid);
		}
		return this.player.getPlayer();
	}

	@Override
	public UUID getUniqueId() {
		return this.player.getUniqueId();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(this.getUniqueId().toString());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.uuid = UUID.fromString(in.readUTF());
	}

	@Override
	public void closeInventory() {
		this.player.closeInventory();
	}

	@Override
	public Inventory getEnderChest() {
		return this.player.getEnderChest();
	}

	@Override
	public int getExpToLevel() {
		return this.player.getExpToLevel();
	}

	@Override
	public GameMode getGameMode() {
		return this.player.getGameMode();
	}

	@Override
	public PlayerInventory getInventory() {
		return this.player.getInventory();
	}

	@Override
	public ItemStack getItemInHand() {
		return this.player.getItemInHand();
	}

	@Override
	public ItemStack getItemOnCursor() {
		return this.player.getItemOnCursor();
	}

	@Override
	public String getName() {
		return this.player.getName();
	}

	@Override
	public InventoryView getOpenInventory() {
		return this.player.getOpenInventory();
	}

	@Override
	public int getSleepTicks() {
		return this.player.getSleepTicks();
	}

	@Override
	public boolean isBlocking() {
		return this.player.isBlocking();
	}

	@Override
	public boolean isSleeping() {
		return this.player.isSleeping();
	}

	@Override
	public InventoryView openEnchanting(Location arg0, boolean arg1) {
		return this.player.openEnchanting(arg0, arg1);
	}

	@Override
	public InventoryView openInventory(Inventory arg0) {
		return this.player.openInventory(arg0);
	}

	@Override
	public void openInventory(InventoryView arg0) {
		this.player.openInventory(arg0);
	}

	@Override
	public InventoryView openWorkbench(Location arg0, boolean arg1) {
		return this.player.openWorkbench(arg0, arg1);
	}

	@Override
	public void setGameMode(GameMode arg0) {
		this.player.setGameMode(arg0);
	}

	@Override
	public void setItemInHand(ItemStack arg0) {
		this.player.setItemInHand(arg0);
	}

	@Override
	public void setItemOnCursor(ItemStack arg0) {
		this.player.setItemOnCursor(arg0);
	}

	@Override
	public boolean setWindowProperty(Property arg0, int arg1) {
		return this.player.setWindowProperty(arg0, arg1);
	}

	@Override
	public int _INVALID_getLastDamage() {
		return _INVALID_getLastDamage();
	}

	@Deprecated
	@Override
	public void _INVALID_setLastDamage(int arg0) {
		this.player._INVALID_setLastDamage(arg0);
	}

	@Override
	public boolean addPotionEffect(PotionEffect arg0) {
		return this.player.addPotionEffect(arg0);
	}

	@Override
	public boolean addPotionEffect(PotionEffect arg0, boolean arg1) {
		return this.player.addPotionEffect(arg0, arg1);
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> arg0) {
		return this.player.addPotionEffects(arg0);
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		return this.player.getActivePotionEffects();
	}

	@Override
	public boolean getCanPickupItems() {
		return this.player.getCanPickupItems();
	}

	@Override
	public String getCustomName() {
		return this.player.getCustomName();
	}

	@Override
	public EntityEquipment getEquipment() {
		return this.player.getEquipment();
	}

	@Override
	public double getEyeHeight() {
		return this.player.getEyeHeight();
	}

	@Override
	public double getEyeHeight(boolean arg0) {
		return this.player.getEyeHeight(arg0);
	}

	@Override
	public Location getEyeLocation() {
		return this.player.getEyeLocation();
	}

	@Override
	public Player getKiller() {
		return this.player.getKiller();
	}

	@Override
	public double getLastDamage() {
		return this.player.getLastDamage();
	}

	@Deprecated
	@Override
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> arg0, int arg1) {
		return this.player.getLastTwoTargetBlocks(arg0, arg1);
	}

	@Override
	public Entity getLeashHolder() throws IllegalStateException {
		return this.player.getLeashHolder();
	}

	@Deprecated
	@Override
	public List<Block> getLineOfSight(HashSet<Byte> arg0, int arg1) {
		return this.player.getLineOfSight(arg0, arg1);
	}

	@Override
	public int getMaximumAir() {
		return this.player.getMaximumAir();
	}

	@Override
	public int getMaximumNoDamageTicks() {
		return this.player.getMaximumNoDamageTicks();
	}

	@Override
	public int getNoDamageTicks() {
		return this.player.getNoDamageTicks();
	}

	@Override
	public int getRemainingAir() {
		return this.player.getRemainingAir();
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		return this.player.getRemoveWhenFarAway();
	}

	@Deprecated
	@Override
	public Block getTargetBlock(HashSet<Byte> arg0, int arg1) {
		return this.player.getTargetBlock(arg0, arg1);
	}

	@Override
	public boolean hasLineOfSight(Entity arg0) {
		return this.player.hasLineOfSight(arg0);
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType arg0) {
		return this.player.hasPotionEffect(arg0);
	}

	@Override
	public boolean isCustomNameVisible() {
		return this.player.isCustomNameVisible();
	}

	@Override
	public boolean isLeashed() {
		return this.player.isLeashed();
	}

	@Override
	public void removePotionEffect(PotionEffectType arg0) {
		this.player.removePotionEffect(arg0);
	}

	@Override
	public void setCanPickupItems(boolean arg0) {
		this.player.setCanPickupItems(arg0);
	}

	@Override
	public void setCustomName(String arg0) {
		this.player.setCustomName(arg0);
	}

	@Override
	public void setCustomNameVisible(boolean arg0) {
		this.player.setCustomNameVisible(arg0);
	}

	@Override
	public void setLastDamage(double arg0) {
		this.player.setLastDamage(arg0);
	}

	@Override
	public boolean setLeashHolder(Entity arg0) {
		return this.player.setLeashHolder(arg0);
	}

	@Override
	public void setMaximumAir(int arg0) {
		this.player.setMaximumAir(arg0);
	}

	@Override
	public void setMaximumNoDamageTicks(int arg0) {
		this.player.setMaximumNoDamageTicks(arg0);
	}

	@Override
	public void setNoDamageTicks(int arg0) {
		this.player.setNoDamageTicks(arg0);
	}

	@Override
	public void setRemainingAir(int arg0) {
		this.player.setRemainingAir(arg0);
	}

	@Override
	public void setRemoveWhenFarAway(boolean arg0) {
		this.player.setRemoveWhenFarAway(arg0);
	}

	@Deprecated
	@Override
	public Arrow shootArrow() {
		return this.player.shootArrow();
	}

	@Deprecated
	@Override
	public Egg throwEgg() {
		return this.player.throwEgg();
	}

	@Deprecated
	@Override
	public Snowball throwSnowball() {
		return this.player.throwSnowball();
	}

	@Override
	public boolean eject() {
		return this.player.eject();
	}

	@Override
	public int getEntityId() {
		return this.player.getEntityId();
	}

	@Override
	public float getFallDistance() {
		return this.player.getFallDistance();
	}

	@Override
	public int getFireTicks() {
		return this.player.getFireTicks();
	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		return this.player.getLastDamageCause();
	}

	@Override
	public Location getLocation() {
		return this.player.getLocation();
	}

	@Override
	public Location getLocation(Location arg0) {
		return this.player.getLocation(arg0);
	}

	@Override
	public int getMaxFireTicks() {
		return this.player.getMaxFireTicks();
	}

	@Override
	public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2) {
		return this.player.getNearbyEntities(arg0, arg1, arg2);
	}

	@Override
	public Entity getPassenger() {
		return this.player.getPassenger();
	}

	@Override
	public Server getServer() {
		return this.player.getServer();
	}

	@Override
	public int getTicksLived() {
		return this.player.getTicksLived();
	}

	@Override
	public EntityType getType() {
		return this.player.getType();
	}

	@Override
	public Entity getVehicle() {
		return this.player.getVehicle();
	}

	@Override
	public Vector getVelocity() {
		return this.player.getVelocity();
	}

	@Override
	public World getWorld() {
		return this.player.getWorld();
	}

	@Override
	public boolean isDead() {
		return this.player.isDead();
	}

	@Override
	public boolean isEmpty() {
		return this.player.isEmpty();
	}

	@Override
	public boolean isInsideVehicle() {
		return this.player.isInsideVehicle();
	}

	@Override
	public boolean isValid() {
		return this.player.isValid();
	}

	@Override
	public boolean leaveVehicle() {
		return this.player.leaveVehicle();
	}

	@Override
	public void playEffect(EntityEffect arg0) {
		this.player.playEffect(arg0);
	}

	@Override
	public void remove() {
		this.player.remove();
	}

	@Override
	public void setFallDistance(float arg0) {
		this.player.setFallDistance(arg0);
	}

	@Override
	public void setFireTicks(int arg0) {
		this.player.setFireTicks(arg0);
	}

	@Override
	public void setLastDamageCause(EntityDamageEvent arg0) {
		this.player.setLastDamageCause(arg0);
	}

	@Override
	public boolean setPassenger(Entity arg0) {
		return this.player.setPassenger(arg0);
	}

	@Override
	public void setTicksLived(int arg0) {
		this.player.setTicksLived(arg0);
	}

	@Override
	public void setVelocity(Vector arg0) {
		this.player.setVelocity(arg0);
	}

	@Override
	public boolean teleport(Location arg0) {
		return this.player.teleport(arg0);
	}

	@Override
	public boolean teleport(Entity arg0) {
		return this.player.teleport(arg0);
	}

	@Override
	public boolean teleport(Location arg0, TeleportCause arg1) {
		return this.player.teleport(arg0, arg1);
	}

	@Override
	public boolean teleport(Entity arg0, TeleportCause arg1) {
		return this.player.teleport(arg0, arg1);
	}

	@Override
	public List<MetadataValue> getMetadata(String arg0) {
		return this.player.getMetadata(arg0);
	}

	@Override
	public boolean hasMetadata(String arg0) {
		return this.player.hasMetadata(arg0);
	}

	@Override
	public void removeMetadata(String arg0, Plugin arg1) {
		this.player.removeMetadata(arg0, arg1);
	}

	@Override
	public void setMetadata(String arg0, MetadataValue arg1) {
		this.player.setMetadata(arg0, arg1);
	}

	@Deprecated
	@Override
	public void _INVALID_damage(int arg0) {
		this.player._INVALID_damage(arg0);
	}

	@Deprecated
	@Override
	public void _INVALID_damage(int arg0, Entity arg1) {
		this.player._INVALID_damage(arg0, arg1);
	}

	@Deprecated
	@Override
	public int _INVALID_getHealth() {
		return this.player._INVALID_getHealth();
	}

	@Deprecated
	@Override
	public int _INVALID_getMaxHealth() {
		return this.player._INVALID_getMaxHealth();
	}

	@Deprecated
	@Override
	public void _INVALID_setHealth(int arg0) {
		this.player._INVALID_setHealth(arg0);
	}

	@Deprecated
	@Override
	public void _INVALID_setMaxHealth(int arg0) {
		this.player._INVALID_setMaxHealth(arg0);
	}

	@Override
	public void damage(double arg0) {
		this.player.damage(arg0);
	}

	@Override
	public void damage(double arg0, Entity arg1) {
		this.player.damage(arg0, arg1);
	}

	@Override
	public double getHealth() {
		return this.player.getHealth();
	}

	@Override
	public double getMaxHealth() {
		return this.player.getMaxHealth();
	}

	@Override
	public void resetMaxHealth() {
		this.player.resetMaxHealth();
	}

	@Override
	public void setHealth(double arg0) {
		this.player.setHealth(arg0);
	}

	@Override
	public void setMaxHealth(double arg0) {
		this.player.setMaxHealth(arg0);
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0) {
		return this.player.launchProjectile(arg0);
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> arg0, Vector arg1) {
		return this.player.launchProjectile(arg0, arg1);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0) {
		return this.player.addAttachment(arg0);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
		return this.player.addAttachment(arg0, arg1);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {
		return this.player.addAttachment(arg0, arg1, arg2);
	}

	@Override
	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {
		return this.player.addAttachment(arg0, arg1, arg2, arg3);
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		return this.player.getEffectivePermissions();
	}

	@Override
	public boolean hasPermission(String arg0) {
		return this.player.hasPermission(arg0);
	}

	@Override
	public boolean hasPermission(Permission arg0) {
		return this.player.hasPermission(arg0);
	}

	@Override
	public boolean isPermissionSet(String arg0) {
		return this.player.isPermissionSet(arg0);
	}

	@Override
	public boolean isPermissionSet(Permission arg0) {
		return this.player.isPermissionSet(arg0);
	}

	@Override
	public void recalculatePermissions() {
		this.player.recalculatePermissions();
	}

	@Override
	public void removeAttachment(PermissionAttachment arg0) {
		this.player.removeAttachment(arg0);
	}

	@Override
	public boolean isOp() {
		return this.player.isOp();
	}

	@Override
	public void setOp(boolean arg0) {
		this.player.setOp(arg0);
	}

	@Override
	public void abandonConversation(Conversation arg0) {
		this.player.abandonConversation(arg0);
	}

	@Override
	public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {
		this.player.abandonConversation(arg0, arg1);
	}

	@Override
	public void acceptConversationInput(String arg0) {
		this.player.acceptConversationInput(arg0);
	}

	@Override
	public boolean beginConversation(Conversation arg0) {
		return this.player.beginConversation(arg0);
	}

	@Override
	public boolean isConversing() {
		return this.player.isConversing();
	}

	@Override
	public void sendMessage(String arg0) {
		this.player.sendMessage(arg0);
	}

	@Override
	public void sendMessage(String[] arg0) {
		this.player.sendMessage(arg0);
	}

	@Override
	public long getFirstPlayed() {
		return this.player.getFirstPlayed();
	}

	@Override
	public long getLastPlayed() {
		return this.player.getLastPlayed();
	}

	@Override
	public boolean hasPlayedBefore() {
		return this.player.hasPlayedBefore();
	}

	@Override
	public boolean isBanned() {
		return this.player.isBanned();
	}

	@Override
	public boolean isOnline() {
		return this.player.isOnline();
	}

	@Override
	public boolean isWhitelisted() {
		return this.player.isWhitelisted();
	}

	@Deprecated
	@Override
	public void setBanned(boolean arg0) {
		this.player.setBanned(arg0);
	}

	@Override
	public void setWhitelisted(boolean arg0) {
		this.player.setWhitelisted(arg0);
	}

	@Override
	public Map<String, Object> serialize() {
		return this.player.serialize();
	}

	@Override
	public Set<String> getListeningPluginChannels() {
		return this.player.getListeningPluginChannels();
	}

	@Override
	public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
		this.player.sendPluginMessage(arg0, arg1, arg2);
	}

	@Override
	public void awardAchievement(Achievement arg0) {
		this.player.awardAchievement(arg0);
	}

	@Override
	public boolean canSee(Player arg0) {
		return this.player.canSee(arg0);
	}

	@Override
	public void chat(String arg0) {
		this.player.chat(arg0);
	}

	@Override
	public void decrementStatistic(Statistic arg0) throws IllegalArgumentException {
		this.player.decrementStatistic(arg0);
	}

	@Override
	public void decrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		this.player.decrementStatistic(arg0, arg1);
	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		this.player.decrementStatistic(arg0, arg1);
	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		this.player.decrementStatistic(arg0, arg1);
	}

	@Override
	public void decrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		this.player.decrementStatistic(arg0, arg1, arg2);
	}

	@Override
	public void decrementStatistic(Statistic arg0, EntityType arg1, int arg2) {
		this.player.decrementStatistic(arg0, arg1, arg2);
	}

	@Override
	public InetSocketAddress getAddress() {
		return this.player.getAddress();
	}

	@Override
	public boolean getAllowFlight() {
		return this.player.getAllowFlight();
	}

	@Override
	public Location getBedSpawnLocation() {
		return this.player.getBedSpawnLocation();
	}

	@Override
	public Location getCompassTarget() {
		return this.player.getCompassTarget();
	}

	@Override
	public String getDisplayName() {
		return this.player.getDisplayName();
	}

	@Override
	public float getExhaustion() {
		return this.player.getExhaustion();
	}

	@Override
	public float getExp() {
		return this.player.getExp();
	}

	@Override
	public float getFlySpeed() {
		return this.player.getFlySpeed();
	}

	@Override
	public int getFoodLevel() {
		return this.player.getFoodLevel();
	}

	@Override
	public double getHealthScale() {
		return this.player.getHealthScale();
	}

	@Override
	public int getLevel() {
		return this.player.getLevel();
	}

	@Override
	public String getPlayerListName() {
		return this.player.getPlayerListName();
	}

	@Override
	public long getPlayerTime() {
		return this.player.getPlayerTime();
	}

	@Override
	public long getPlayerTimeOffset() {
		return this.player.getPlayerTimeOffset();
	}

	@Override
	public WeatherType getPlayerWeather() {
		return this.player.getPlayerWeather();
	}

	@Override
	public float getSaturation() {
		return this.player.getSaturation();
	}

	@Override
	public Scoreboard getScoreboard() {
		return this.player.getScoreboard();
	}

	@Override
	public int getStatistic(Statistic arg0) throws IllegalArgumentException {
		return this.player.getStatistic(arg0);
	}

	@Override
	public int getStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		return this.player.getStatistic(arg0, arg1);
	}

	@Override
	public int getStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		return this.player.getStatistic(arg0, arg1);
	}

	@Override
	public int getTotalExperience() {
		return this.player.getTotalExperience();
	}

	@Override
	public float getWalkSpeed() {
		return this.player.getWalkSpeed();
	}

	@Override
	public void giveExp(int arg0) {
		this.player.giveExp(arg0);
	}

	@Override
	public void giveExpLevels(int arg0) {
		this.player.giveExpLevels(arg0);
	}

	@Override
	public boolean hasAchievement(Achievement arg0) {
		return this.player.hasAchievement(arg0);
	}

	@Override
	public void hidePlayer(Player arg0) {
		this.player.hidePlayer(arg0);
	}

	@Override
	public void incrementStatistic(Statistic arg0) throws IllegalArgumentException {
		this.player.incrementStatistic(arg0);
	}

	@Override
	public void incrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		this.player.incrementStatistic(arg0, arg1);
	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
		this.player.incrementStatistic(arg0, arg1);
	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
		this.player.incrementStatistic(arg0, arg1);
	}

	@Override
	public void incrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		this.player.incrementStatistic(arg0, arg1, arg2);
	}

	@Override
	public void incrementStatistic(Statistic arg0, EntityType arg1, int arg2) throws IllegalArgumentException {
		this.player.incrementStatistic(arg0, arg1, arg2);
	}

	@Override
	public boolean isFlying() {
		return this.player.isFlying();
	}

	@Override
	public boolean isHealthScaled() {
		return this.player.isHealthScaled();
	}

	@Deprecated
	@Override
	public boolean isOnGround() {
		return this.player.isOnGround();
	}

	@Override
	public boolean isPlayerTimeRelative() {
		return this.player.isPlayerTimeRelative();
	}

	@Override
	public boolean isSleepingIgnored() {
		return this.player.isSleepingIgnored();
	}

	@Override
	public boolean isSneaking() {
		return this.player.isSneaking();
	}

	@Override
	public boolean isSprinting() {
		return this.player.isSprinting();
	}

	@Override
	public void kickPlayer(String arg0) {
		this.player.kickPlayer(arg0);
	}

	@Override
	public void loadData() {
		this.player.loadData();
	}

	@Override
	public boolean performCommand(String arg0) {
		return this.player.performCommand(arg0);
	}

	@Deprecated
	@Override
	public void playEffect(Location arg0, Effect arg1, int arg2) {
		this.player.playEffect(arg0, arg1, arg2);
	}

	@Override
	public <T> void playEffect(Location arg0, Effect arg1, T arg2) {
		this.player.playEffect(arg0, arg1, arg2);
	}

	@Deprecated
	@Override
	public void playNote(Location arg0, byte arg1, byte arg2) {
		this.player.playNote(arg0, arg1, arg2);
	}

	@Override
	public void playNote(Location arg0, Instrument arg1, Note arg2) {
		this.player.playNote(arg0, arg1, arg2);
	}

	@Override
	public void playSound(Location arg0, Sound arg1, float arg2, float arg3) {
		this.player.playSound(arg0, arg1, arg2, arg3);
	}

	@Deprecated
	@Override
	public void playSound(Location arg0, String arg1, float arg2, float arg3) {
		this.player.playSound(arg0, arg1, arg2, arg3);
	}

	@Override
	public void removeAchievement(Achievement arg0) {
		this.player.removeAchievement(arg0);
	}

	@Override
	public void resetPlayerTime() {
		this.player.resetPlayerTime();
	}

	@Override
	public void resetPlayerWeather() {
		this.player.resetPlayerWeather();
	}

	@Override
	public void saveData() {
		this.player.saveData();
	}

	@Deprecated
	@Override
	public void sendBlockChange(Location arg0, Material arg1, byte arg2) {
		this.player.sendBlockChange(arg0, arg1, arg2);
	}

	@Deprecated
	@Override
	public void sendBlockChange(Location arg0, int arg1, byte arg2) {
		this.player.sendBlockChange(arg0, arg1, arg2);
	}

	@Deprecated
	@Override
	public boolean sendChunkChange(Location arg0, int arg1, int arg2, int arg3, byte[] arg4) {
		return this.player.sendChunkChange(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void sendMap(MapView arg0) {
		this.player.sendMap(arg0);
	}

	@Override
	public void sendRawMessage(String arg0) {
		this.player.sendRawMessage(arg0);
	}

	@Override
	public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException {
		this.player.sendSignChange(arg0, arg1);
	}

	@Override
	public void setAllowFlight(boolean arg0) {
		this.player.setAllowFlight(arg0);
	}

	@Override
	public void setBedSpawnLocation(Location arg0) {
		this.player.setBedSpawnLocation(arg0);
	}

	@Override
	public void setBedSpawnLocation(Location arg0, boolean arg1) {
		this.player.setBedSpawnLocation(arg0, arg1);
	}

	@Override
	public void setCompassTarget(Location arg0) {
		this.player.setCompassTarget(arg0);
	}

	@Override
	public void setDisplayName(String arg0) {
		this.player.setDisplayName(arg0);
	}

	@Override
	public void setExhaustion(float arg0) {
		this.player.setExhaustion(arg0);
	}

	@Override
	public void setExp(float arg0) {
		this.player.setExp(arg0);
	}

	@Override
	public void setFlySpeed(float arg0) throws IllegalArgumentException {
		this.player.setFlySpeed(arg0);
	}

	@Override
	public void setFlying(boolean arg0) {
		this.player.setFlying(arg0);
	}

	@Override
	public void setFoodLevel(int arg0) {
		this.player.setFoodLevel(arg0);
	}

	@Override
	public void setHealthScale(double arg0) throws IllegalArgumentException {
		this.player.setHealthScale(arg0);
	}

	@Override
	public void setHealthScaled(boolean arg0) {
		this.player.setHealthScaled(arg0);
	}

	@Override
	public void setLevel(int arg0) {
		this.player.setLevel(arg0);
	}

	@Override
	public void setPlayerListName(String arg0) {
		this.player.setPlayerListName(arg0);
	}

	@Override
	public void setPlayerTime(long arg0, boolean arg1) {
		this.player.setPlayerTime(arg0, arg1);
	}

	@Override
	public void setPlayerWeather(WeatherType arg0) {
		this.player.setPlayerWeather(arg0);
	}

	@Override
	public void setResourcePack(String arg0) {
		this.player.setResourcePack(arg0);
	}

	@Override
	public void setSaturation(float arg0) {
		this.player.setSaturation(arg0);
	}

	@Override
	public void setScoreboard(Scoreboard arg0) throws IllegalArgumentException, IllegalStateException {
		this.player.setScoreboard(arg0);
	}

	@Override
	public void setSleepingIgnored(boolean arg0) {
		this.player.setSleepingIgnored(arg0);
	}

	@Override
	public void setSneaking(boolean arg0) {
		this.player.setSneaking(arg0);
	}

	@Override
	public void setSprinting(boolean arg0) {
		this.player.setSprinting(arg0);
	}

	@Override
	public void setStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
		this.player.setStatistic(arg0, arg1);
	}

	@Override
	public void setStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
		this.player.setStatistic(arg0, arg1, arg2);
	}

	@Override
	public void setStatistic(Statistic arg0, EntityType arg1, int arg2) {
		this.player.setStatistic(arg0, arg1, arg2);
	}

	@Deprecated
	@Override
	public void setTexturePack(String arg0) {
		this.player.setTexturePack(arg0);
	}

	@Override
	public void setTotalExperience(int arg0) {
		this.player.setTotalExperience(arg0);
	}

	@Override
	public void setWalkSpeed(float arg0) throws IllegalArgumentException {
		this.player.setWalkSpeed(arg0);
	}

	@Override
	public void showPlayer(Player arg0) {
		this.player.showPlayer(arg0);
	}

	@Deprecated
	@Override
	public void updateInventory() {
		this.player.updateInventory();
	}

}
