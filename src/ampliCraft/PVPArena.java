package ampliCraft;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class PVPArena {
	final static Location spawnALinks = new Location(Bukkit.getWorld("world"), -193, 66, -242);
	final static Location spawnBLinks = new Location(Bukkit.getWorld("world"), -195, 66, -272);
	final static Location spawnCLinks = new Location(Bukkit.getWorld("world"), -212, 68, -240);
	final static Location spawnDLinks = new Location(Bukkit.getWorld("world"), -227, 66, -253);
	final static Location spawnELinks = new Location(Bukkit.getWorld("world"), -215, 70, -269);
	final static Location spawnFLinks = new Location(Bukkit.getWorld("world"), -219, 66, -262);
	
	final static Location spawnARechts = new Location(Bukkit.getWorld("world"), -190, 66, -243);
	final static Location spawnBRechts = new Location(Bukkit.getWorld("world"), -167, 70, -244);
	final static Location spawnCRechts = new Location(Bukkit.getWorld("world"), -164, 66, -234);
	final static Location spawnDRechts = new Location(Bukkit.getWorld("world"), -160, 73, -256);
	final static Location spawnERechts = new Location(Bukkit.getWorld("world"), -157, 72, -267);
	final static Location spawnFRechts = new Location(Bukkit.getWorld("world"), -169, 66, -270);
	static Random wuerfel = new Random();
	
	
	
	public static void joinFight(Player p, String team) {
		if(team.equalsIgnoreCase("blau")) {
			spawnBlueFighter(p);
		}
		else if(team.equalsIgnoreCase("rot")) {
			spawnRedFighter(p);
		}
		else if(team.equalsIgnoreCase("exit")) {
			if(PlayerSets.blueTeam.contains(p) || PlayerSets.redTeam.contains(p)) {
				if(PlayerSets.blueTeam.contains(p)) {
					PlayerSets.blueTeam.remove(p);
				}
				else {
					PlayerSets.redTeam.remove(p);
				}
				p.teleport(Bukkit.getWorld("world").getSpawnLocation());
				p.sendMessage("Du hast die Arena verlassen!");
			}
			else {
				p.sendMessage("Du befindest dich nicht im Arenakampf!");
			}
		}
		else {
			p.sendMessage(ChatColor.RED + "Dieses Team gibt es nicht! " + ChatColor.RESET);
			p.sendMessage(ChatColor.GREEN + "Zur Auswahl stehen " + ChatColor.BLUE + "blau" + ChatColor.GREEN + " und " + ChatColor.RED + "rot" + ChatColor.GREEN + "!");
		}
	}
	public static Location getRandomSpawnLocationLinks() {
		int random = wuerfel.nextInt(3);
		if(random == 0) {
			return spawnALinks;
		}
		if(random == 1) {
			return spawnBLinks;
		}
		if(random == 2) {
			return spawnCLinks;
		}
		if(random == 3) {
			return spawnDLinks;
		}
		if(random == 4) {
			return spawnELinks;
		}
		if(random == 5) {
			return spawnFLinks;
		}
		return Bukkit.getWorld("world").getSpawnLocation();
	}
	public static Location getRandomSpawnLocationRechts() {
		int random = wuerfel.nextInt(3);
		if(random == 0) {
			return spawnARechts;
		}
		if(random == 1) {
			return spawnBRechts;
		}
		if(random == 2) {
			return spawnCRechts;
		}
		if(random == 3) {
			return spawnDRechts;
		}
		if(random == 4) {
			return spawnERechts;
		}
		if(random == 5) {
			return spawnFRechts;
		}
		return Bukkit.getWorld("world").getSpawnLocation();
	}
	public static void spawnBlueFighter(Player p) {
		p.teleport(getRandomSpawnLocationLinks());
		p.setGameMode(GameMode.SURVIVAL);
		p.getInventory().clear();
		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
		p.getInventory().addItem(new ItemStack(Material.BREAD, 10));
		p.getInventory().addItem(new ItemStack(Material.BOW, 1));
		p.getInventory().addItem(new ItemStack(Material.ARROW, 32));
		p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
		p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
		p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
		p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
		p.sendMessage("Du wurdest für " + ChatColor.BLUE + "Blau" + ChatColor.RESET + " gespawnt! Fight!");
		if(PlayerSets.redTeam.contains(p)) {
			PlayerSets.redTeam.remove(p);
		}
		PlayerSets.blueTeam.add(p);
	}
	public static void spawnRedFighter(Player p) {
		p.teleport(getRandomSpawnLocationRechts());
		p.setGameMode(GameMode.SURVIVAL);
		p.getInventory().clear();
		p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
		p.getInventory().addItem(new ItemStack(Material.BREAD, 10));
		p.getInventory().addItem(new ItemStack(Material.BOW, 1));
		p.getInventory().addItem(new ItemStack(Material.ARROW, 32));
		p.getInventory().setHelmet(new ItemStack(Material.NETHERITE_HELMET, 1));
		p.getInventory().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS, 1));
		p.getInventory().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE, 1));
		p.getInventory().setBoots(new ItemStack(Material.NETHERITE_BOOTS, 1));
		p.sendMessage("Du wurdest für " + ChatColor.RED + "Rot" + ChatColor.RESET + " gespawnt! Fight!");
		if(PlayerSets.blueTeam.contains(p)) {
			PlayerSets.blueTeam.remove(p);
		}
		PlayerSets.redTeam.add(p);
	}
	public static void onDeathCheckForArenaFight(Player p, PlayerDeathEvent event, FileConfiguration config) {
		if(PlayerSets.blueTeam.contains(p)) {
			Player killer = p.getKiller();
			Levelsystem lvl = new Levelsystem(killer, config);
			if(PlayerSets.redTeam.contains(killer)) {
				event.setKeepInventory(true);
				event.setKeepLevel(true);
				killer.sendMessage(ChatColor.GOLD + "Du hast einen Kill gemacht!");
				lvl.addExp(100);
				Bukkit.getPluginManager().getPlugin("AmpliCraft").saveConfig();
				p.sendMessage(ChatColor.DARK_RED + "Du wurdest besiegt!");
				p.getInventory().clear();
			}
			else {
				PlayerSets.blueTeam.remove(p);
			}
		}
		else if(PlayerSets.redTeam.contains(p)) {
			Player killer = p.getKiller();
			Levelsystem lvl = new Levelsystem(killer, config);
			if(PlayerSets.blueTeam.contains(killer)) {
				event.setKeepInventory(true);
				event.setKeepLevel(true);
				PlayerSets.redTeam.remove(p);
				killer.sendMessage(ChatColor.GOLD + "Du hast einen Kill gemacht!");
				lvl.addExp(100);
				Bukkit.getPluginManager().getPlugin("AmpliCraft").saveConfig();
				p.sendMessage(ChatColor.DARK_RED + "Du wurdest besiegt!");
				p.getInventory().clear();
			}
			else {
				PlayerSets.redTeam.remove(p);
			}
		}
	}
}
