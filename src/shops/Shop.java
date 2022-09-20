package shops;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ampliCraft.AmpliPlayer;
import net.md_5.bungee.api.ChatColor;

public class Shop {
	HashMap<ItemStack, Float> priceList = new HashMap<ItemStack, Float>();
	String name;
	public Shop(String name) {
		this.name = name;
	}
	public void buyItem(InventoryClickEvent e, FileConfiguration config) {
		Float price = this.getPrice(e.getCurrentItem());
		ItemStack item = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		AmpliPlayer ap = new AmpliPlayer(p, config);
		if(ap.payMoney(price)) {
			p.getInventory().addItem(item);
			String itemName;
			if(item.hasItemMeta()) {
				itemName = item.getItemMeta().getDisplayName();
			}
			else {
				itemName = item.getType().name();
			}
			p.sendMessage(ChatColor.AQUA + "Du hast " + ChatColor.GOLD + itemName + ChatColor.AQUA + " gekauft!");
		}
		e.setCancelled(true);
	}
	public float getPrice(ItemStack input) {
		return priceList.get(input);
	}
	public void generateChestInventory(Block chest) {
		Chest c = (Chest) chest.getState();
		Inventory bi = c.getBlockInventory();
		bi.clear();
		for(ItemStack i : priceList.keySet()) {
			bi.addItem(new ItemStack(i));
		}
		Bukkit.getPluginManager().getPlugin("AmpliCraft").getLogger().info("[SHOPS] " + this.name + " was initialized");
	}
}
