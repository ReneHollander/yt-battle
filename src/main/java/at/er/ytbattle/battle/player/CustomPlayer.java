package at.er.ytbattle.battle.player;

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

public class CustomPlayer implements Player {

    private UUID uuid;
    private String lastValidName;

    private transient Player player;

    public CustomPlayer(Player player) {
        this.player = player;
        this.uuid = UUID.fromString(player.getUniqueId().toString());

        this.lastValidName = player.getName();
    }

    @Override
    public Player getPlayer() {
        if (this.player == null) {
            Player player = Bukkit.getPlayer(this.uuid);
            if (player != null) {
                this.player = player;
                this.uuid = player.getUniqueId();
                this.lastValidName = player.getName();
            } else {
                throw new RuntimeException("no player for uuid: " + this.uuid.toString() + " with last valid name: " + this.lastValidName + " found! maybe he is offline!");
            }
        }
        return this.player;
    }

    @Override
    public UUID getUniqueId() {
        try {
            this.uuid = this.getPlayer().getUniqueId();
        } catch (Exception e) {
        }
        return this.uuid;
    }

    @Override
    public String getName() {
        return this.getLastValidName();
    }

    public boolean hasPlayer() {
        return Bukkit.getPlayer(this.getUniqueId()) == null ? false : true;
    }

    public boolean isLoaded() {
        return this.player != null;
    }

    public String getLastValidName() {
        if (this.isLoaded())
            this.lastValidName = this.getPlayer().getName();
        return this.lastValidName;
    }

    public void store() {
        this.player = null;
    }

    @Override
    public void closeInventory() {
        if (this.isLoaded())
            this.getPlayer().closeInventory();
    }

    @Override
    public Inventory getEnderChest() {
        return this.getPlayer().getEnderChest();
    }

    @Override
    public int getExpToLevel() {
        return this.getPlayer().getExpToLevel();
    }

    @Override
    public GameMode getGameMode() {
        return this.getPlayer().getGameMode();
    }

    @Override
    public PlayerInventory getInventory() {
        return this.getPlayer().getInventory();
    }

    @Override
    public ItemStack getItemInHand() {
        return this.getPlayer().getItemInHand();
    }

    @Override
    public ItemStack getItemOnCursor() {
        return this.getPlayer().getItemOnCursor();
    }

    @Override
    public InventoryView getOpenInventory() {
        return this.getPlayer().getOpenInventory();
    }

    @Override
    public int getSleepTicks() {
        return this.getPlayer().getSleepTicks();
    }

    @Override
    public boolean isBlocking() {
        return this.getPlayer().isBlocking();
    }

    @Override
    public boolean isSleeping() {
        return this.getPlayer().isSleeping();
    }

    @Override
    public InventoryView openEnchanting(Location arg0, boolean arg1) {
        return this.getPlayer().openEnchanting(arg0, arg1);
    }

    @Override
    public InventoryView openInventory(Inventory arg0) {
        return this.getPlayer().openInventory(arg0);
    }

    @Override
    public void openInventory(InventoryView arg0) {
        if (this.isLoaded())
            this.getPlayer().openInventory(arg0);
    }

    @Override
    public InventoryView openWorkbench(Location arg0, boolean arg1) {
        return this.getPlayer().openWorkbench(arg0, arg1);
    }

    @Override
    public void setGameMode(GameMode arg0) {
        if (this.isLoaded())
            this.getPlayer().setGameMode(arg0);
    }

    @Override
    public void setItemInHand(ItemStack arg0) {
        if (this.isLoaded())
            this.getPlayer().setItemInHand(arg0);
    }

    @Override
    public void setItemOnCursor(ItemStack arg0) {
        if (this.isLoaded())
            this.getPlayer().setItemOnCursor(arg0);
    }

    @Override
    public boolean setWindowProperty(Property arg0, int arg1) {
        return this.getPlayer().setWindowProperty(arg0, arg1);
    }

    @Override
    public int _INVALID_getLastDamage() {
        return _INVALID_getLastDamage();
    }

    @Deprecated
    @Override
    public void _INVALID_setLastDamage(int arg0) {
        if (this.isLoaded())
            this.getPlayer()._INVALID_setLastDamage(arg0);
    }

    @Override
    public boolean addPotionEffect(PotionEffect arg0) {
        return this.getPlayer().addPotionEffect(arg0);
    }

