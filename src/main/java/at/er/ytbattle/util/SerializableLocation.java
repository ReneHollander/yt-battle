package at.er.ytbattle.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;

public final class SerializableLocation {
    private String world;
    private UUID uuid;
    private double x, y, z;
    private float yaw, pitch;
    private transient Location loc;

    public SerializableLocation() {

    }

    public SerializableLocation(Location l) {
        this.world = l.getWorld().getName();
        this.uuid = l.getWorld().getUID();
        this.x = l.getX();
        this.y = l.getY();
        this.z = l.getZ();
        this.yaw = l.getYaw();
        this.pitch = l.getPitch();
    }

    public static Location returnLocation(SerializableLocation l) {
        float pitch = l.pitch;
        float yaw = l.yaw;
        double x = l.x;
        double y = l.y;
        double z = l.z;
        World world = Bukkit.getWorld(l.world);
        Location location = new Location(world, x, y, z, yaw, pitch);
        return location;
    }

    public static Location returnBlockLocation(SerializableLocation l) {
        double x = l.x;
        double y = l.y;
        double z = l.z;
        World world = Bukkit.getWorld(l.world);
        Location location = new Location(world, x, y, z);
        return location;
    }

    public final Location getLocation() {
        Server server = Bukkit.getServer();
        if (loc == null) {
            World world = server.getWorld(this.uuid);
            if (world == null) {
                world = server.getWorld(this.world);
            }
            loc = new Location(world, x, y, z, yaw, pitch);
        }
        return loc;
    }
}
