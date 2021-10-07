package ampliCraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

public class Worlds {
	public static void teleport(String worldname, Player p) {
		if(worldname.equalsIgnoreCase("hauptwelt")) {
			Location spawn = Bukkit.getWorld("world").getSpawnLocation();
			p.teleport(spawn);
			p.sendMessage("Du wurdest zur Hauptwelt teleportiert!");
		}
		else {
			Location spawn = Bukkit.getWorld(worldname).getSpawnLocation();
			p.teleport(spawn);
			p.sendMessage("Du wurdest zu " + worldname + " teleportiert!");
		}
	}
	public static void createWorld(String name, Player p) {
		WorldCreator wc = new WorldCreator(name);
		wc.environment(World.Environment.NORMAL);
		wc.type(WorldType.NORMAL);
		wc.createWorld();
		p.sendMessage("Die Welt " + name + " wurde erschaffen!");
	}
	public static void listWorlds(Player p) {
		
	}
}