    @Override
    public boolean addPotionEffect(PotionEffect arg0, boolean arg1) {
        return this.getPlayer().addPotionEffect(arg0, arg1);
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> arg0) {
        return this.getPlayer().addPotionEffects(arg0);
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return this.getPlayer().getActivePotionEffects();
    }

    @Override
    public boolean getCanPickupItems() {
        return this.getPlayer().getCanPickupItems();
    }

    @Override
    public String getCustomName() {
        return this.getPlayer().getCustomName();
    }

    @Override
    public EntityEquipment getEquipment() {
        return this.getPlayer().getEquipment();
    }

    @Override
    public double getEyeHeight() {
        return this.getPlayer().getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean arg0) {
        return this.getPlayer().getEyeHeight(arg0);
    }

    @Override
    public Location getEyeLocation() {
        return this.getPlayer().getEyeLocation();
    }

    @Override
    public Player getKiller() {
        return this.getPlayer().getKiller();
    }

    @Override
    public double getLastDamage() {
        return this.getPlayer().getLastDamage();
    }

    @Deprecated
    @Override
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> arg0, int arg1) {
        return this.getPlayer().getLastTwoTargetBlocks(arg0, arg1);
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return this.getPlayer().getLeashHolder();
    }

    @Deprecated
    @Override
    public List<Block> getLineOfSight(HashSet<Byte> arg0, int arg1) {
        return this.getPlayer().getLineOfSight(arg0, arg1);
    }

    @Override
    public int getMaximumAir() {
        return this.getPlayer().getMaximumAir();
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return this.getPlayer().getMaximumNoDamageTicks();
    }

    @Override
    public int getNoDamageTicks() {
        return this.getPlayer().getNoDamageTicks();
    }

    @Override
    public int getRemainingAir() {
        return this.getPlayer().getRemainingAir();
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return this.getPlayer().getRemoveWhenFarAway();
    }

    @Deprecated
    @Override
    public Block getTargetBlock(HashSet<Byte> arg0, int arg1) {
        return this.getPlayer().getTargetBlock(arg0, arg1);
    }

    @Override
    public boolean hasLineOfSight(Entity arg0) {
        return this.getPlayer().hasLineOfSight(arg0);
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType arg0) {
        return this.getPlayer().hasPotionEffect(arg0);
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.getPlayer().isCustomNameVisible();
    }

    @Override
    public boolean isLeashed() {
        return this.getPlayer().isLeashed();
    }

    @Override
    public void removePotionEffect(PotionEffectType arg0) {
        if (this.isLoaded())
            this.getPlayer().removePotionEffect(arg0);
    }

    @Override
    public void setCanPickupItems(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setCanPickupItems(arg0);
    }

    @Override
    public void setCustomName(String arg0) {
        if (this.isLoaded())
            this.getPlayer().setCustomName(arg0);
    }

    @Override
    public void setCustomNameVisible(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setCustomNameVisible(arg0);
    }

    @Override
    public void setLastDamage(double arg0) {
        if (this.isLoaded())
            this.getPlayer().setLastDamage(arg0);
    }

    @Override
    public boolean setLeashHolder(Entity arg0) {
        return this.getPlayer().setLeashHolder(arg0);
    }

    @Override
    public void setMaximumAir(int arg0) {
        if (this.isLoaded())
            this.getPlayer().setMaximumAir(arg0);
    }

    @Override
    public void setMaximumNoDamageTicks(int arg0) {
        if (this.isLoaded())
            this.getPlayer().setMaximumNoDamageTicks(arg0);
    }

    @Override
    public void setNoDamageTicks(int arg0) {
        if (this.isLoaded())
            this.getPlayer().setNoDamageTicks(arg0);
    }

    @Override
    public void setRemainingAir(int arg0) {
        if (this.isLoaded())
            this.getPlayer().setRemainingAir(arg0);
    }

    @Override
    public void setRemoveWhenFarAway(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setRemoveWhenFarAway(arg0);
    }

    @Deprecated
    @Override
    public Arrow shootArrow() {
        return this.getPlayer().shootArrow();
    }

    @Deprecated
    @Override
    public Egg throwEgg() {
        return this.getPlayer().throwEgg();
    }

    @Deprecated
    @Override
    public Snowball throwSnowball() {
        return this.getPlayer().throwSnowball();
    }

    @Override
    public boolean eject() {
        return this.getPlayer().eject();
    }

    @Override
    public int getEntityId() {
        return this.getPlayer().getEntityId();
    }

    @Override
    public float getFallDistance() {
        return this.getPlayer().getFallDistance();
    }

    @Override
    public int getFireTicks() {
        return this.getPlayer().getFireTicks();
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return this.getPlayer().getLastDamageCause();
    }

    @Override
    public Location getLocation() {
        return this.getPlayer().getLocation();
    }

    @Override
    public Location getLocation(Location arg0) {
        return this.getPlayer().getLocation(arg0);
    }

    @Override
    public int getMaxFireTicks() {
        return this.getPlayer().getMaxFireTicks();
    }

    @Override
    public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2) {
        return this.getPlayer().getNearbyEntities(arg0, arg1, arg2);
    }

    @Override
    public Entity getPassenger() {
        return this.getPlayer().getPassenger();
    }

    @Override
    public Server getServer() {
        return this.getPlayer().getServer();
    }

    @Override
    public int getTicksLived() {
        return this.getPlayer().getTicksLived();
    }

    @Override
    public EntityType getType() {
        return this.getPlayer().getType();
    }

    @Override
    public Entity getVehicle() {
        return this.getPlayer().getVehicle();
    }

    @Override
    public Vector getVelocity() {
        return this.getPlayer().getVelocity();
    }

    @Override
    public World getWorld() {
        return this.getPlayer().getWorld();
    }

    @Override
    public boolean isDead() {
        return this.getPlayer().isDead();
    }

    @Override
    public boolean isEmpty() {
        return this.getPlayer().isEmpty();
    }

    @Override
    public boolean isInsideVehicle() {
        return this.getPlayer().isInsideVehicle();
    }

    @Override
    public boolean isValid() {
        return this.getPlayer().isValid();
    }

    @Override
    public boolean leaveVehicle() {
        return this.getPlayer().leaveVehicle();
    }

    @Override
    public void playEffect(EntityEffect arg0) {
        if (this.isLoaded())
            this.getPlayer().playEffect(arg0);
    }

    @Override
    public void remove() {
        if (this.isLoaded())
            this.getPlayer().remove();
    }

    @Override
    public void setFallDistance(float arg0) {
        if (this.isLoaded())
            this.getPlayer().setFallDistance(arg0);
    }

    @Override
    public void setFireTicks(int arg0) {
        if (this.isLoaded())
            this.getPlayer().setFireTicks(arg0);
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent arg0) {
        if (this.isLoaded())
            this.getPlayer().setLastDamageCause(arg0);
    }

    @Override
    public boolean setPassenger(Entity arg0) {
        return this.getPlayer().setPassenger(arg0);
    }

    @Override
    public void setTicksLived(int arg0) {
        if (this.isLoaded())
            this.getPlayer().setTicksLived(arg0);
    }

    @Override
    public void setVelocity(Vector arg0) {
        if (this.isLoaded())
            this.getPlayer().setVelocity(arg0);
    }

    @Override
    public boolean teleport(Location arg0) {
        return this.getPlayer().teleport(arg0);
    }

    @Override
    public boolean teleport(Entity arg0) {
        return this.getPlayer().teleport(arg0);
    }

    @Override
    public boolean teleport(Location arg0, TeleportCause arg1) {
        return this.getPlayer().teleport(arg0, arg1);
    }

    @Override
    public boolean teleport(Entity arg0, TeleportCause arg1) {
        return this.getPlayer().teleport(arg0, arg1);
    }

    @Override
    public List<MetadataValue> getMetadata(String arg0) {
        return this.getPlayer().getMetadata(arg0);
    }

    @Override
    public boolean hasMetadata(String arg0) {
        return this.getPlayer().hasMetadata(arg0);
    }

    @Override
    public void removeMetadata(String arg0, Plugin arg1) {
        if (this.isLoaded())
            this.getPlayer().removeMetadata(arg0, arg1);
    }

    @Override
    public void setMetadata(String arg0, MetadataValue arg1) {
        if (this.isLoaded())
            this.getPlayer().setMetadata(arg0, arg1);
    }

    @Deprecated
    @Override
    public void _INVALID_damage(int arg0) {
        if (this.isLoaded())
            this.getPlayer()._INVALID_damage(arg0);
    }

    @Deprecated
    @Override
    public void _INVALID_damage(int arg0, Entity arg1) {
        if (this.isLoaded())
            this.getPlayer()._INVALID_damage(arg0, arg1);
    }

    @Deprecated
    @Override
    public int _INVALID_getHealth() {
        return this.getPlayer()._INVALID_getHealth();
    }

    @Deprecated
    @Override
    public int _INVALID_getMaxHealth() {
        return this.getPlayer()._INVALID_getMaxHealth();
    }

    @Deprecated
    @Override
    public void _INVALID_setHealth(int arg0) {
        if (this.isLoaded())
            this.getPlayer()._INVALID_setHealth(arg0);
    }

    @Deprecated
    @Override
    public void _INVALID_setMaxHealth(int arg0) {
        if (this.isLoaded())
            this.getPlayer()._INVALID_setMaxHealth(arg0);
    }

    @Override
    public void damage(double arg0) {
        if (this.isLoaded())
            this.getPlayer().damage(arg0);
    }

    @Override
    public void damage(double arg0, Entity arg1) {
        if (this.isLoaded())
            this.getPlayer().damage(arg0, arg1);
    }

    @Override
    public double getHealth() {
        return this.getPlayer().getHealth();
    }

    @Override
    public double getMaxHealth() {
        return this.getPlayer().getMaxHealth();
    }

    @Override
    public void resetMaxHealth() {
        if (this.isLoaded())
            this.getPlayer().resetMaxHealth();
    }

    @Override
    public void setHealth(double arg0) {
        if (this.isLoaded())
            this.getPlayer().setHealth(arg0);
    }

    @Override
    public void setMaxHealth(double arg0) {
        if (this.isLoaded())
            this.getPlayer().setMaxHealth(arg0);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> arg0) {
        return this.getPlayer().launchProjectile(arg0);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> arg0, Vector arg1) {
        return this.getPlayer().launchProjectile(arg0, arg1);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0) {
        return this.getPlayer().addAttachment(arg0);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0, int arg1) {
        return this.getPlayer().addAttachment(arg0, arg1);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) {
        return this.getPlayer().addAttachment(arg0, arg1, arg2);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) {
        return this.getPlayer().addAttachment(arg0, arg1, arg2, arg3);
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return this.getPlayer().getEffectivePermissions();
    }

    @Override
    public boolean hasPermission(String arg0) {
        return this.getPlayer().hasPermission(arg0);
    }

    @Override
    public boolean hasPermission(Permission arg0) {
        return this.getPlayer().hasPermission(arg0);
    }

    @Override
    public boolean isPermissionSet(String arg0) {
        return this.getPlayer().isPermissionSet(arg0);
    }

    @Override
    public boolean isPermissionSet(Permission arg0) {
        return this.getPlayer().isPermissionSet(arg0);
    }

    @Override
    public void recalculatePermissions() {
        if (this.isLoaded())
            this.getPlayer().recalculatePermissions();
    }

    @Override
    public void removeAttachment(PermissionAttachment arg0) {
        if (this.isLoaded())
            this.getPlayer().removeAttachment(arg0);
    }

    @Override
    public boolean isOp() {
        return this.getPlayer().isOp();
    }

    @Override
    public void setOp(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setOp(arg0);
    }

    @Override
    public void abandonConversation(Conversation arg0) {
        if (this.isLoaded())
            this.getPlayer().abandonConversation(arg0);
    }

    @Override
    public void abandonConversation(Conversation arg0, ConversationAbandonedEvent arg1) {
        if (this.isLoaded())
            this.getPlayer().abandonConversation(arg0, arg1);
    }

    @Override
    public void acceptConversationInput(String arg0) {
        if (this.isLoaded())
            this.getPlayer().acceptConversationInput(arg0);
    }

    @Override
    public boolean beginConversation(Conversation arg0) {
        return this.getPlayer().beginConversation(arg0);
    }

    @Override
    public boolean isConversing() {
        return this.getPlayer().isConversing();
    }

    @Override
    public void sendMessage(String arg0) {
        if (this.isLoaded())
            this.getPlayer().sendMessage(arg0);
    }

    @Override
    public void sendMessage(String[] arg0) {
        if (this.isLoaded())
            this.getPlayer().sendMessage(arg0);
    }

    @Override
    public long getFirstPlayed() {
        return this.getPlayer().getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return this.getPlayer().getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return this.getPlayer().hasPlayedBefore();
    }

    @Override
    public boolean isBanned() {
        return this.getPlayer().isBanned();
    }

    @Override
    public boolean isOnline() {
        return this.getPlayer().isOnline();
    }

    @Override
    public boolean isWhitelisted() {
        return this.getPlayer().isWhitelisted();
    }

    @Deprecated
    @Override
    public void setBanned(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setBanned(arg0);
    }

    @Override
    public void setWhitelisted(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setWhitelisted(arg0);
    }

    @Override
    public Map<String, Object> serialize() {
        return this.getPlayer().serialize();
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return this.getPlayer().getListeningPluginChannels();
    }

    @Override
    public void sendPluginMessage(Plugin arg0, String arg1, byte[] arg2) {
        if (this.isLoaded())
            this.getPlayer().sendPluginMessage(arg0, arg1, arg2);
    }

    @Override
    public void awardAchievement(Achievement arg0) {
        if (this.isLoaded())
            this.getPlayer().awardAchievement(arg0);
    }

    @Override
    public boolean canSee(Player arg0) {
        return this.getPlayer().canSee(arg0);
    }

    @Override
    public void chat(String arg0) {
        if (this.isLoaded())
            this.getPlayer().chat(arg0);
    }

    @Override
    public void decrementStatistic(Statistic arg0) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().decrementStatistic(arg0);
    }

    @Override
    public void decrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().decrementStatistic(arg0, arg1);
    }

    @Override
    public void decrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().decrementStatistic(arg0, arg1);
    }

    @Override
    public void decrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().decrementStatistic(arg0, arg1);
    }

    @Override
    public void decrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().decrementStatistic(arg0, arg1, arg2);
    }

    @Override
    public void decrementStatistic(Statistic arg0, EntityType arg1, int arg2) {
        if (this.isLoaded())
            this.getPlayer().decrementStatistic(arg0, arg1, arg2);
    }

    @Override
    public InetSocketAddress getAddress() {
        return this.getPlayer().getAddress();
    }

    @Override
    public boolean getAllowFlight() {
        return this.getPlayer().getAllowFlight();
    }

    @Override
    public Location getBedSpawnLocation() {
        return this.getPlayer().getBedSpawnLocation();
    }

    @Override
    public Location getCompassTarget() {
        return this.getPlayer().getCompassTarget();
    }

    @Override
    public String getDisplayName() {
        return this.getPlayer().getDisplayName();
    }

    @Override
    public float getExhaustion() {
        return this.getPlayer().getExhaustion();
    }

    @Override
    public float getExp() {
        return this.getPlayer().getExp();
    }

    @Override
    public float getFlySpeed() {
        return this.getPlayer().getFlySpeed();
    }

    @Override
    public int getFoodLevel() {
        return this.getPlayer().getFoodLevel();
    }

    @Override
    public double getHealthScale() {
        return this.getPlayer().getHealthScale();
    }

    @Override
    public int getLevel() {
        return this.getPlayer().getLevel();
    }

    @Override
    public String getPlayerListName() {
        return this.getPlayer().getPlayerListName();
    }

    @Override
    public long getPlayerTime() {
        return this.getPlayer().getPlayerTime();
    }

    @Override
    public long getPlayerTimeOffset() {
        return this.getPlayer().getPlayerTimeOffset();
    }

    @Override
    public WeatherType getPlayerWeather() {
        return this.getPlayer().getPlayerWeather();
    }

    @Override
    public float getSaturation() {
        return this.getPlayer().getSaturation();
    }

    @Override
    public Scoreboard getScoreboard() {
        return this.getPlayer().getScoreboard();
    }

    @Override
    public int getStatistic(Statistic arg0) throws IllegalArgumentException {
        return this.getPlayer().getStatistic(arg0);
    }

    @Override
    public int getStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
        return this.getPlayer().getStatistic(arg0, arg1);
    }

    @Override
    public int getStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
        return this.getPlayer().getStatistic(arg0, arg1);
    }

    @Override
    public int getTotalExperience() {
        return this.getPlayer().getTotalExperience();
    }

    @Override
    public float getWalkSpeed() {
        return this.getPlayer().getWalkSpeed();
    }

    @Override
    public void giveExp(int arg0) {
        if (this.isLoaded())
            this.getPlayer().giveExp(arg0);
    }

    @Override
    public void giveExpLevels(int arg0) {
        if (this.isLoaded())
            this.getPlayer().giveExpLevels(arg0);
    }

    @Override
    public boolean hasAchievement(Achievement arg0) {
        return this.getPlayer().hasAchievement(arg0);
    }

    @Override
    public void hidePlayer(Player arg0) {
        if (this.isLoaded())
            this.getPlayer().hidePlayer(arg0);
    }

    @Override
    public void incrementStatistic(Statistic arg0) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().incrementStatistic(arg0);
    }

    @Override
    public void incrementStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().incrementStatistic(arg0, arg1);
    }

    @Override
    public void incrementStatistic(Statistic arg0, Material arg1) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().incrementStatistic(arg0, arg1);
    }

    @Override
    public void incrementStatistic(Statistic arg0, EntityType arg1) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().incrementStatistic(arg0, arg1);
    }

    @Override
    public void incrementStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().incrementStatistic(arg0, arg1, arg2);
    }

    @Override
    public void incrementStatistic(Statistic arg0, EntityType arg1, int arg2) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().incrementStatistic(arg0, arg1, arg2);
    }

    @Override
    public boolean isFlying() {
        return this.getPlayer().isFlying();
    }

    @Override
    public boolean isHealthScaled() {
        return this.getPlayer().isHealthScaled();
    }

    @Deprecated
    @Override
    public boolean isOnGround() {
        return this.getPlayer().isOnGround();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return this.getPlayer().isPlayerTimeRelative();
    }

    @Override
    public boolean isSleepingIgnored() {
        return this.getPlayer().isSleepingIgnored();
    }

    @Override
    public boolean isSneaking() {
        return this.getPlayer().isSneaking();
    }

    @Override
    public boolean isSprinting() {
        return this.getPlayer().isSprinting();
    }

    @Override
    public void kickPlayer(String arg0) {
        if (this.isLoaded())
            this.getPlayer().kickPlayer(arg0);
    }

    @Override
    public void loadData() {
        if (this.isLoaded())
            this.getPlayer().loadData();
    }

    @Override
    public boolean performCommand(String arg0) {
        return this.getPlayer().performCommand(arg0);
    }

    @Deprecated
    @Override
    public void playEffect(Location arg0, Effect arg1, int arg2) {
        if (this.isLoaded())
            this.getPlayer().playEffect(arg0, arg1, arg2);
    }

    @Override
    public <T> void playEffect(Location arg0, Effect arg1, T arg2) {
        if (this.isLoaded())
            this.getPlayer().playEffect(arg0, arg1, arg2);
    }

    @Deprecated
    @Override
    public void playNote(Location arg0, byte arg1, byte arg2) {
        if (this.isLoaded())
            this.getPlayer().playNote(arg0, arg1, arg2);
    }

    @Override
    public void playNote(Location arg0, Instrument arg1, Note arg2) {
        if (this.isLoaded())
            this.getPlayer().playNote(arg0, arg1, arg2);
    }

    @Override
    public void playSound(Location arg0, Sound arg1, float arg2, float arg3) {
        if (this.isLoaded())
            this.getPlayer().playSound(arg0, arg1, arg2, arg3);
    }

    @Deprecated
    @Override
    public void playSound(Location arg0, String arg1, float arg2, float arg3) {
        if (this.isLoaded())
            this.getPlayer().playSound(arg0, arg1, arg2, arg3);
    }

    @Override
    public void removeAchievement(Achievement arg0) {
        if (this.isLoaded())
            this.getPlayer().removeAchievement(arg0);
    }

    @Override
    public void resetPlayerTime() {
        if (this.isLoaded())
            this.getPlayer().resetPlayerTime();
    }

    @Override
    public void resetPlayerWeather() {
        if (this.isLoaded())
            this.getPlayer().resetPlayerWeather();
    }

    @Override
    public void saveData() {
        if (this.isLoaded())
            this.getPlayer().saveData();
    }

    @Deprecated
    @Override
    public void sendBlockChange(Location arg0, Material arg1, byte arg2) {
        if (this.isLoaded())
            this.getPlayer().sendBlockChange(arg0, arg1, arg2);
    }

    @Deprecated
    @Override
    public void sendBlockChange(Location arg0, int arg1, byte arg2) {
        if (this.isLoaded())
            this.getPlayer().sendBlockChange(arg0, arg1, arg2);
    }

    @Deprecated
    @Override
    public boolean sendChunkChange(Location arg0, int arg1, int arg2, int arg3, byte[] arg4) {
        return this.getPlayer().sendChunkChange(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public void sendMap(MapView arg0) {
        if (this.isLoaded())
            this.getPlayer().sendMap(arg0);
    }

    @Override
    public void sendRawMessage(String arg0) {
        if (this.isLoaded())
            this.getPlayer().sendRawMessage(arg0);
    }

    @Override
    public void sendSignChange(Location arg0, String[] arg1) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().sendSignChange(arg0, arg1);
    }

    @Override
    public void setAllowFlight(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setAllowFlight(arg0);
    }

    @Override
    public void setBedSpawnLocation(Location arg0) {
        if (this.isLoaded())
            this.getPlayer().setBedSpawnLocation(arg0);
    }

    @Override
    public void setBedSpawnLocation(Location arg0, boolean arg1) {
        if (this.isLoaded())
            this.getPlayer().setBedSpawnLocation(arg0, arg1);
    }

    @Override
    public void setCompassTarget(Location arg0) {
        if (this.isLoaded())
            this.getPlayer().setCompassTarget(arg0);
    }

    @Override
    public void setDisplayName(String arg0) {
        if (this.isLoaded())
            this.getPlayer().setDisplayName(arg0);
    }

    @Override
    public void setExhaustion(float arg0) {
        if (this.isLoaded())
            this.getPlayer().setExhaustion(arg0);
    }

    @Override
    public void setExp(float arg0) {
        if (this.isLoaded())
            this.getPlayer().setExp(arg0);
    }

    @Override
    public void setFlySpeed(float arg0) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().setFlySpeed(arg0);
    }

    @Override
    public void setFlying(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setFlying(arg0);
    }

    @Override
    public void setFoodLevel(int arg0) {
        if (this.isLoaded())
            this.getPlayer().setFoodLevel(arg0);
    }

    @Override
    public void setHealthScale(double arg0) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().setHealthScale(arg0);
    }

    @Override
    public void setHealthScaled(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setHealthScaled(arg0);
    }

    @Override
    public void setLevel(int arg0) {
        if (this.isLoaded())
            this.getPlayer().setLevel(arg0);
    }

    @Override
    public void setPlayerListName(String arg0) {
        if (this.isLoaded())
            this.getPlayer().setPlayerListName(arg0);
    }

    @Override
    public void setPlayerTime(long arg0, boolean arg1) {
        if (this.isLoaded())
            this.getPlayer().setPlayerTime(arg0, arg1);
    }

    @Override
    public void setPlayerWeather(WeatherType arg0) {
        if (this.isLoaded())
            this.getPlayer().setPlayerWeather(arg0);
    }

    @Override
    public void setResourcePack(String arg0) {
        if (this.isLoaded())
            this.getPlayer().setResourcePack(arg0);
    }

    @Override
    public void setSaturation(float arg0) {
        if (this.isLoaded())
            this.getPlayer().setSaturation(arg0);
    }

    @Override
    public void setScoreboard(Scoreboard arg0) throws IllegalArgumentException, IllegalStateException {
        if (this.isLoaded())
            this.getPlayer().setScoreboard(arg0);
    }

    @Override
    public void setSleepingIgnored(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setSleepingIgnored(arg0);
    }

    @Override
    public void setSneaking(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setSneaking(arg0);
    }

    @Override
    public void setSprinting(boolean arg0) {
        if (this.isLoaded())
            this.getPlayer().setSprinting(arg0);
    }

    @Override
    public void setStatistic(Statistic arg0, int arg1) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().setStatistic(arg0, arg1);
    }

    @Override
    public void setStatistic(Statistic arg0, Material arg1, int arg2) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().setStatistic(arg0, arg1, arg2);
    }

    @Override
    public void setStatistic(Statistic arg0, EntityType arg1, int arg2) {
        if (this.isLoaded())
            this.getPlayer().setStatistic(arg0, arg1, arg2);
    }

    @Deprecated
    @Override
    public void setTexturePack(String arg0) {
        if (this.isLoaded())
            this.getPlayer().setTexturePack(arg0);
    }

    @Override
    public void setTotalExperience(int arg0) {
        if (this.isLoaded())
            this.getPlayer().setTotalExperience(arg0);
    }

    @Override
    public void setWalkSpeed(float arg0) throws IllegalArgumentException {
        if (this.isLoaded())
            this.getPlayer().setWalkSpeed(arg0);
    }

    @Override
    public void showPlayer(Player arg0) {
        if (this.isLoaded())
            this.getPlayer().showPlayer(arg0);
    }

    @Deprecated
    @Override
    public void updateInventory() {
        if (this.isLoaded())
            this.getPlayer().updateInventory();
    }

}
