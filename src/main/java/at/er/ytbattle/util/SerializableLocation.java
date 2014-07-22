package at.er.ytbattle.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;

public final class SerializableLocation implements Externalizable {
	private static final long serialVersionUID = 1L;

	private String world;
	private UUID uuid;
	private double x, y, z;
	private float yaw, pitch;
	private Location loc;

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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(world);
		out.writeUTF(uuid.toString());
		out.writeDouble(x);
		out.writeDouble(y);
		out.writeDouble(z);
		out.writeFloat(yaw);
		out.writeFloat(pitch);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		world = in.readUTF();
		uuid = UUID.fromString(in.readUTF());
		x = in.readDouble();
		y = in.readDouble();
		z = in.readDouble();
		yaw = in.readFloat();
		pitch = in.readFloat();
	}
}
