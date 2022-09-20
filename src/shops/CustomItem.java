package shops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItem {
	ItemStack item;
	public CustomItem(Material base, HashMap<Enchantment, Integer> en, String name, String[] lore) {
		item = new ItemStack(base, 1);
		for(Enchantment i : en.keySet()) {
			item.addEnchantment(i, en.get(i));
		}
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		List<String> loreList = new ArrayList<String>();
		for(String line : lore) {
			loreList.add(line);
		}
		itemMeta.setLore(loreList);
		item.setItemMeta(itemMeta);
	}
	ItemStack getItem() {
		return item;
	}
}
