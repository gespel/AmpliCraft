package shops;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import ampliCraft.Locations;

public class WeaponShop extends Shop {
	
	public WeaponShop() {
		super("WeaponShop");
		HashMap<Enchantment, Integer> dunkelschattenEnchantments = new HashMap<Enchantment, Integer>();
		dunkelschattenEnchantments.put(Enchantment.DAMAGE_ALL, 3);
		CustomItem dunkelschatten = new CustomItem(Material.DIAMOND_SWORD, dunkelschattenEnchantments, ChatColor.DARK_PURPLE + "Dunkelschatten", new String[] {ChatColor.AQUA + "Niemand kennt den genauen", ChatColor.AQUA +  "Ursprung dieser Klinge.", ChatColor.AQUA + "Ihre Macht aber, ist schon", ChatColor.AQUA +  "bei Betrachtung spürbar."});
		
		HashMap<Enchantment, Integer> asariEnchantments = new HashMap<Enchantment, Integer>();
		asariEnchantments.put(Enchantment.DAMAGE_ALL, 4);
		CustomItem asari = new CustomItem(Material.NETHERITE_SWORD, asariEnchantments, ChatColor.GOLD + "Asari", new String[] {ChatColor.AQUA + "Die Klinge der Könige."});
		
		priceList.put(asari.getItem(), 3000.f);
		priceList.put(dunkelschatten.getItem(), 1000.f);
		priceList.put(new ItemStack(Material.NETHERITE_SWORD), 200.f);
		priceList.put(new ItemStack(Material.DIAMOND_SWORD), 100.f);
		priceList.put(new ItemStack(Material.GOLDEN_SWORD), 50.f);
		priceList.put(new ItemStack(Material.IRON_SWORD), 5.f);
		priceList.put(new ItemStack(Material.STONE_SWORD), 2.f);
		priceList.put(new ItemStack(Material.WOODEN_SWORD), 1.f);
		generateChestInventory(Locations.weaponShopChest.getBlock());
	}
	

}
