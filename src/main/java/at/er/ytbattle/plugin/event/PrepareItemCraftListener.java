package at.er.ytbattle.plugin.event;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import at.er.ytbattle.plugin.BattlePlugin;

public class PrepareItemCraftListener implements Listener {

	public PrepareItemCraftListener() {
		Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
	}

	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent event) {

		if (event.getInventory().getResult().getType() == Material.WOOL) {
			ItemStack ce = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta ceMeta = (BookMeta) ce.getItemMeta();
			ceMeta.setDisplayName(ChatColor.GOLD + "Cheat Protection");
			ceMeta.addPage("Nein.");
			ceMeta.setAuthor("Entwickler");
			ce.setItemMeta(ceMeta);
			event.getInventory().setResult(ce);
		}

		if (event.getInventory().getResult().getType() == Material.CARPET) {
			ItemStack tear = new ItemStack(Material.GHAST_TEAR, 1);
			ItemMeta tearMeta = tear.getItemMeta();
			tearMeta.setDisplayName(ChatColor.GOLD + "Live Exchanger");
			tearMeta.setLore(Arrays.asList("Right Click Me"));
			tear.setItemMeta(tearMeta);
			event.getInventory().setResult(tear);
		}
	}

}
