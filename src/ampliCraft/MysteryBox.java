package ampliCraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MysteryBox {
	Player p;
	AmpliCraft plugin;
	HashMap<Integer, ItemStack> itemList = new HashMap<Integer, ItemStack>();
	public MysteryBox(Player p, AmpliCraft plugin) {
		this.p = p;
		this.plugin = plugin;
		initItemList();
	}
	public void openMysteryBox() {
		p.getWorld().spawnParticle(Particle.SPELL, p.getLocation(), 100);
		p.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, p.getLocation(), 100);
		p.getWorld().spawnParticle(Particle.DRIP_LAVA, p.getLocation(), 100);
		p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 0);
		p.getWorld().playEffect(p.getLocation(), Effect.INSTANT_POTION_BREAK, 0);
		p.getInventory().addItem(randomItem());
	}
	private ItemStack randomItem() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 4 + 1);
		return itemList.get(randomNum);
	}
	private void initItemList() {
		//===================================================================
		ItemStack nashorum = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta nashorumIm = nashorum.getItemMeta();
		nashorumIm.setDisplayName(ChatColor.RED + "Nashorum");
		List<String> loreList = new ArrayList<String>();
		loreList.add(ChatColor.GOLD + "Das legendäre Schwert!");//This is the first line of lore
		loreList.add(ChatColor.GOLD + "Es wird erzählt es hätte bereits einen Drachen besiegt");//This is the second line of lore
		nashorumIm.setLore(loreList);
		nashorum.setItemMeta(nashorumIm);
		itemList.put(0, nashorum);
		//===================================================================
		ItemStack diamondBlock = new ItemStack(Material.DIAMOND_BLOCK, 2);
		itemList.put(1, diamondBlock);
		//===================================================================
		ItemStack enderPerl = new ItemStack(Material.ENDER_PEARL, 3);
		itemList.put(2, enderPerl);
		//===================================================================
		ItemStack netherArmor = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
		itemList.put(3, netherArmor);
		//===================================================================
		ItemStack gold = new ItemStack(Material.GOLD_INGOT, 10);
		itemList.put(4, gold);
	}
}
