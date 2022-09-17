package games;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import ampliCraft.AmpliCraft;
import ampliCraft.Locations;
import net.md_5.bungee.api.ChatColor;

public class PVEArena {
	Player p;
	FileConfiguration config;
	AmpliCraft plugin;
	private Location spawn = new Location(Bukkit.getWorld("games"), -39, 69, 244);
	public PVEArena(Player p, FileConfiguration config, AmpliCraft plugin) {
		this.p = p;
		this.config = config;
		this.plugin = plugin;
	}
	public void startFight() {
		p.teleport(Locations.pveArena);
		Bukkit.getWorld("games").spawnEntity(spawn, EntityType.ZOMBIE);
		Bukkit.getWorld("games").spawnEntity(spawn, EntityType.ZOMBIE);
		Bukkit.getWorld("games").spawnEntity(spawn, EntityType.ZOMBIE);
		p.sendMessage(ChatColor.RED + "Die Gegner wurden gespawnt! FIGHT");
	}
}
