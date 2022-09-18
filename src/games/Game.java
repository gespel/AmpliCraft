package games;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ampliCraft.AmpliPlayer;
import ampliCraft.PlayerSets;
//import levelmoney.Geldsystem;
//import levelmoney.Levelsystem;

public class Game {
	Player p;
	public Game(Player p) {
		this.p = p;
	}
	public void startGame(String gameName) {
		if(gameName.equalsIgnoreCase("jumpandrun") || gameName.equalsIgnoreCase("jnr")) {
			Location start = new Location(Bukkit.getWorld("games"), -24.5, 69, 272.5);
			p.teleport(start);
			p.sendMessage(ChatColor.GOLD + "Willkommen beim " + ChatColor.GREEN + "Jump and Run" + ChatColor.GOLD + "!");
			p.sendMessage(ChatColor.GOLD + "Erreiche das Ziel, und du erhälst einen fantastischen Preis!");
			PlayerSets.jnrPlayer.add(p);
			long startTime = System.currentTimeMillis();
			PlayerSets.jnrTimer.put(p, startTime);
		}
		else if(gameName.equalsIgnoreCase("elytra")) {
			Location start = new Location(Bukkit.getWorld("games"), 75, 144, 461);
			p.teleport(start);
			PlayerSets.elytraPlayer.add(p);
			p.sendMessage(ChatColor.GOLD + "Willkommen beim " + ChatColor.GREEN + "Elytra Parkour" + ChatColor.GOLD + "!");
			p.sendMessage(ChatColor.GOLD + "Wenn du das Ziel erreichst erhälst du einen fantastischen Preis!");
			Inventory inv = p.getInventory();
			inv.addItem(new ItemStack(Material.ELYTRA, 1));
			inv.addItem(new ItemStack(Material.FIREWORK_ROCKET, 64));
		}
		else if(gameName.equalsIgnoreCase("folterkammer")) {
			Location start = new Location(Bukkit.getWorld("games"), -44, 60, 387);
			p.teleport(start);
			p.sendMessage(ChatColor.RED + "Willkommen in der Folterkammer!");
			p.sendMessage(ChatColor.RED + "Erreiche das Ziel, und du erhälst einen fantastischen Preis!");
			PlayerSets.folterkammerPlayer.add(p);
			long startTime = System.currentTimeMillis();
			PlayerSets.folterkammerTimer.put(p, startTime);
		}
		else {
			p.sendMessage(ChatColor.RED + "Es gibt kein Spiel mit diesem Namen!");
		}
	}
	public static void finishedJnr(Player p, PlayerInteractEvent event, FileConfiguration config) {
		long endTime = System.currentTimeMillis();
		long startTime = PlayerSets.jnrTimer.get(p);
		double time = ((double)endTime-startTime)/1000;
		p.sendMessage(ChatColor.LIGHT_PURPLE + "Du hast " + time + " Sekunden gebraucht!");
		Inventory inv = p.getInventory();
		AmpliPlayer ap = new AmpliPlayer(p, config);
		inv.addItem(new ItemStack(Material.DIAMOND, 10));
		p.sendMessage(ChatColor.GOLD + "Du hast das Ende erreicht!");
		p.sendMessage(ChatColor.GOLD + "Du erhälst " + ChatColor.GREEN + "10" + ChatColor.GOLD + " Diamanten!");
		ap.addExp(200);
		PlayerSets.jnrPlayer.remove(p);
		PlayerSets.jnrTimer.remove(p);
		p.teleport(Bukkit.getWorld("world").getSpawnLocation());
	}
	public static void finishedFolterkammer(Player p, PlayerInteractEvent event, FileConfiguration config) {
		long endTime = System.currentTimeMillis();
		long startTime = PlayerSets.folterkammerTimer.get(p);
		double time = ((double)endTime-startTime)/1000;
		Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE + p.getName() + " hat soeben die Folterkammer beendet. Zeit: " + time);
		p.sendMessage(ChatColor.LIGHT_PURPLE + "Du hast " + time + " Sekunden gebraucht!");
		Inventory inv = p.getInventory();
		AmpliPlayer ap = new AmpliPlayer(p, config);
		inv.addItem(new ItemStack(Material.DIAMOND, 200));
		p.sendMessage(ChatColor.GOLD + "Du hast das Ende erreicht! WTF");
		p.sendMessage(ChatColor.GOLD + "Du erhälst " + ChatColor.GREEN + "200" + ChatColor.GOLD + " Diamanten!");
		ap.addExp(10000);
		PlayerSets.folterkammerPlayer.remove(p);
		PlayerSets.folterkammerTimer.remove(p);
		p.teleport(Bukkit.getWorld("world").getSpawnLocation());
	}
	public static void finishedEyltra(Player p, PlayerInteractEvent event, FileConfiguration config) {
		Inventory inv = p.getInventory();
		AmpliPlayer ap = new AmpliPlayer(p, config);
		inv.remove(new ItemStack(Material.ELYTRA, 1));
		inv.remove(new ItemStack(Material.FIREWORK_ROCKET));
		p.sendMessage(ChatColor.GOLD + "Du hast das Ende erreicht!");
		ap.addExp(100);
		ap.giveMoney(10);
		PlayerSets.elytraPlayer.remove(p);
		p.teleport(Bukkit.getWorld("world").getSpawnLocation());
	}
}
