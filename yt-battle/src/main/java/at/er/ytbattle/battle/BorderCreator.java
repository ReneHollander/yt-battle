package at.er.ytbattle.battle;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.patterns.SingleBlockPattern;
import com.sk89q.worldedit.tools.brushes.HollowCylinderBrush;

public class BorderCreator {

	private Battle plugin;

	public BorderCreator(Battle plugin) {
		this.plugin = plugin;
	}

	public String build(int radius) {
		HollowCylinderBrush hcb = new HollowCylinderBrush(256);
		try {
			hcb.build(new EditSession(new BukkitWorld(plugin.getGame().getSpawn().getLocation().getWorld()), 2147483647), new Vector(plugin.getGame().getSpawn().getLocation().getX(), 0, plugin.getGame().getSpawn().getLocation().getZ()),
					new SingleBlockPattern(new BaseBlock(20, 0)), radius);

			return Battle.prefix() + "Border complete!";
		} catch (MaxChangedBlocksException e) {
			return Battle.prefix() + "Border generation failed: WorldEdit max edit blocks reached";
		}
	}
}