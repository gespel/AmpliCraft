package shops;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ampliCraft.AmpliPlayer;
import ampliCraft.Locations;
import net.md_5.bungee.api.ChatColor;

public class WeaponShop {
	HashMap<Material, Float> priceList = new HashMap<Material, Float>();
	public WeaponShop() {
		priceList.put(Material.NETHERITE_SWORD, 200.f);
		priceList.put(Material.DIAMOND_SWORD, 100.f);
		priceList.put(Material.IRON_SWORD, 5.f);
		
		Block b = Locations.weaponShopChest.getBlock();
		Chest c = (Chest) b.getState();
		Inventory bi = c.getBlockInventory();
		bi.clear();
		for(Material m : priceList.keySet()) {
			bi.addItem(new ItemStack(m));
		}
	}
	public void buyItem(InventoryClickEvent e, FileConfiguration config) {
		Float price = this.getPrice(e.getCurrentItem().getType());
		Material item = e.getCurrentItem().getType();
		Player p = (Player) e.getWhoClicked();
		AmpliPlayer ap = new AmpliPlayer(p, config);
		if(ap.payMoney(price)) {
			p.getInventory().addItem(new ItemStack(item, 1));
			p.sendMessage(ChatColor.AQUA + "Du hast " + ChatColor.GOLD + item.toString() + ChatColor.AQUA + " gekauft!");
		}
		e.setCancelled(true);
	}
	public float getPrice(Material input) {
		return priceList.get(input);
	}

}
